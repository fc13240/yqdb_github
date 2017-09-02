package com.p2p.webapp.group.membermgr.vo;

/**
 * @description 群成员vo
 * @author
 * @String 2015-10-28 下午4:10:52
 */
public class MemberVo {
    /**
     * @Fields group_id : 群ID
     */
    private String group_id;

    /**
     * @Fields user_id : 用户id
     */
    private String user_id;

    /**
     * @Fields user_name : 用户姓名
     */
    private String user_name;

    /**
     * @Fields bamembers_identity : 成员身份
     */
    private String bamembers_identity;

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getBamembers_identity() {
        return bamembers_identity;
    }

    public void setBamembers_identity(String bamembers_identity) {
        this.bamembers_identity = bamembers_identity;
    }

}