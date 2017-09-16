package com.p2p.webapp.activity.activitymgr.action;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.p2p.webapp.activity.activitymgr.service.ActivityMgrService;
import com.p2p.webapp.activity.activitymgr.vo.ActivityVo;
import com.p2p.webapp.city.citymgr.service.CityMgrService;
import com.p2p.webapp.city.citymgr.vo.AreaVo;
import com.p2p.webapp.city.citymgr.vo.CityVo;
import com.p2p.webapp.common.base.BaseAction;
import com.p2p.webapp.common.constant.Constant;
import com.p2p.webapp.common.util.DateTimeFormatUtil;
import com.p2p.webapp.enroll.enrollmgr.service.EnrollMgrService;
import com.p2p.webapp.enroll.enrollmgr.vo.EnrollVo;
import com.p2p.webapp.group.groupmgr.service.GroupMgrService;
import com.p2p.webapp.group.groupmgr.vo.GroupVo;
import com.p2p.webapp.user.usercenter.service.UserCenterService;
import com.p2p.webapp.user.usercenter.vo.UserCreditVo;
import com.p2p.webapp.user.usercenter.vo.UserInfoVo;

import 

/**
 * @description 比赛管理
 * @author
 * @date 2015-10-28 下午4:21:01
 */
public class ActivityMgrAction extends BaseAction {
    private static final long serialVersionUID = 1L;

    // 写日志对象
    public static Logger logger = LoggerFactory.getLogger(ActivityMgrAction.class);
    private ActivityMgrService activityMgrService;// 比赛Service
    private EnrollMgrService enrollMgrService;// 报名Service
    private UserCenterService userCenterService;// 用户中心Service
    private GroupMgrService groupMgrService;// 群管理Service
    private CityMgrService cityMgrService;

    private List<ActivityVo> allActivity;// 全部比赛list
    private List<ActivityVo> newActivity;// 最新比赛list
    private List<ActivityVo> historyActivity;// 历史比赛list
    private List<GroupVo> groupVoList;
    private List<AreaVo> areaList;

    private ActivityVo activityVo;
    private EnrollVo enrollVo;
    private GroupVo groupVo;
    private UserCreditVo userCreditVo;
    private UserInfoVo contactVo;

    private String totalNum;

    /**
     * @description 查询全部比赛
     * @version
     * @title
     * @author
     * @return 全部比赛页面
     */
    public String queryActivityInit() {
        CityVo cityVo = new CityVo();
        /*
        //jiangxian
        String cityName = session.get("city").toString();
        cityVo = cityMgrService.queryCityDetail(cityName);
        AreaVo areaVo = new AreaVo();
        areaVo.setCity_code(cityVo.getCity_code());
        areaList = cityMgrService.queryArea(areaVo);
        */
        logger.debug("比赛页面初始化");
        return "activityInit";
    }

    /**
     * @description 滑动栏查询比赛
     * @version
     * @title
     * @author
     * @return 全部最新比赛页面
     */
    public String queryAllActivity() {
        logger.debug("查询全部比赛");
        activityVo = new ActivityVo();
        String type = request.getParameter("type");
        String count = request.getParameter("count");
        activityVo.setCount(count);
        if (type.equals("0")) {
            // 查询全部比赛
            allActivity = activityMgrService.queryAllActivity(activityVo);
        } else if (type.equals("1")) {
            // 查询全部比赛
            allActivity = activityMgrService.queryAllNewActivity(activityVo);
        } else if (type.equals("2")) {
            // 查询历史比赛
            allActivity = activityMgrService.queryAllHistoryActivity(activityVo);
        }
        return "scrollActivity";
    }

