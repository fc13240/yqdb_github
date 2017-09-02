package com.p2p.webapp.group.groupmgr.entity;

import java.util.Date;

public class GroupInfo {
    /**
     * @Fields group_id : 群ID
     */
    private String group_id;

    /**
     * @Fields group_name : 群名称
     */
    private String group_name;

    /**
     * @Fields group_logo : 群logo
     */
    private String group_logo;

    /**
     * @Fields group_type : 群类型
     */
    private String group_type;

    /**
     * @Fields bind_no : 群绑定编码
     */
    private String bind_no;

    /**
     * @Fields group_manage_user_id : 群管理员
     */
    private String group_manage_user_id;

    /**
     * @Fields remark : 群备注
     */
    private String remark;

    /**
     * @Fields create_date : 创建时间
     */
    private Date create_date;

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getGroup_logo() {
        return group_logo;
    }

    public void setGroup_logo(String group_logo) {
        this.group_logo = group_logo;
    }

    public String getGroup_type() {
        return group_type;
    }

    public void setGroup_type(String group_type) {
        this.group_type = group_type;
    }

    public String getBind_no() {
        return bind_no;
    }

    public void setBind_no(String bind_no) {
        this.bind_no = bind_no;
    }

    public String getGroup_manage_user_id() {
        return group_manage_user_id;
    }

    public void setGroup_manage_user_id(String group_manage_user_id) {
        this.group_manage_user_id = group_manage_user_id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }
}
