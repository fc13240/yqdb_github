package com.p2p.webapp.user.index.dao;

import java.util.List;

import com.p2p.webapp.activity.activitymgr.entity.ActivityInfo;
import com.p2p.webapp.user.index.entity.IndexCount;

public interface IndexDao {

    public IndexCount queryUserAllProfit();

    public int queryUserCount();

    /*
     * 查询历史比赛
     */
    public List<ActivityInfo> queryHistoryActivity();

    /*
     * 查询最新比赛
     */
    public List<ActivityInfo> queryNewActivity();
}
