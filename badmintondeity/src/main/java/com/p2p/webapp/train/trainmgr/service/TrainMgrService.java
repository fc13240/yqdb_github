package com.p2p.webapp.train.trainmgr.service;

import java.util.List;

import com.p2p.webapp.train.trainmgr.vo.TrainVo;

/**
 * @description 训练管理service
 * @author
 * @date 2018-08-30 下午4:47:43
 */
public interface TrainMgrService {

    /**
     * @description 查询全部训练
     * @version
     * @title
     * @author
     * @return
     */
    public List<TrainVo> queryAllTrain(TrainVo trainVo);
    

    /**
     * @description 查询训练详细信息
     * @version
     * @title
     * @author
     * @return
     */
    public TrainVo queryTrainDetailInfo(TrainVo trainVo);

    /**
     * @description 新增训练
     * @version
     * @title
     * @author
     * @return
     */
    public String addTrain(TrainVo trainVo);


    /**
     * @description 删除训练
     * @version
     * @title
     * @author
     * @return
     */
    public String deleteTrain(TrainVo trainVo);

}