    /**
     * @description 滑动栏时间区县查询比赛
     * @version
     * @title
     * @author
     * @return 全部最新比赛页面
     */
    public String queryActivityByTimeArea() {
        logger.debug("滑动条查询比赛");
        activityVo = new ActivityVo();
        SimpleDateFormat sdf = new SimpleDateFormat(DateTimeFormatUtil.YEAR_MONTH_DAY_TEMPLATE);
        String dstr = request.getParameter("beginDate");
        java.util.Date date;
        try {
            date = sdf.parse(dstr);
            activityVo.setActivity_begin(date);
            String areaname = new String(request.getParameter("areacode").getBytes("iso-8859-1"), "utf-8");
            activityVo.setSite_addr_district(areaname);
            activityVo.setCount(request.getParameter("count"));
            // 查询全部比赛
            allActivity = activityMgrService.queryActivityByTimeArea(activityVo);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "scrollActivity";
    }

    /**
     * @description 查询比赛详细信息
     * @version
     * @title
     * @author
     * @return 比赛详细信息页面
     */
    public String queryActivityDetailInfo() {
        logger.debug("查询比赛详细信息");
        String activityId = request.getParameter("activityId");
        if (activityId != null && activityId != "") {
            activityVo = new ActivityVo();
            activityVo.setActivity_id(activityId);
        }
        // 查询比赛详细信息
        activityVo = activityMgrService.queryActivityDetailInfo(activityVo);
        contactVo = new UserInfoVo();
        contactVo = userCenterService.queryUserInfo(activityVo.getManage_id());
        groupVo = new GroupVo();
        groupVo.setGroup_id(activityVo.getGroup_id());
        groupVo = groupMgrService.queryGroupInfo(groupVo);

        enrollVo = new EnrollVo();
        // 查询我的账户情况
        UserCreditVo queryCreditVo = new UserCreditVo();
        userCreditVo = new UserCreditVo();
        if (request.getSession().getAttribute("userid") != null) {
            queryCreditVo.setUser_id(request.getSession().getAttribute("userid").toString());
            userCreditVo = userCenterService.queryUserCredit(queryCreditVo);
        }

        // 查询比赛报名情况
        EnrollVo queryEnrollVo = new EnrollVo();
        queryEnrollVo.setActivity_id(activityVo.getActivity_id());
        totalNum = enrollMgrService.queryEnrollCount(queryEnrollVo);
        if (totalNum == null) {
            totalNum = "0";
        }

        return "activityDetailInfo";
    }

    /**
     * @description 添加新比赛初始化页面
     * @version
     * @title
     * @author
     * @return 比赛详细信息页面
     */
    public String addActivityInit() {
        groupVoList = groupMgrService.queryMyManageGroup(session.get("userid").toString());
       /* CityVo cityVo = new CityVo();
        //jiangxian
        String cityName = "北京";//session.get("city").toString();
        cityVo = cityMgrService.queryCityDetail(cityName);
        AreaVo areaVo = new AreaVo();
        areaVo.setCity_code(cityVo.getCity_code());
        areaList = cityMgrService.queryArea(areaVo);*/
        return "addActivityInit";
    }

    /**
     * @description 添加新比赛
     * @version
     * @title
     * @author
     * @return 比赛详细信息页面
     */
    public String addActivity() {
        String activityId = activityMgrService.addActivity(activityVo);
        activityVo.setActivity_id(activityId);
        return "queryActivityDetailInfo";
    }

    /**
     * @description 修改比赛
     * @version
     * @title
     * @author
     * @return 比赛详细信息页面
     */
    public String updateActivity() {
        activityMgrService.updateActivity(activityVo);
        return "activityDetailInfo";
    }

    /**
     * @description 比赛结束按钮
     * @version
     * @title
     * @author
     * @return 结算页面
     */
    public String endActivity() {
        activityVo = activityMgrService.queryActivityDetailInfo(activityVo);
        activityVo.setActivity_status(Constant.ACTIVITY_STATUS_UNSETTLE);
        activityMgrService.updateActivity(activityVo);
        return "enrollDetailInfo";
    }

    /**
     * @description 取消比赛按钮
     * @version
     * @title
     * @author
     * @return 结算页面
     */
    public String cancelActivity() {
        activityVo = activityMgrService.queryActivityDetailInfo(activityVo);
        activityVo.setActivity_status(Constant.ACTIVITY_STATUS_CANCEL);
        activityMgrService.updateActivity(activityVo);
        return "activityInit";
    }

    public ActivityMgrService getActivityMgrService() {
        return activityMgrService;
    }

    public void setActivityMgrService(ActivityMgrService activityMgrService) {
        this.activityMgrService = activityMgrService;
    }

    public CityMgrService getCityMgrService() {
        return cityMgrService;
    }

    public void setCityMgrService(CityMgrService cityMgrService) {
        this.cityMgrService = cityMgrService;
    }

    public EnrollMgrService getEnrollMgrService() {
        return enrollMgrService;
    }

    public void setEnrollMgrService(EnrollMgrService enrollMgrService) {
        this.enrollMgrService = enrollMgrService;
    }

    public UserCenterService getUserCenterService() {
        return userCenterService;
    }

    public void setUserCenterService(UserCenterService userCenterService) {
        this.userCenterService = userCenterService;
    }

    public GroupMgrService getGroupMgrService() {
        return groupMgrService;
    }

    public void setGroupMgrService(GroupMgrService groupMgrService) {
        this.groupMgrService = groupMgrService;
    }

    public List<ActivityVo> getNewActivity() {
        return newActivity;
    }

    public void setNewActivity(List<ActivityVo> newActivity) {
        this.newActivity = newActivity;
    }

    public ActivityVo getActivityVo() {
        return activityVo;
    }

    public void setActivityVo(ActivityVo activityVo) {
        this.activityVo = activityVo;
    }

    public EnrollVo getEnrollVo() {
        return enrollVo;
    }

    public void setEnrollVo(EnrollVo enrollVo) {
        this.enrollVo = enrollVo;
    }

    public GroupVo getGroupVo() {
        return groupVo;
    }

    public void setGroupVo(GroupVo groupVo) {
        this.groupVo = groupVo;
    }

    public UserCreditVo getUserCreditVo() {
        return userCreditVo;
    }

    public void setUserCreditVo(UserCreditVo userCreditVo) {
        this.userCreditVo = userCreditVo;
    }

    public List<GroupVo> getGroupVoList() {
        return groupVoList;
    }

    public void setGroupVoList(List<GroupVo> groupVoList) {
        this.groupVoList = groupVoList;
    }

    public List<AreaVo> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<AreaVo> areaList) {
        this.areaList = areaList;
    }

    public List<ActivityVo> getAllActivity() {
        return allActivity;
    }

    public void setAllActivity(List<ActivityVo> allActivity) {
        this.allActivity = allActivity;
    }

    public List<ActivityVo> getHistoryActivity() {
        return historyActivity;
    }

    public void setHistoryActivity(List<ActivityVo> historyActivity) {
        this.historyActivity = historyActivity;
    }

    public UserInfoVo getContactVo() {
        return contactVo;
    }

    public void setContactVo(UserInfoVo contactVo) {
        this.contactVo = contactVo;
    }

    public String getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(String totalNum) {
        this.totalNum = totalNum;
    }

}
