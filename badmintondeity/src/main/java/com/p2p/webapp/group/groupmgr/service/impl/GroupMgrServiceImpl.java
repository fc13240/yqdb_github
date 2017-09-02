package com.p2p.webapp.group.groupmgr.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.p2p.webapp.group.groupmgr.dao.GroupDao;
import com.p2p.webapp.group.groupmgr.entity.GroupInfo;
import com.p2p.webapp.group.groupmgr.service.GroupMgrService;
import com.p2p.webapp.group.groupmgr.vo.GroupVo;
import com.p2p.webapp.group.membermgr.dao.MemberDao;

public class GroupMgrServiceImpl implements GroupMgrService {
    private GroupDao groupDao;
    private MemberDao memberDao;

    /**
     * @description 查询全部群
     * @version
     * @title
     * @author
     * @see com.p2p.webapp.group.groupmgr.service.GroupMgrService#queryMyGroup(com.p2p.webapp.group.groupmgr.vo.GroupVo)
     * @param groupVo
     * @return
     */
    public List<GroupVo> queryAllGroup(GroupVo groupVo) {
        GroupInfo groupInfo = new GroupInfo();
        BeanUtils.copyProperties(groupVo, groupInfo);
        List<GroupInfo> groupInfoList = groupDao.queryAllGroup(groupInfo);
        List<GroupVo> groupVoList = new ArrayList<GroupVo>();
        GroupVo backVo;
        for (int i = 0; i < groupInfoList.size(); i++) {
            backVo = new GroupVo();
            BeanUtils.copyProperties(groupInfoList.get(i), backVo);
            backVo.setMember_count(memberDao.queryMemberCount(backVo.getGroup_id()));
            groupVoList.add(backVo);
        }
        return groupVoList;
    }

    /**
     * @description 查询我的群——通过userid查询群
     * @version
     * @title
     * @author
     * @see com.p2p.webapp.group.membermgr.service.MemberMgrService#queryMyGroup(java.lang.String)
     * @param userid
     * @return
     */
    public List<GroupVo> queryMyGroup(String userid) {
        List<GroupInfo> groupInfoList = groupDao.queryMyGroup(userid);
        List<GroupVo> groupVoList = new ArrayList<GroupVo>();
        GroupVo backVo;
        for (int i = 0; i < groupInfoList.size(); i++) {
            backVo = new GroupVo();
            BeanUtils.copyProperties(groupInfoList.get(i), backVo);
            backVo.setMember_count(memberDao.queryMemberCount(backVo.getGroup_id()));
            groupVoList.add(backVo);
        }
        return groupVoList;
    }

    /**
     * @description 通过userid查询我管理的群
     * @version
     * @title
     * @author
     * @see com.p2p.webapp.group.groupmgr.service.GroupMgrService#queryMyManageGroup(java.lang.String)
     * @param userid
     * @return
     */
    public List<GroupVo> queryMyManageGroup(String userid) {
        List<GroupInfo> groupInfoList = groupDao.queryMyManageGroup(userid);
        List<GroupVo> groupVoList = new ArrayList<GroupVo>();
        GroupVo backVo;
        for (int i = 0; i < groupInfoList.size(); i++) {
            backVo = new GroupVo();
            BeanUtils.copyProperties(groupInfoList.get(i), backVo);
            groupVoList.add(backVo);
        }
        return groupVoList;
    }

    /**
     * @description 查询群详细信息
     * @version
     * @title
     * @author
     * @see com.p2p.webapp.group.groupmgr.service.GroupMgrService#queryGroupInfo(com.p2p.webapp.group.groupmgr.vo.GroupVo)
     * @param groupVo
     * @return
     */
    public GroupVo queryGroupInfo(GroupVo groupVo) {
        GroupInfo groupInfo = new GroupInfo();
        BeanUtils.copyProperties(groupVo, groupInfo);
        GroupInfo groupBackInfo = groupDao.queryGroupInfo(groupInfo);
        GroupVo groupBackVo = new GroupVo();
        BeanUtils.copyProperties(groupBackInfo, groupBackVo);
        return groupBackVo;
    }

    /**
     * @description 新增群
     * @version
     * @title
     * @author
     * @see com.p2p.webapp.group.groupmgr.service.GroupMgrService#addGroup(com.p2p.webapp.group.groupmgr.vo.GroupVo)
     * @param groupVo
     * @return
     */
    public String addGroup(GroupVo groupVo) {
        String groupId = groupDao.queryGroupId();
        GroupInfo groupInfo = new GroupInfo();
        BeanUtils.copyProperties(groupVo, groupInfo);
        groupInfo.setGroup_id(groupId);
        groupDao.addGroup(groupInfo);
        return groupId;
    }

    /**
     * @description 修改群信息
     * @version
     * @title
     * @author
     * @see com.p2p.webapp.group.groupmgr.service.GroupMgrService#updateGroup(com.p2p.webapp.group.groupmgr.vo.GroupVo)
     * @param groupVo
     * @return
     */
    public String updateGroup(GroupVo groupVo) {
        GroupInfo groupInfo = new GroupInfo();
        BeanUtils.copyProperties(groupVo, groupInfo);
        groupDao.updateGroup(groupInfo);
        return groupVo.getGroup_id();
    }

    /**
     * @description 删除群
     * @version
     * @title
     * @author
     * @see com.p2p.webapp.group.groupmgr.service.GroupMgrService#deleteGroup(com.p2p.webapp.group.groupmgr.vo.GroupVo)
     * @param groupVo
     * @return
     */
    public String deleteGroup(GroupVo groupVo) {
        GroupInfo groupInfo = new GroupInfo();
        BeanUtils.copyProperties(groupVo, groupInfo);
        groupDao.deleteGroup(groupInfo);
        return "0";
    }

    /**
     * @description 查询战队组织比赛次数
     * @version
     * @title
     * @author
     * @see com.p2p.webapp.group.groupmgr.service.GroupMgrService#queryGroupActCount(java.lang.String)
     * @param group_id
     * @return
     */
    public int queryGroupActCount(String group_id) {
        int count = groupDao.queryGroupActCount(group_id);
        return count;
    }

    public String queryGroupId() {
        return groupDao.queryGroupId();
    }

    public GroupDao getGroupDao() {
        return groupDao;
    }

    public void setGroupDao(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    public MemberDao getMemberDao() {
        return memberDao;
    }

    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

}
