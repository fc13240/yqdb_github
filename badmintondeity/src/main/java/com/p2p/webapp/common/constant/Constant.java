package com.p2p.webapp.common.constant;

public class Constant {
    // 系统根路径
    public static String webContontRoot = "/p2pstock";
    // 默认公共菜单
    public static String menu_default = "INDEXPAGE,MYACOUNT,";
    // session 变量
    private static String session_secparam = "secParamStr";
    /* 平台类变量 */
    // 是否支持后付费 0 否 1是
    private static String pay_flag = "1";
    // 拦截器过滤，对以下命名空间的action不进行登录拦截
    private static String bizinterceptfilter = "demo,regist,login,common,ajax,weixin";
    // 拦截器过滤 ，对含有以下方法的一定要进行拦截和处理
    private static String bizinterceptfilters = "payEnroll";

    // 分页参数-每页条数
    private static String pagesize = "10";
    // 手机验证码内容模板
    private static String cptModulStr = "[一启动吧]您的验证码是： #x，5分钟内有效。 ";
    // 短信验证码有效时间
    public static int cptActiveTime = 300;
    // 短信验证码开关 0 真实 1 模拟 111111
    private static String cptnoflag = "0";
    // 短信发送开关 0 真实发送 1 模拟发送
    private static String cptflag = "0";
    // 短信发送地址
    private static String cptsendadress = "http://smssh1.253.com/msg/send/json";
    // 短信额度查询地址
    private static String cptqueryadress = "http://smssh1.253.com/msg/balance/json";
    // 短信账号
    private static String cptaccount = "N4276342";
    // 密码
    private static String cptpwd = "Bj999mvjfl8";
    // 产品编码
    private static String productid = "15";

    // session atrribute
    private static String session_userid = "userid";
    private static String session_username = "username";
    private static String session_phone = "phone";
    private static String session_usertyp = "usertyp";
    private static String session_mymenulist = "mymenu";
    private static String request_menutree = "menutree";
    private static String session_user_weixinid = "userweixinid";// open id

    /* 用户类变量 */
    // 用户基本信息表 用户状态 A 有效 N无效 C冻结
    private static String user_status_active = "A";
    private static String user_status_stop = "N";
    private static String user_status_cold = "C";
    private static String user_typ_custom = "G";
    private static String user_typ_manager = "A";
    // 用户账户类型
    // 现金
    private static String user_acctyp_cash = "C";
    // 账户状态
    // 正常
    private static String user_accstatus_act = "0";
    // 冻结
    private static String user_accstatus_cold = "1";
    // 注销
    private static String user_accstatus_off = "2";
    // 登录锁定失败次数
    private static int user_loginerrr_count = 5;
    // 用户操作状态 0成功 1失败
    private static String user_optstatus_succ = "0";
    private static String user_optstatus_err = "1";

    // 平台管理员账户
    private static String plat_admin_acount = "0";
    private static String plat_admin_userid = "0";

    // 比赛状态
    public static String ACTIVITY_STATUS_BASE = "0";// 未结算
    public static String ACTIVITY_STATUS_UNSETTLE = "1";// 未结算
    public static String ACTIVITY_STATUS_SETTLE = "2";// 已结算
    public static String ACTIVITY_STATUS_CANCEL = "3";// 取消

    // 报名状态
    public static String ENROLL_STATUS_BASE = "0";// 未支付
    public static String ENROLL_STATUS_PAY = "1";// 已支付
    public static String ENROLL_STATUS_REFUND = "2";// 退款

    // 支付方式
    public static String PAY_TYPE_ALIPAY = "0";// 支付宝
    public static String PAY_TYPE_WEIXIN = "1";// 微信
    public static String PAY_TYPE_OFFLINE = "2";// 线下

    public static String SETTLE_STATUS_BASE = "0";// 未结算
    public static String SETTLE_STATUS_OK = "1";// 结算完成

    // 自定义交易号
    public static String TRANSACTION_CODE_ENROLL = "A";// activity比赛报名
    public static String TRANSACTION_CODE_REFUND = "R";// refund退款
    public static String TRANSACTION_CODE_SETTLE = "S";// settle结算

    // 消息类型
    public static String MESSAGE_TYPE_SYSTEM = "S";// system系统消息
    public static String MESSAGE_TYPE_GROUP = "G";// group俱乐部消息
    public static String MESSAGE_TYPE_PERSON = "P";// personal个人消息

    // 自定义交易号
    public static String MESSAGE_STATUS_UNREAD = "0";// 消息未读
    public static String MESSAGE_STATUS_READED = "1";// 消息已读

    // 群成员类型
    public static String MEMBER_TYPE_FOUNDER = "F";// funder创立者
    public static String MEMBER_TYPE_MANAGE = "M";// manager管理员
    public static String MEMBER_TYPE_ORDINARY = "O";// ordinary普通成员
    public static String MEMBER_TYPE_INTEREST = "I";// interest关注者

    // 账户变化类型
    public static String ACC_TRAN_TYPE_INCREASE = "I";// increase 账户增加
    public static String ACC_TRAN_TYPE_DECREASE = "D";// decrease 账户减少
    public static String ACC_TRAN_TYPE_FREEZE = "F";// freeze 冻结

