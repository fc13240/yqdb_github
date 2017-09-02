package com.p2p.webapp.group.membermgr.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.p2p.webapp.group.membermgr.dao.MemberDao;
import com.p2p.webapp.group.membermgr.entity.MemberInfo;
import com.p2p.webapp.group.membermgr.service.MemberMgrService;
import com.p2p.webapp.group.membermgr.vo.MemberVo;

public class MemberMgrServiceImpl implements MemberMgrService {
    private MemberDao memberDao;

    /**
     * @description 查询全部成员
     * @version
     * @title
     * @author
     * @see com.p2p.webapp.group.membermgr.service.MemberMgrService#queryAllMember(com.p2p.webapp.group.membermgr.vo.MemberVo)
     * @param memberVo
     * @return
     */
    public List<MemberVo> queryAllMember(MemberVo memberVo) {
        MemberInfo memberInfo = new MemberInfo();
        BeanUtils.copyProperties(memberVo, memberInfo);
        List<MemberInfo> memberInfoList = memberDao.queryAllMember(memberInfo);
        List<MemberVo> memberVoList = new ArrayList<MemberVo>();
        MemberVo backVo;
        for (int i = 0; i < memberInfoList.size(); i++) {
            backVo = new MemberVo();
            BeanUtils.copyProperties(memberInfoList.get(i), backVo);
            memberVoList.add(backVo);
        }
        return memberVoList;
    }

    /**
     * @description 查询用户身份
     * @version
     * @title
     * @author
     * @see com.p2p.webapp.group.membermgr.service.MemberMgrService#queryUserIdentity(com.p2p.webapp.group.membermgr.vo.MemberVo)
     * @param memberVo
     * @return
     */
    public MemberVo queryUserIdentity(MemberVo memberVo) {
        MemberInfo memberInfo = new MemberInfo();
        BeanUtils.copyProperties(memberVo, memberInfo);
        MemberInfo memberBackInfo = memberDao.queryUserIdentity(memberInfo);
        MemberVo memberBackVo = new MemberVo();
        if (memberBackInfo != null) {
            BeanUtils.copyProperties(memberBackInfo, memberBackVo);
        }
        return memberBackVo;
    }

    /**
     * @description 查询管理员
     * @version
     * @title
     * @author
     * @see com.p2p.webapp.group.membermgr.service.MemberMgrService#queryGroupAdmin(com.p2p.webapp.group.membermgr.vo.MemberVo)
     * @param memberVo
     * @return
     */
    public List<MemberVo> queryGroupAdmin(MemberVo memberVo) {
        MemberInfo memberInfo = new MemberInfo();
        BeanUtils.copyProperties(memberVo, memberInfo);
        List<MemberInfo> memberInfoList = memberDao.queryGroupAdmin(memberInfo);
        List<MemberVo> memberVoList = new ArrayList<MemberVo>();
        MemberVo backVo;
        for (int i = 0; i < memberInfoList.size(); i++) {
            backVo = new MemberVo();
            BeanUtils.copyProperties(memberInfoList.get(i), backVo);
            memberVoList.add(backVo);
        }
        return memberVoList;
    }

    /**
     * @description 新增成员
     * @version
     * @title
     * @author
     * @see com.p2p.webapp.group.membermgr.service.MemberMgrService#addMember(com.p2p.webapp.group.membermgr.vo.MemberVo)
     * @param memberVo
     */
    public String addMember(MemberVo memberVo) {
        try {
            MemberInfo memberInfo = new MemberInfo();
            BeanUtils.copyProperties(memberVo, memberInfo);
            memberDao.addMember(memberInfo);
            return "0";
        } catch (Exception e) {
            return "-1";
        }
    }

    /**
     * @description 修改成员
     * @version
     * @title
     * @author
     * @see com.p2p.webapp.group.membermgr.service.MemberMgrService#updateMember(com.p2p.webapp.group.membermgr.vo.MemberVo)
     * @param memberVo
     */
    public String updateMember(MemberVo memberVo) {
        try {
            MemberInfo memberInfo = new MemberInfo();
            BeanUtils.copyProperties(memberVo, memberInfo);
            memberDao.updateMember(memberInfo);
            return "0";
        } catch (Exception e) {
            return "-1";
        }

    }

    /**
     * @description 删除成员
     * @version
     * @title
     * @author
     * @see com.p2p.webapp.group.membermgr.service.MemberMgrService#deleteMember(com.p2p.webapp.group.membermgr.vo.MemberVo)
     * @param memberVo
     */
    public String deleteMember(MemberVo memberVo) {
        try {
            MemberInfo memberInfo = new MemberInfo();
            BeanUtils.copyProperties(memberVo, memberInfo);
            memberDao.deleteMember(memberInfo);
            return "0";
        } catch (Exception e) {
            return "-1";
        }

    }

    /**
     * @description 查询成员总数
     * @version
     * @title
     * @author
     * @see com.p2p.webapp.group.membermgr.service.MemberMgrService#queryMemberCount(java.lang.String)
     * @param group_id
     * @return
     */
    public int queryMemberCount(String group_id) {
        int count = memberDao.queryMemberCount(group_id);
        return count;
    }

    public MemberDao getMemberDao() {
        return memberDao;
    }

    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

}
