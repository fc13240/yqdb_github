package com.p2p.webapp.group.membermgr.dao;

import java.util.List;

import com.p2p.webapp.group.membermgr.entity.MemberInfo;

public interface MemberDao {
    /**
     * @description 查询全部成员
     * @version
     * @title
     * @author
     * @return 群管理页面
     */
    public List<MemberInfo> queryAllMember(MemberInfo memberInfo);
    
    /**
     * @description 查询管理员
     * @version
     * @title
     * @author
     * @return 群管理页面
     */
    public List<MemberInfo> queryGroupAdmin(MemberInfo memberInfo);
    
    /**
     * @description 查询用户身份
     * @version
     * @title
     * @author 
     * @param memberInfo
     * @return 
    */
    public MemberInfo queryUserIdentity(MemberInfo memberInfo);

    /**
     * @description 添加新群确定事件
     * @version
     * @title
     * @author
     * @return
     */
    public void addMember(MemberInfo memberInfo);

    /**
     * @description 修改群成员确定事件
     * @version
     * @title
     * @author
     * @return
     */
    public void updateMember(MemberInfo memberInfo);

    /**
     * @description 删除群成员确定事件
     * @version
     * @title
     * @author
     * @return 操作成功返回 群管理页面
     */
    public void deleteMember(MemberInfo memberInfo);

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
