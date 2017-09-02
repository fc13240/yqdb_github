package com.p2p.webapp.activity.activitymgr.service;

import java.util.List;

import com.p2p.webapp.activity.activitymgr.vo.ActivityVo;

/**
 * @description 比赛管理service
 * @author
 * @date 2015-10-28 下午4:47:43
 */
public interface ActivityMgrService {

    /**
     * @description 查询全部比赛
     * @version
     * @title
     * @author
     * @return
     */
    public List<ActivityVo> queryAllActivity(ActivityVo activityVo);
    
    /**
     * @description 通过时间和区县查询比赛
     * @version
     * @title
     * @author
     * @return
     */
    public List<ActivityVo> queryActivityByTimeArea(ActivityVo activityVo);

    /**
     * @description 查询最新比赛
     * @version
     * @title
     * @author
     * @return
     */
    public List<ActivityVo> queryAllNewActivity(ActivityVo activityVo);

    /**
     * @description 查询全部历史比赛
     * @version
     * @title
     * @author
     * @return
     */
    public List<ActivityVo> queryAllHistoryActivity(ActivityVo activityVo);

    /**
     * @description 查询比赛详细信息
     * @version
     * @title
     * @author
     * @return
     */
    public ActivityVo queryActivityDetailInfo(ActivityVo activityVo);

    /**
     * @description 新增比赛
     * @version
     * @title
     * @author
     * @return
     */
    public String addActivity(ActivityVo activityVo);

    /**
     * @description 修改比赛
     * @version
     * @title
     * @author
     * @return
     */
    public String updateActivity(ActivityVo activityVo);

    /**
     * @description 删除比赛
     * @version
     * @title
     * @author
     * @return
     */
    public String deleteActivity(ActivityVo activityVo);

}
