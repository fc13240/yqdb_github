package com.p2p.webapp.activity.activitymgr.dao;

import java.util.List;

import com.p2p.webapp.activity.activitymgr.entity.ActivityInfo;


public interface ActivityDao {
    /*
     * 查询全部比赛
     */
    public List<ActivityInfo> queryAllActivity(ActivityInfo activityInfo);
    /*
     * 通过时间和区县查询比赛
     */
    public List<ActivityInfo> queryActivityByTimeArea(ActivityInfo activityInfo);
    /*
     * 查询历史比赛
     */
    public List<ActivityInfo> queryAllHistoryActivity(ActivityInfo activityInfo);
    
    /*
     * 查询最新比赛
     */
    public List<ActivityInfo> queryAllNewActivity(ActivityInfo activityInfo);
    
    /*
     * 查询比赛详细信息
     */
    public ActivityInfo queryActivityDetailInfo(ActivityInfo activityInfo);
    
    /*
     * 新增比赛查询比赛ID
     */
    public String queryActivityId();
    
    /*
     * 新增比赛
     */
    public void addActivity(ActivityInfo activityInfo);
    
    /*
     * 修改比赛
     */
    public void updateActivity(ActivityInfo activityInfo);
    
    /*
     * 删除比赛
     */
    public void deleteActivity(ActivityInfo activityInfo);

}
