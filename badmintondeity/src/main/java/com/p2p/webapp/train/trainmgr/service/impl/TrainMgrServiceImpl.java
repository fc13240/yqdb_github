package com.p2p.webapp.train.trainmgr.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.p2p.webapp.train.trainmgr.dao.TrainDao;
import com.p2p.webapp.train.trainmgr.entity.TrainInfo;
import com.p2p.webapp.train.trainmgr.service.TrainMgrService;
import com.p2p.webapp.train.trainmgr.vo.TrainVo;

public class TrainMgrServiceImpl implements TrainMgrService {
    private TrainDao trainDao;

    /**
     * @description 查询全部训练
     * @version
     * @title
     * @author
     * @return List<TrainVo>
     */
    public List<TrainVo> queryAllTrain(TrainVo trainVo) {
        TrainInfo trainInfo = new TrainInfo();
        BeanUtils.copyProperties(trainVo, trainInfo);
        List<TrainInfo> trainInfoList = trainDao.queryAllTrain(trainInfo);
        List<TrainVo> trainVoList = new ArrayList<TrainVo>();
        TrainVo backVo;
        for (int i = 0; i < trainInfoList.size(); i++) {
            backVo = new TrainVo();
            BeanUtils.copyProperties(trainInfoList.get(i), backVo);
            trainVoList.add(backVo);
        }
        return trainVoList;
    }

    /**
     * @description 查询训练详细信息
     * @version
     * @title
     * @author
     * @return List<TrainVo>
     */
    public TrainVo queryTrainDetailInfo(TrainVo trainVo) {
        TrainInfo trainInfo = new TrainInfo();
        BeanUtils.copyProperties(trainVo, trainInfo);
        TrainInfo trainBackInfo = trainDao.queryTrainDetailInfo(trainInfo);
        TrainVo trainBackVo = new TrainVo();
        BeanUtils.copyProperties(trainBackInfo, trainBackVo);
        return trainBackVo;
    }

    /**
     * @description 新增比赛
     * @version
     * @title
     * @author
     * @return String
     */
    public String addTrain(TrainVo trainVo) {
        String trainId = trainDao.queryTrainId();
        TrainInfo trainInfo = new TrainInfo();
        BeanUtils.copyProperties(trainVo, trainInfo);
        trainInfo.setTrain_id(trainId);
        trainDao.addTrain(trainInfo);
        return trainId;
    }

    /**
     * @description 删除训练
     * @version
     * @title
     * @author
     * @return String
     */
    public String deleteTrain(TrainVo trainVo) {
        TrainInfo trainInfo = new TrainInfo();
        trainDao.deleteTrain(trainInfo);
        return "0";
    }

    public TrainDao getTrainDao() {
        return trainDao;
    }

    public void setTrainDao(TrainDao trainDao) {
        this.trainDao = trainDao;
    }

}
