package com.p2p.webapp.user.usercenter.vo;

import java.util.Date;

/**
 * @description 用户积分明细表
 * @author
 * @date 2015-12-31 上午9:03:14
 */
public class UserCreditDetailVo {

    /**
     * @Fields account_id : 账户id
     */
    private String account_id;
    /**
     * @Fields user_id : 用户id
     */
    private String user_id;

    /**
     * @Fields bs_type : 账户操作类型
     */
    private String bs_type;

    /**
     * @Fields bs_no : 账户业务编号
     */
    private String bs_no;

    /**
     * @Fields acc_tran_type: 账户变化类型
     */
    private String acc_tran_type;

    /**
     * @Fields amnt : 金额
     */
    private String amnt;

    /**
     * @Fields enroll_id : 报名id
     */
    private String enroll_id;

    /**
     * @Fields ext2 : 扩展字段2
     */
    private String ext2;

    /**
     * @Fields ext3 : 扩展字段3
     */
    private String ext3;
    /**
     * @Fields create_date : 创建时间
     */
    private Date create_date;

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

    public String getBs_type() {
        return bs_type;
    }

    public void setBs_type(String bs_type) {
        this.bs_type = bs_type;
    }

    public String getBs_no() {
        return bs_no;
    }

    public void setBs_no(String bs_no) {
        this.bs_no = bs_no;
    }

    public String getAcc_tran_type() {
        return acc_tran_type;
    }

    public void setAcc_tran_type(String acc_tran_type) {
        this.acc_tran_type = acc_tran_type;
    }

    public String getAmnt() {
        return amnt;
    }

    public void setAmnt(String amnt) {
        this.amnt = amnt;
    }

    public String getEnroll_id() {
        return enroll_id;
    }

    public void setEnroll_id(String enroll_id) {
        this.enroll_id = enroll_id;
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2;
    }

    public String getExt3() {
        return ext3;
    }

    public void setExt3(String ext3) {
        this.ext3 = ext3;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }

}
