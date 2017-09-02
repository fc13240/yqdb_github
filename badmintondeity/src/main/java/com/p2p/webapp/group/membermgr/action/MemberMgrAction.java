package com.p2p.webapp.group.membermgr.action;

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
 * @description 群成员管理
 * @author
 * @date 2015-10-28 下午4:21:01
 */
public class MemberMgrAction extends BaseAction {
    private static final long serialVersionUID = 1L;

    // 写日志对象
    public static Logger logger = LoggerFactory.getLogger(MemberMgrAction.class);
    private MemberMgrService memberMgrService;
    private GroupMgrService groupMgrService;
    private GroupVo groupVo;
    private MemberVo memberVo;
    private List<MemberVo> memberVoList;

    /**
     * @description 群成员初始化页面
     * @version
     * @title
     * @author
     * @return
     */
    public String memberMgrInit() {
        String group_id = request.getParameter("group_id");
        MemberVo member = new MemberVo();
        member.setGroup_id(group_id);
        memberVoList = memberMgrService.queryAllMember(member);
        groupVo = new GroupVo();
        groupVo.setGroup_id(group_id);
        groupVo = groupMgrService.queryGroupInfo(groupVo);
        return "memberInit";
    }

    /**
     * @description 群成员初始化页面
     * @version
     * @title
     * @author
     * @return
     */
    public void queryGroupAdmin() {
        Map<String, Object> map = new HashMap<String, Object>();
        String group_id = request.getParameter("group_id");
        MemberVo member = new MemberVo();
        member.setGroup_id(group_id);
        memberVoList = memberMgrService.queryGroupAdmin(member);
        map.put("memberVoList", memberVoList);
        outJson(map);
    }

    /**
     * @description 查询群成员详细信息
     * @version
     * @title
     * @author
     * @return 成员信息
     */
    public String queryMemeberDetailInfo() {
        return "memberDetailInfo";
    }

    /**
     * @description 添加新成员
     * @version
     * @title
     * @author
     * @return 查询群详细信息页面
     */
    public String addMember() {
        memberMgrService.addMember(memberVo);
        return "memberInit";
    }

    /**
     * @description 修改群初始化
     * @version
     * @title
     * @author
     * @return 修改群初始化
     */
    public String updateMember() {
        memberMgrService.updateMember(memberVo);
        return "groupDetailInfo";
    }

    /**
     * @description 删除群成员
     * @version
     * @title
     * @author
     * @return 操作成功返回 群管理页面
     */
    public String deleteMember() {
        memberMgrService.deleteMember(memberVo);
        return "groupDetailInfo";
    }

    public MemberMgrService getMemberMgrService() {
        return memberMgrService;
    }

    public void setMemberMgrService(MemberMgrService memberMgrService) {
        this.memberMgrService = memberMgrService;
    }

    public GroupMgrService getGroupMgrService() {
        return groupMgrService;
    }

    public void setGroupMgrService(GroupMgrService groupMgrService) {
        this.groupMgrService = groupMgrService;
    }

    public MemberVo getMemberVo() {
        return memberVo;
    }

    public void setMemberVo(MemberVo memberVo) {
        this.memberVo = memberVo;
    }

    public GroupVo getGroupVo() {
        return groupVo;
    }

    public void setGroupVo(GroupVo groupVo) {
        this.groupVo = groupVo;
    }

    public List<MemberVo> getMemberVoList() {
        return memberVoList;
    }

    public void setMemberVoList(List<MemberVo> memberVoList) {
        this.memberVoList = memberVoList;
    }

}
