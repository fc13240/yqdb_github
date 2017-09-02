package com.p2p.webapp.activity.activitymgr.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.p2p.webapp.activity.activitymgr.dao.ActivityDao;
import com.p2p.webapp.activity.activitymgr.entity.ActivityInfo;
import com.p2p.webapp.activity.activitymgr.service.ActivityMgrService;
import com.p2p.webapp.activity.activitymgr.vo.ActivityVo;

public class ActivityMgrServiceImpl implements ActivityMgrService {
    private ActivityDao activityDao;

    /**
     * @description 查询全部比赛
     * @version
     * @title
     * @author
     * @return List<ActivityVo>
     */
    public List<ActivityVo> queryAllActivity(ActivityVo activityVo) {
        ActivityInfo activityInfo = new ActivityInfo();
        BeanUtils.copyProperties(activityVo, activityInfo);
        List<ActivityInfo> activityInfoList = activityDao.queryAllActivity(activityInfo);
        List<ActivityVo> activityVoList = new ArrayList<ActivityVo>();
        ActivityVo backVo;
        for (int i = 0; i < activityInfoList.size(); i++) {
            backVo = new ActivityVo();
            BeanUtils.copyProperties(activityInfoList.get(i), backVo);
            activityVoList.add(backVo);
        }
        return activityVoList;
    }

    /**
     * @description 查询全部比赛
     * @version
     * @title
     * @author
     * @return List<ActivityVo>
     */
    public List<ActivityVo> queryAllNewActivity(ActivityVo activityVo) {
        ActivityInfo activityInfo = new ActivityInfo();
        BeanUtils.copyProperties(activityVo, activityInfo);
        List<ActivityInfo> activityInfoList = activityDao.queryAllNewActivity(activityInfo);
        List<ActivityVo> activityVoList = new ArrayList<ActivityVo>();
        ActivityVo backVo;
        for (int i = 0; i < activityInfoList.size(); i++) {
            backVo = new ActivityVo();
            BeanUtils.copyProperties(activityInfoList.get(i), backVo);
            activityVoList.add(backVo);
        }
        return activityVoList;
    }

    /**
     * @description 通过时间和区县查询比赛
     * @version
     * @title
     * @author
     * @return List<ActivityVo>
     */
    public List<ActivityVo> queryActivityByTimeArea(ActivityVo activityVo) {
        ActivityInfo activityInfo = new ActivityInfo();
        BeanUtils.copyProperties(activityVo, activityInfo);
        List<ActivityInfo> activityInfoList = activityDao.queryActivityByTimeArea(activityInfo);
        List<ActivityVo> activityVoList = new ArrayList<ActivityVo>();
        ActivityVo backVo;
        for (int i = 0; i < activityInfoList.size(); i++) {
            backVo = new ActivityVo();
            BeanUtils.copyProperties(activityInfoList.get(i), backVo);
            activityVoList.add(backVo);
        }
        return activityVoList;
    }

    /**
     * @description 查询历史比赛
     * @version
     * @title
     * @author
     * @return List<ActivityVo>
     */
    public List<ActivityVo> queryAllHistoryActivity(ActivityVo activityVo) {
        ActivityInfo activityInfo = new ActivityInfo();
        BeanUtils.copyProperties(activityVo, activityInfo);
        List<ActivityInfo> activityInfoList = activityDao.queryAllHistoryActivity(activityInfo);
        List<ActivityVo> activityVoList = new ArrayList<ActivityVo>();
        ActivityVo backVo;
        for (int i = 0; i < activityInfoList.size(); i++) {
            backVo = new ActivityVo();
            BeanUtils.copyProperties(activityInfoList.get(i), backVo);
            activityVoList.add(backVo);
        }
        return activityVoList;
    }

    /**
     * @description 查询比赛详细信息
     * @version
     * @title
     * @author
     * @return List<ActivityVo>
     */
    public ActivityVo queryActivityDetailInfo(ActivityVo activityVo) {
        ActivityInfo activityInfo = new ActivityInfo();
        BeanUtils.copyProperties(activityVo, activityInfo);
        ActivityInfo activityBackInfo = activityDao.queryActivityDetailInfo(activityInfo);
        ActivityVo activityBackVo = new ActivityVo();
        BeanUtils.copyProperties(activityBackInfo, activityBackVo);
        return activityBackVo;
    }

    /**
     * @description 新增比赛
     * @version
     * @title
     * @author
     * @return String
     */
    public String addActivity(ActivityVo activityVo) {
        String activityId = activityDao.queryActivityId();
        ActivityInfo activityInfo = new ActivityInfo();
        BeanUtils.copyProperties(activityVo, activityInfo);
        activityInfo.setActivity_id(activityId);
        activityDao.addActivity(activityInfo);
        return activityId;
    }

    /**
     * @description 修改比赛
     * @version
     * @title
     * @author
     * @return String
     */
    public String updateActivity(ActivityVo activityVo) {
        ActivityInfo activityInfo = new ActivityInfo();
        BeanUtils.copyProperties(activityVo, activityInfo);
        activityDao.updateActivity(activityInfo);
        return activityVo.getActivity_id();
    }

    /**
     * @description 删除比赛
     * @version
     * @title
     * @author
     * @return String
     */
    public String deleteActivity(ActivityVo activityVo) {
        ActivityInfo activityInfo = new ActivityInfo();
        activityDao.deleteActivity(activityInfo);
        return "0";
    }

    public ActivityDao getActivityDao() {
        return activityDao;
    }

    public void setActivityDao(ActivityDao activityDao) {
        this.activityDao = activityDao;
    }

}
