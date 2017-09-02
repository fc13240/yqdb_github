package com.p2p.webapp.train.trainmgr.dao;

import java.util.List;

import com.p2p.webapp.train.trainmgr.entity.TrainInfo;


public interface TrainDao {
    /*
     * 查询全部训练
     */
    public List<TrainInfo> queryAllTrain(TrainInfo trainInfo);
   
    
    /*
     * 查询训练详细信息
     */
    public TrainInfo queryTrainDetailInfo(TrainInfo trainInfo);
    
    /*
     * 新增训练查询训练ID
     */
    public String queryTrainId();
    
    /*
     * 新增训练
     */
    public void addTrain(TrainInfo trainInfo);
    
 
    /*
     * 删除训练
     */
    public void deleteTrain(TrainInfo trainInfo);

}
