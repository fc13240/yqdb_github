package com.p2p.webapp.train.trainmgr.action;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.p2p.webapp.train.trainmgr.service.TrainMgrService;
import com.p2p.webapp.train.trainmgr.vo.TrainVo;
import com.p2p.webapp.common.base.BaseAction;
import com.p2p.webapp.common.constant.Constant;
import com.p2p.webapp.common.util.DateTimeFormatUtil;
import com.p2p.webapp.user.usercenter.service.UserCenterService;
import com.p2p.webapp.user.usercenter.vo.UserCreditVo;
import com.p2p.webapp.user.usercenter.vo.UserInfoVo;

/**
 * @description 训练管理
 * @author
 * @date 2017-08-30 下午4:21:01
 */
public class TrainMgrAction extends BaseAction {
    private static final long serialVersionUID = 1L;

    // 写日志对象
    public static Logger logger = LoggerFactory.getLogger(TrainMgrAction.class);
    private TrainMgrService trainMgrService;// 训练Service
    private UserCenterService userCenterService;// 用户中心Service
    
    private List<TrainVo> allTrain;// 全部训练list
    private List<TrainVo> newTrain;// 最新训练list
    private TrainVo trainVo;
    private UserInfoVo userInfoVo;

    private String totalNum;

    /**
     * @description 查询全部训练
     * @version
     * @title
     * @author
     * @return 全部训练页面
     */
    public String queryTrainInit() {
        String userid = (String) request.getSession().getAttribute(Constant.getSession_userid());
        userInfoVo = userCenterService.queryUserInfo(userid);
        logger.debug("训练页面初始化");
        return "trainInit";
    }


    public String trainRuning(){
        String userid = (String) request.getSession().getAttribute(Constant.getSession_userid());
        userInfoVo = userCenterService.queryUserInfo(userid);
        logger.debug("开始训练页面");
        return "trainRuning";
    }

    public String trainEnd(){
        String userid = (String) request.getSession().getAttribute(Constant.getSession_userid());
        userInfoVo = userCenterService.queryUserInfo(userid);
        logger.debug("训练结束页面");
        return "trainEnd";
    }

    public String trainShare(){
        String userid = (String) request.getSession().getAttribute(Constant.getSession_userid());
        userInfoVo = userCenterService.queryUserInfo(userid);
        logger.debug("训练分享页面");
        return "trainShare";
    }

    /**
     * @description 滑动栏查询训练
     * @version
     * @title
     * @author
     * @return 全部训练页面
     */
    public String queryAllActivity() {
        logger.debug("查询全部训练");
        trainVo = new TrainVo();
        String type = request.getParameter("type");
        String count = request.getParameter("count");
        trainVo.setCount(count);
        
        allTrain = trainMgrService.queryAllTrain(trainVo);
        
        return "scrollTrain";
    }

    /**
     * @description 查询训练详细信息
     * @version
     * @title
     * @author
     * @return 训练详细信息页面
     */
    public String queryTrainDetailInfo() {
        logger.debug("查询训练详细信息");
        
        String trainId = request.getParameter("trainId");
        
        if (trainId != null && trainId != "") {
            trainVo = new TrainVo();
            trainVo.setTrain_id(trainId);
        }
        
        // 查询训练详细信息
        trainVo = trainMgrService.queryTrainDetailInfo(trainVo);
        userInfoVo = new UserInfoVo();
        userInfoVo = userCenterService.queryUserInfo(trainVo.getUser_id());
       
        return "trainDetailInfo";
    }

    /**
     * @description 添加新训练初始化页面
     * @version
     * @title
     * @author
     * @return 训练详细信息页面
     */
    public String addTrainInit() {
        
        return "addTrainInit";
    }

    /**
     * @description 添加新训练
     * @version
     * @title
     * @author
     * @return 训练详细信息页面
     */
    public String addTrain() {
        String trainId = trainMgrService.addTrain(trainVo);
       
        trainVo.setTrain_id(trainId);
       
        return "queryTrainDetailInfo";
    }

    public TrainMgrService getTrainMgrService() {
        return trainMgrService;
    }

    public void setTrainMgrService(TrainMgrService trainMgrService) {
        this.trainMgrService = trainMgrService;
    }

    public UserCenterService getUserCenterService() {
        return userCenterService;
    }

    public void setUserCenterService(UserCenterService userCenterService) {
        this.userCenterService = userCenterService;
    }

    public List<TrainVo> getNewTrain() {
        return newTrain;
    }

    public void setNewTrain(List<TrainVo> newTrain) {
        this.newTrain = newTrain;
    }

    public TrainVo getTrainVo() {
        return trainVo;
    }

    public void setTrainVo(TrainVo trainVo) {
        this.trainVo = trainVo;
    }

    public List<TrainVo> getAllTrain() {
        return allTrain;
    }

    public void setAllTrain(List<TrainVo> allTrain) {
        this.allTrain = allTrain;
    }

    public UserInfoVo getuserInfoVo() {
        return userInfoVo;
    }

    public void setuserInfoVo(UserInfoVo userInfoVo) {
        this.userInfoVo = userInfoVo;
    }

    public String getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(String totalNum) {
        this.totalNum = totalNum;
    }

}
