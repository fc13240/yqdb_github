package com.p2p.webapp.group.groupmgr.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.p2p.webapp.common.base.BaseAction;
import com.p2p.webapp.common.constant.Constant;
import com.p2p.webapp.group.groupmgr.service.GroupMgrService;
import com.p2p.webapp.group.groupmgr.vo.GroupVo;
import com.p2p.webapp.group.membermgr.service.MemberMgrService;
import com.p2p.webapp.group.membermgr.vo.MemberVo;

/**
 * @description 战队管理
 * @author
 * @date 2015-10-28 下午4:21:01
 */
public class GroupMgrAction extends BaseAction {
    private static final long serialVersionUID = 1L;

    // 写日志对象
    public static Logger logger = LoggerFactory.getLogger(GroupMgrAction.class);
    private GroupMgrService groupMgrService;
    private MemberMgrService memberMgrService;

    private MemberVo memberVo;
    private GroupVo groupVo;
    private List<GroupVo> groupVoList;
    private List<MemberVo> memberVoList;
    private int memberCount;
    private int activityCount;

    /**
     * @description 战队管理初始化页面
     * @version
     * @title
     * @author
     * @return
     */
    public String groupMgrInit() {
        GroupVo group = new GroupVo();
        groupVoList = groupMgrService.queryAllGroup(group);
        return "groupInit";
    }

    /**
     * @description 我的战队页面
     * @version
     * @title
     * @author
     * @return
     */
    public String myGroup() {
        groupVoList = groupMgrService.queryMyGroup(session.get("userid").toString());
        return "myGroup";
    }

    /**
     * @description 查询战队详细信息
     * @version
     * @title
     * @author
     * @return 查询战队详细信息页面
     */
    public String queryGroupDetailInfo() {
        groupVo = groupMgrService.queryGroupInfo(groupVo);
        memberCount = memberMgrService.queryMemberCount(groupVo.getGroup_id());
        activityCount = groupMgrService.queryGroupActCount(groupVo.getGroup_id());
        memberVo = new MemberVo();
        if (session.get("userid") != null) {
            memberVo.setGroup_id(groupVo.getGroup_id());
            memberVo.setUser_id(session.get("userid").toString());
            memberVo = memberMgrService.queryUserIdentity(memberVo);
        }
        return "groupDetailInfo";
    }

    /**
     * @description 添加新战队初始化页面
     * @version
     * @title
     * @author
     * @return 查询战队详细信息页面
     */
    public String addGroupInit() {
        groupVo = new GroupVo();
        groupVo.setGroup_id(groupMgrService.queryGroupId());
        return "addGroupInit";
    }

    /**
     * @description 添加新战队
     * @version
     * @title
     * @author
     * @return 查询战队详细信息页面
     */
    public String addGroup() {
        String groupId = groupMgrService.addGroup(groupVo);
        groupVo.setGroup_id(groupId);
        memberVo = new MemberVo();
        memberVo.setUser_id(session.get("userid").toString());
        memberVo.setGroup_id(groupId);
        memberVo.setBamembers_identity(Constant.MEMBER_TYPE_FOUNDER);
        memberMgrService.addMember(memberVo);
        return "groupDetailInfo";
    }

    /**
     * @description 修改战队初始化
     * @version
     * @title
     * @author
     * @return 修改战队初始化
     */
    public String updateGroup() {
        groupMgrService.updateGroup(groupVo);
        return "groupDetailInfo";
    }

    /**
     * @description 删除战队
     * @version
     * @title
     * @author
     * @return 操作成功返回 战队管理页面
     */
    public String deleteGroup() {
        groupMgrService.deleteGroup(groupVo);
        return "groupDetailInfo";
    }

    /**
     * @description 关注群
     * @version
     * @title
     * @author
     * @return
     */
    public void concernGroup() {
        Map<String, String> map = new HashMap<String, String>();
        String group_id = request.getParameter("group_id");
        memberVo = new MemberVo();
        memberVo.setGroup_id(group_id);
        memberVo.setUser_id(session.get("userid").toString());
        memberVo.setBamembers_identity(Constant.MEMBER_TYPE_INTEREST);
        String result = memberMgrService.addMember(memberVo);
        map.put("result", result);
        outJson(map);
    }
    
    /**
     * @description 取消关注
     * @version
     * @title
     * @author
     * @return
     */
    public void cancelConcern() {
        Map<String, String> map = new HashMap<String, String>();
        String group_id = request.getParameter("group_id");
        memberVo = new MemberVo();
        memberVo.setGroup_id(group_id);
        memberVo.setUser_id(session.get("userid").toString());        
        String result = memberMgrService.deleteMember(memberVo);
        map.put("result", result);
        outJson(map);
    }
    
    /**
     * @description 加入战队
     * @version
     * @title
     * @author
     * @return
     */
    public void joinGroup() {
        Map<String, String> map = new HashMap<String, String>();
        String group_id = request.getParameter("group_id");
        MemberVo queryVo = new MemberVo();
        queryVo.setGroup_id(group_id);
        queryVo.setUser_id(session.get("userid").toString());        
        memberVo = memberMgrService.queryUserIdentity(queryVo);
        String result = "-1";
        if(memberVo.getBamembers_identity()==null){
            queryVo.setBamembers_identity(Constant.MEMBER_TYPE_ORDINARY);
            result = memberMgrService.addMember(queryVo);
        }else{
            queryVo.setBamembers_identity(Constant.MEMBER_TYPE_ORDINARY);
            result = memberMgrService.updateMember(queryVo);
        }        
        map.put("result", result);
        outJson(map);
    }
    
    /**
     * @description 退出战队
     * @version
     * @title
     * @author  
    */
    public void quitGroup() {
        Map<String, String> map = new HashMap<String, String>();
        String group_id = request.getParameter("group_id");
        memberVo = new MemberVo();
        memberVo.setGroup_id(group_id);
        memberVo.setUser_id(session.get("userid").toString()); 
        String result = memberMgrService.deleteMember(memberVo);
        map.put("result", result);
        outJson(map);
    }

    public GroupMgrService getGroupMgrService() {
        return groupMgrService;
    }

    public void setGroupMgrService(GroupMgrService groupMgrService) {
        this.groupMgrService = groupMgrService;
    }

    public MemberMgrService getMemberMgrService() {
        return memberMgrService;
    }

    public void setMemberMgrService(MemberMgrService memberMgrService) {
        this.memberMgrService = memberMgrService;
    }

    public GroupVo getGroupVo() {
        return groupVo;
    }

    public void setGroupVo(GroupVo groupVo) {
        this.groupVo = groupVo;
    }

    public MemberVo getMemberVo() {
        return memberVo;
    }

    public void setMemberVo(MemberVo memberVo) {
        this.memberVo = memberVo;
    }

    public List<GroupVo> getGroupVoList() {
        return groupVoList;
    }

    public void setGroupVoList(List<GroupVo> groupVoList) {
        this.groupVoList = groupVoList;
    }

    public List<MemberVo> getMemberVoList() {
        return memberVoList;
    }

    public void setMemberVoList(List<MemberVo> memberVoList) {
        this.memberVoList = memberVoList;
    }

    public int getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(int memberCount) {
        this.memberCount = memberCount;
    }

    public int getActivityCount() {
        return activityCount;
    }

    public void setActivityCount(int activityCount) {
        this.activityCount = activityCount;
    }

}
