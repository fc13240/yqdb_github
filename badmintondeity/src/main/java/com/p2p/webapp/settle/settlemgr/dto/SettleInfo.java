package com.p2p.webapp.settle.settlemgr.dto;

import java.util.Date;

public class SettleInfo {

    /**
     * @Fields settle_id: 结算编号
     */
    private String settle_id;

    /**
     * @Fields settle_status : 结算状态
     */
    private String settle_status;

    /**
     * @Fields activity_id : 比赛ID
     */
    private String activity_id;

    /**
     * @Fields cost : 费用
     */
    private String cost;

    /**
     * @Fields user_id : 收款用户
     */
    private String user_id;

    /**
     * @Fields pay_type : 收款账户类型
     */
    private String pay_type;

    /**
     * @Fields pay_type : 收款账户ID
     */
    private String pay_no;

    /**
     * @Fields activity_name : 比赛名称
     */
    private String activity_name;

    /**
     * @Fields update_date : 更新日期
     */
    private Date update_date;

    public String getSettle_id() {
        return settle_id;
    }

    public void setSettle_id(String settle_id) {
        this.settle_id = settle_id;
    }

    public String getSettle_status() {
        return settle_status;
    }

    public void setSettle_status(String settle_status) {
        this.settle_status = settle_status;
    }

    public String getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(String activity_id) {
        this.activity_id = activity_id;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPay_type() {
        return pay_type;
    }

    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
    }

    public String getPay_no() {
        return pay_no;
    }

    public void setPay_no(String pay_no) {
        this.pay_no = pay_no;
    }

    public String getActivity_name() {
        return activity_name;
    }

    public void setActivity_name(String activity_name) {
        this.activity_name = activity_name;
    }

    public Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }

}
