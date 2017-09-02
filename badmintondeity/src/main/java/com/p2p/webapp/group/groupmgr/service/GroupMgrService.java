package com.p2p.webapp.group.groupmgr.service;

import java.util.List;

import com.p2p.webapp.group.groupmgr.vo.GroupVo;

/**
 * @description 比赛管理service
 * @author
 * @date 2015-10-28 下午4:47:43
 */
public interface GroupMgrService {

    /**
     * @description 群管理页面
     * @version
     * @title
     * @author
     * @return 群管理页面
     */
    public List<GroupVo> queryAllGroup(GroupVo groupVo);

    /**
     * @description 通过userid查询群
     * @version
     * @title
     * @author
     * @param userid
     * @return
     */
    public List<GroupVo> queryMyGroup(String userid);
    
    /**
     * @description 通过userid查询管理的群
     * @version
     * @title
     * @author
     * @param userid
     * @return
     */
    public List<GroupVo> queryMyManageGroup(String userid);

    /**
     * @description 查询群详细信息
     * @version
     * @title
     * @author
     * @return 查询群详细信息页面
     */
    public GroupVo queryGroupInfo(GroupVo groupVo);

    /**
     * @description 新增用查询下一个群号
     * @version
     * @title
     * @author
     * @return 群号
     */
    public String queryGroupId();

    /**
     * @description 添加新群确定事件
     * @version
     * @title
     * @author
     * @return 操作成功返回 群管理页面
     */
    public String addGroup(GroupVo groupVo);

    /**
     * @description 修改群确定事件
     * @version
     * @title
     * @author
     * @return 操作成功返回 群管理页面
     */
    public String updateGroup(GroupVo groupVo);

    /**
     * @description 删除群确定事件
     * @version
     * @title
     * @author
     * @return 操作成功返回 群管理页面
     */
    public String deleteGroup(GroupVo groupVo);

    /**
     * @description 查询战队组织比赛次数
     * @version
     * @title
     * @author
     * @param group_id
     * @return
     */
    public int queryGroupActCount(String group_id);

}
