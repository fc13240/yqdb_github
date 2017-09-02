package com.p2p.webapp.activity.activitymgr.dto;

import java.util.Date;

public class ActivityInfo {

    /**
     * @Fields activity_id : 比赛ID
     */
    private String activity_id;

    /**
     * @Fields group_id : 比赛所属群ID
     */
    private String group_id;

    /**
     * @Fields activity_name : 比赛名称
     */
    private String activity_name;

    /**
     * @Fields activity_status : 比赛状态
     */
    private String activity_status;

    /**
     * @Fields activity_type : 比赛类型
     */
    private String activity_type;

    /**
     * @Fields user_id : 比赛发起用户
     */
    private String user_id;

    /**
     * 比赛报名条件ID
     */
    private String enroll_roll_id;

    /**
     * @Fields activity_begin : 比赛开始时间
     */
    private Date activity_begin;

    /**
     * @Fields activity_end : 比赛结束时间
     */
    private Date activity_end;

    /**
     * @Fields activity_addr : 比赛地址点
     */
    private String activity_addr;
    /**
     * @Fields manage_id : 比赛管理员
     */
    private String manage_id;
    /**
     * @Fields site_name : 地点名称
     */
    private String site_name;

    /** 
    * @Fields group_name : 俱乐部名称
    */ 
    private String group_name;
    
    /** 
    * @Fields site_addr_district : 区
    */ 
    private String site_addr_district;
    private String count;

    public String getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(String activity_id) {
        this.activity_id = activity_id;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getActivity_name() {
        return activity_name;
    }

    public void setActivity_name(String activity_name) {
        this.activity_name = activity_name;
    }

    public String getActivity_status() {
        return activity_status;
    }

    public void setActivity_status(String activity_status) {
        this.activity_status = activity_status;
    }

    public String getActivity_type() {
        return activity_type;
    }

    public void setActivity_type(String activity_type) {
        this.activity_type = activity_type;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getEnroll_roll_id() {
        return enroll_roll_id;
    }

    public void setEnroll_roll_id(String enroll_roll_id) {
        this.enroll_roll_id = enroll_roll_id;
    }

    public String getActivity_addr() {
        return activity_addr;
    }

    public void setActivity_addr(String activity_addr) {
        this.activity_addr = activity_addr;
    }

    public String getManage_id() {
        return manage_id;
    }

    public void setManage_id(String manage_id) {
        this.manage_id = manage_id;
    }

    public Date getActivity_begin() {
        return activity_begin;
    }

    public void setActivity_begin(Date activity_begin) {
        this.activity_begin = activity_begin;
    }

    public Date getActivity_end() {
        return activity_end;
    }

    public void setActivity_end(Date activity_end) {
        this.activity_end = activity_end;
    }

    public String getSite_name() {
        return site_name;
    }

    public void setSite_name(String site_name) {
        this.site_name = site_name;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getSite_addr_district() {
        return site_addr_district;
    }

    public void setSite_addr_district(String site_addr_district) {
        this.site_addr_district = site_addr_district;
    }

}
