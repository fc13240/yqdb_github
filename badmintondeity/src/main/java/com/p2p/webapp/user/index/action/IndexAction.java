package com.p2p.webapp.user.index.action;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.p2p.webapp.activity.activitymgr.entity.ActivityInfo;
import com.p2p.webapp.common.base.BaseAction;
import com.p2p.webapp.user.index.service.IndexService;

public class IndexAction extends BaseAction {

    /**
     * 首页初始化跳转
     */
    private static final long serialVersionUID = 1L;

    // 写日志对象
    public static Logger logger = LoggerFactory.getLogger(IndexAction.class);
    private IndexService indexService;

    // 最新比赛list
    private List<ActivityInfo> newActivity;

    /**
     * @description 首页
     * @version
     * @title
     * @author
     * @return
     */
    public String init() {
        logger.debug("首页");
        return "pubmain";
    }

    /**
     * @description 查询最新比赛
     * @version
     * @title
     * @author
     * @return 最新比赛页面
     */
    public String queryNewActivity() {
        newActivity = indexService.queryNewActivity();
        return "newActivity";
    }

    public List<ActivityInfo> getNewActivity() {
        return newActivity;
    }

    public void setNewActivity(List<ActivityInfo> newActivity) {
        this.newActivity = newActivity;
    }

    public IndexService getIndexService() {
        return indexService;
    }

    public void setIndexService(IndexService indexService) {
        this.indexService = indexService;
    }

}