    // 账户操作用途
    public static String BS_TYPE_ACTIVITY = "A";// activity 账户用于报名
    public static String BS_TYPE_SYSTEM = "S";// system 账户用于系统

    // eoems@sina.com add lizhenzhong weixin
    public static String APPID = "wx315fa51c9bb4b39e";
    public static String APPSECRET = "60f73066a802c386ae26c4dad31800f6";
    public static String PARTNERKEY = "wxef18a13927bc52cdmoorecubemoore";// 商户的支付的密钥
    public static String REDIRECT_URI = "http://yiqidongba.cn/p2pstock/weixin/oauth2Base_weixinAction.action";
    /*
     * public static String REDIRECT_URI_RELATED =
     * "www.shuttler.cn/p2pstock/weixin/callbackUserInfo_weiXinAction.action";
     * public static String REDIRECT_URI_PAYNOTIC =
     * "www.shuttler.cn/p2pstock/weixin/noticePayResultEnroll_enrollMgrAction.action"
     * ;
     */
    public static String SCOPE = "snsapi_userinfo";// 需要用户认证，返回基本信息包括头像等
    public static String BASE_SCOPE = "snsapi_base";// 只有openid
    public static String TOKEN = "yiqidongba";// 公众平台上面自己填写的Token
    public static String ENCODINGAESKEY = "Dah7pico818Ooxke4m178XB9m7q4nCYfe1JAWAFzcAr";// 公众平台上面自己填写的43位EncodingAESKey

    // 微信退款
    public static String REFUND_URI = "https://api.mch.weixin.qq.com/secapi/pay/refund";

    // eoems add 20151122 主要是用于cookie 的命名和判断
    public static String USER_COOKIE = "badmintondeity";
    public static String USER_COOKIE_USERID = "userid";
    public static String USER_COOKIE_PHONE = "phone";
    public final static String strAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token";
    public final static String strGetUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo";

    // eoems add
    public static String WEIXIN_OPENID = "weixin_openid";
    /**
     * 请求消息类型：事件
     */
    public static final String REQ_MESSAGE_TYPE_EVENT = "event";

    /**
     * 事件类型：subscribe(关注)
     */
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";

    /**
     * 事件类型：unsubscribe(取消关注)
     */
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";

    /**
     * 事件类型：CLICK(自定义菜单点击事件)
     */
    public static final String EVENT_TYPE_CLICK = "CLICK";

    public static final String WEIXIN_USER_PAYATENTION_YES = "1";
    public static final String WEIXIN_USER_PAYATENTION_NO = "0";

    public static String getCptModulStr() {
        return cptModulStr;
    }

    public static String getCptsendadress() {
        return cptsendadress;
    }

    public static String getCptqueryadress() {
        return cptqueryadress;
    }

    public static String getCptaccount() {
        return cptaccount;
    }

    public static String getCptpwd() {
        return cptpwd;
    }

    public static String getProductid() {
        return productid;
    }

    public static String getCptflag() {
        return cptflag;
    }

    public static String getUser_status_active() {
        return user_status_active;
    }

    public static String getUser_status_stop() {
        return user_status_stop;
    }

    public static String getUser_status_cold() {
        return user_status_cold;
    }

    public static String getUser_typ_custom() {
        return user_typ_custom;
    }

    public static String getUser_typ_manager() {
        return user_typ_manager;
    }

    public static int getUser_loginerrr_count() {
        return user_loginerrr_count;
    }

    public static String getUser_optstatus_succ() {
        return user_optstatus_succ;
    }

    public static String getUser_optstatus_err() {
        return user_optstatus_err;
    }

    public static String getBizinterceptfilter() {
        return bizinterceptfilter;
    }

    public static String getSession_userid() {
        return session_userid;
    }

    public static String getSession_username() {
        return session_username;
    }

    public static String getSession_usertyp() {
        return session_usertyp;
    }

    public static String getSession_phone() {
        return session_phone;
    }

    public static String getPagesize() {
        return pagesize;
    }

    public static String getSession_secparam() {
        return session_secparam;
    }

    public static String getUser_acctyp_cash() {
        return user_acctyp_cash;
    }

    public static String getUser_accstatus_act() {
        return user_accstatus_act;
    }

    public static String getUser_accstatus_cold() {
        return user_accstatus_cold;
    }

    public static String getUser_accstatus_off() {
        return user_accstatus_off;
    }

    public static String getPay_flag() {
        return pay_flag;
    }

    public static String getPlat_admin_userid() {
        return plat_admin_userid;
    }

    public static String getCptnoflag() {
        return cptnoflag;
    }

    public static String getSession_mymenulist() {
        return session_mymenulist;
    }

    public static String getRequest_menutree() {
        return request_menutree;
    }

    public static String getMenu_default() {
        return menu_default;
    }

    public static String getSession_user_weixinid() {
        return session_user_weixinid;
    }

    public static String getBizinterceptfilters() {
        return bizinterceptfilters;
    }

    public static void setBizinterceptfilters(String bizinterceptfilters) {
        Constant.bizinterceptfilters = bizinterceptfilters;
    }

    public static String getPlat_admin_acount() {
        return plat_admin_acount;
    }

}
