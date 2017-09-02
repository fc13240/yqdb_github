package com.p2p.webapp.settle.settlemgr.service;

import java.util.List;

import com.p2p.webapp.activity.activitymgr.vo.ActivityVo;
import com.p2p.webapp.settle.settlemgr.vo.SettleVo;

/**
 * @description 比赛管理service
 * @author
 * @date 2015-10-28 下午4:47:43
 */
public interface SettleMgrService {

    /**
     * @description 查询已结算比赛
     * @version
     * @title
     * @author
     * @return
     */
    public List<SettleVo> querySettledActivity(SettleVo settleVo);

    /**
     * @description 查询未结算的比赛
     * @version
     * @title
     * @author
     * @param activityInfo
     * @return
     */
    public List<ActivityVo> queryNotsettledActivity(ActivityVo activityVo);
    
    /**
     * @description 查询比赛结算详细信息
     * @version
     * @title
     * @author
     * @return
     */
    public SettleVo querySettleDetailInfo(SettleVo settleVo);

    /**
     * @description 新增比赛结算记录
     * @version
     * @title
     * @author
     * @return
     */
    public String addSettle(SettleVo settleVo);

    /**
     * @description 修改结算信息
     * @version
     * @title
     * @author
     * @return
     */
    public String updateSettle(SettleVo settleVo);

}
