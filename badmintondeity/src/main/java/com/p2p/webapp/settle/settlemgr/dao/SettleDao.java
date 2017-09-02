package com.p2p.webapp.settle.settlemgr.dao;

import java.util.List;

import com.p2p.webapp.activity.activitymgr.entity.ActivityInfo;
import com.p2p.webapp.settle.settlemgr.entity.SettleInfo;

public interface SettleDao {
    /*
     * 查询已结算比赛
     */
    public List<SettleInfo> querySettledActivity(SettleInfo settleInfo);

    /*
     * 查询未结算比赛
     */
    public List<ActivityInfo> queryNotsettledActivity(ActivityInfo activityInfo);
    
    /*
     * 查询结算详细信息
     */
    public SettleInfo querySettleDetailInfo(SettleInfo settleInfo);

    /*
     * 新增比赛结算记录查询下一个记录ID
     */
    public String querySettleId();

    /*
     * 新增比赛结算记录
     */
    public void addSettle(SettleInfo settleInfo);

    /*
     * 修改比赛结算记录
     */
    public void updateSettle(SettleInfo settleInfo);

}
