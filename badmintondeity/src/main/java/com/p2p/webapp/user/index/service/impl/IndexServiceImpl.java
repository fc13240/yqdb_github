package com.p2p.webapp.user.index.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.p2p.webapp.activity.activitymgr.entity.ActivityInfo;
import com.p2p.webapp.user.index.dao.IndexDao;
import com.p2p.webapp.user.index.service.IndexService;

public class IndexServiceImpl implements IndexService {

    private IndexDao indexDao;

    /**
     * @description 查询历史比赛
     * @version
     * @title
     * @author 
     * @see com.p2p.webapp.user.index.service.IndexService#queryHistoryActivity()
     * @return 
    */
    public List<ActivityInfo> queryHistoryActivity() {
        List<ActivityInfo> historyActivity = new ArrayList<ActivityInfo>();
        historyActivity = indexDao.queryHistoryActivity();
        return historyActivity;
    }

    /**
     * @description 查询最新比赛
     * @version
     * @title
     * @author 
     * @see com.p2p.webapp.user.index.service.IndexService#queryNewActivity()
     * @return 
    */
    public List<ActivityInfo> queryNewActivity() {
        List<ActivityInfo> newActivity = new ArrayList<ActivityInfo>();
        newActivity = indexDao.queryNewActivity();
        return newActivity;
    }

    public IndexDao getIndexDao() {
        return indexDao;
    }

    public void setIndexDao(IndexDao indexDao) {
        this.indexDao = indexDao;
    }

}
