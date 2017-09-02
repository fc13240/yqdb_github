package com.p2p.webapp.user.usercenter.entity;

import java.util.Date;

/**
 * @description 用户积分总表
 * @author
 * @date 2015-12-31 上午9:03:14
 */
public class UserCreditInfo {

    /**
     * @Fields account_id : 账户id
     */
    private String account_id;
    /**
     * @Fields user_id : 用户id
     */
    private String user_id;

    /**
     * @Fields acc_status : 账户类型
     */
    private String acc_type = "C";

    /**
     * @Fields acc_status : 账户状态
     */
    private String acc_status;

    /**
     * @Fields acc_open_date : 开户时间
     */
    private Date acc_open_date;

    /**
     * @Fields acc_balance : 账户余额
     */
    private String acc_balance;

    /**
     * @Fields acc_freeze : 账户冻结金额
     */
    private String acc_freeze;

    /**
     * @Fields acc_enchash : 可提现金额
     */
    private String acc_enchash;

    /**
     * @Fields creat_date : 创建时间
     */
    private Date creat_date;

    /**
     * @Fields update_date : 更新时间
     */
    private Date update_date;

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAcc_type() {
        return acc_type;
    }

    public void setAcc_type(String acc_type) {
        this.acc_type = acc_type;
    }

    public String getAcc_status() {
        return acc_status;
    }

    public void setAcc_status(String acc_status) {
        this.acc_status = acc_status;
    }

    public Date getAcc_open_date() {
        return acc_open_date;
    }

    public void setAcc_open_date(Date acc_open_date) {
        this.acc_open_date = acc_open_date;
    }

    public String getAcc_balance() {
        return acc_balance;
    }

    public void setAcc_balance(String acc_balance) {
        this.acc_balance = acc_balance;
    }

    public String getAcc_freeze() {
        return acc_freeze;
    }

    public void setAcc_freeze(String acc_freeze) {
        this.acc_freeze = acc_freeze;
    }

    public String getAcc_enchash() {
        return acc_enchash;
    }

    public void setAcc_enchash(String acc_enchash) {
        this.acc_enchash = acc_enchash;
    }

    public Date getCreat_date() {
        return creat_date;
    }

    public void setCreat_date(Date creat_date) {
        this.creat_date = creat_date;
    }

    public Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }

}
