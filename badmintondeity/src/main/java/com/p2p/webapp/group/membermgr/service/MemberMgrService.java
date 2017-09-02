package com.p2p.webapp.group.membermgr.service;

import java.util.List;

import com.p2p.webapp.group.membermgr.vo.MemberVo;

/**
 * @description 比赛管理service
 * @author
 * @date 2015-10-28 下午4:47:43
 */
public interface MemberMgrService {
    /**
     * @description 查询全部成员
     * @version
     * @title
     * @author
     * @return 群管理页面
     */
    public List<MemberVo> queryAllMember(MemberVo memberVo);
    
    /**
     * @description 查询管理员
     * @version
     * @title
     * @author 
     * @param memberVo
     * @return 
    */
    public List<MemberVo> queryGroupAdmin(MemberVo memberVo);
    
    /**
     * @description 查询用户身份
     * @version
     * @title
     * @author 
     * @param memberVo
     * @return 
    */
    public MemberVo queryUserIdentity(MemberVo memberVo);

    /**
     * @description 添加新群确定事件
     * @version
     * @title
     * @author
     * @return
     */
    public String addMember(MemberVo memberVo);

    /**
     * @description 修改群成员确定事件
     * @version
     * @title
     * @author
     * @return
     */
    public String updateMember(MemberVo memberVo);

    /**
     * @description 删除群成员确定事件
     * @version
     * @title
     * @author
     * @return 操作成功返回 群管理页面
     */
    public String deleteMember(MemberVo memberVo);

    /**
     * @description 查询成员数量
     * @version
     * @title
     * @author
     * @param group_id
     * @return
     */
    public int queryMemberCount(String group_id);
}