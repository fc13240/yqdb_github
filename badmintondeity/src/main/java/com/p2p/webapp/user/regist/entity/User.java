package com.p2p.webapp.user.regist.entity;

/**
 * 用户基本信息表
 * 
 * @author Administrator
 * 
 */
public class User {

    // 用户ID
    private String user_id;
    // 用户代码
    private String user_code;
    // 用户状态
    private String user_status;
    // 用户类型
    private String user_type;
    // 用户姓名
    private String user_name;
    // 用户头像
    private String user_headimgurl;
    // 用户昵称
    private String user_nickname;
    // 用户手机号
    private String phone="";
    // 用户邮箱
    private String mail="";
    // 证件类型
    private String certif_type="";
    // 证件号码
    private String certif_no="";
    // 创建时间
    private String create_date;
    // 更新时间
    private String update_date;

    // weixin open id
    private String weixin_id;

    public String getWeixin_id() {
        return weixin_id;
    }

    public void setWeixin_id(String weixin_id) {
        this.weixin_id = weixin_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String userId) {
        user_id = userId;
    }

    public String getUser_code() {
        return user_code;
    }

    public void setUser_code(String userCode) {
        user_code = userCode;
    }

    public String getUser_status() {
        return user_status;
    }

    public void setUser_status(String userStatus) {
        user_status = userStatus;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String userType) {
        user_type = userType;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String userName) {
        user_name = userName;
    }

    public String getUser_headimgurl() {
        return user_headimgurl;
    }

    public void setUser_headimgurl(String user_headimgurl) {
        this.user_headimgurl = user_headimgurl;
    }

    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCertif_type() {
        return certif_type;
    }

    public void setCertif_type(String certifType) {
        certif_type = certifType;
    }

    public String getCertif_no() {
        return certif_no;
    }

    public void setCertif_no(String certifNo) {
        certif_no = certifNo;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String createDate) {
        create_date = createDate;
    }

    public String getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(String updateDate) {
        update_date = updateDate;
    }

}
