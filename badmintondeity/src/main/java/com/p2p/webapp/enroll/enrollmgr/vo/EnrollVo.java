package com.p2p.webapp.enroll.enrollmgr.vo;

import java.util.Date;

/**
 * @description 报名vo
 * @author
 * @String 2015-10-28 下午4:10:52
 */
public class EnrollVo {
    /**
     * @Fields enroll_id : 主键报名id
     */
    private String enroll_id;

    /**
     * @Fields activity_id : 比赛ID
     */
    private String activity_id;

    /**
     * @Fields activity_name : 比赛名称
     */
    private String activity_name;

    /**
     * @Fields user_id : 用户id
     */
    private String user_id;

    /**
     * @Fields identity : 比赛人员身份
     */
    private String identity;

    /**
     * @Fields status : 状态
     */
    private String status;
    /**
     * @Fields number : 报名人数
     */
    private String number;
    /**
     * @Fields pay_type : 支付方式
     */
    private String pay_type;

    /**
     * @Fields acc_no : 支付账号
     */
    private String acc_no;

    /**
     * @Fields pay_type : 支付流水号
     */
    private String pay_id;

    /**
     * @Fields pay_date : 支付时间
     */
    private Date pay_date;

    /**
     * @Fields original_cost : 原始费用
     */
    private String original_cost;

    /**
     * @Fields cost : 实际费用
     */
    private String cost;

    /**
     * @Fields settle_id : 结算编号
     */
    private String settle_id;

    /**
     * @Fields user_name : 用户姓名
     */
    private String user_name;

    /**
     * @Fields sign_in : 签到标识
     */
    private String sign_in;

    public String getEnroll_id() {
        return enroll_id;
    }

    public void setEnroll_id(String enroll_id) {
        this.enroll_id = enroll_id;
    }

    public String getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(String activity_id) {
        this.activity_id = activity_id;
    }

    public String getActivity_name() {
        return activity_name;
    }

    public void setActivity_name(String activity_name) {
        this.activity_name = activity_name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPay_type() {
        return pay_type;
    }

    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
    }

    public String getAcc_no() {
        return acc_no;
    }

    public void setAcc_no(String acc_no) {
        this.acc_no = acc_no;
    }

    public String getPay_id() {
        return pay_id;
    }

    public void setPay_id(String pay_id) {
        this.pay_id = pay_id;
    }

    public Date getPay_date() {
        return pay_date;
    }

    public void setPay_date(Date pay_date) {
        this.pay_date = pay_date;
    }

    public String getOriginal_cost() {
        return original_cost;
    }

    public void setOriginal_cost(String original_cost) {
        this.original_cost = original_cost;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getSettle_id() {
        return settle_id;
    }

    public void setSettle_id(String settle_id) {
        this.settle_id = settle_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getSign_in() {
        return sign_in;
    }

    public void setSign_in(String sign_in) {
        this.sign_in = sign_in;
    }

}
