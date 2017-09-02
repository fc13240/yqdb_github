package com.p2p.webapp.user.index.service;

import java.util.List;

import com.p2p.webapp.activity.activitymgr.entity.ActivityInfo;

public interface IndexService {

    /**
     * @description 查询历史比赛
     * @version
     * @title
     * @author
     * @return
     */
    public List<ActivityInfo> queryHistoryActivity();

    /**
     * @description 查询最新比赛
     * @version
     * @title
     * @author
     * @return
     */
    public List<ActivityInfo> queryNewActivity();
}
