package com.p2p.webapp.group.groupmgr.dao;

import java.util.List;

import com.p2p.webapp.group.groupmgr.entity.GroupInfo;

public interface GroupDao {

    /**
     * @description 群管理页面
     * @version
     * @title
     * @author
     * @return 群管理页面
     */
    public List<GroupInfo> queryAllGroup(GroupInfo groupInfo);

    /**
     * @description 通过userid查询群
     * @version
     * @title
     * @author
     * @param memberInfo
     * @return
     */
    public List<GroupInfo> queryMyGroup(String userid);

    /**
     * @description 通过userid查询管理的群
     * @version
     * @title
     * @author
     * @param memberInfo
     * @return
     */
    public List<GroupInfo> queryMyManageGroup(String userid);

    /**
     * @description 查询群详细信息
     * @version
     * @title
     * @author
     * @return 查询群详细信息页面
     */
    public GroupInfo queryGroupInfo(GroupInfo groupInfo);

    /**
     * @description 添加新群确定事件
     * @version
     * @title
     * @author
     * @return 操作成功返回 群管理页面
     */
    public void addGroup(GroupInfo groupInfo);

    /**
     * @description 修改群确定事件
     * @version
     * @title
     * @author
     * @return 操作成功返回 群管理页面
     */
    public void updateGroup(GroupInfo groupInfo);

    /**
     * @description 删除群确定事件
     * @version
     * @title
     * @author
     * @return 操作成功返回 群管理页面
     */
    public void deleteGroup(GroupInfo groupInfo);

    /**
     * @description 获取下一个GROUP_ID
     * @version
     * @title
     * @author
     * @return GROUP_ID
     */
    public String queryGroupId();

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
