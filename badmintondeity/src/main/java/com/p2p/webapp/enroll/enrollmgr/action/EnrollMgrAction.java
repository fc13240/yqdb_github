package com.p2p.webapp.enroll.enrollmgr.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.p2p.webapp.activity.activitymgr.action.ActivityMgrAction;
import com.p2p.webapp.activity.activitymgr.service.ActivityMgrService;
import com.p2p.webapp.activity.activitymgr.vo.ActivityVo;
import com.p2p.webapp.common.base.BaseAction;
import com.p2p.webapp.common.constant.Constant;
import com.p2p.webapp.common.util.DateTimeFormatUtil;
import com.p2p.webapp.common.util.UUIDHexGeneratorEx;
import com.p2p.webapp.enroll.enrollmgr.service.EnrollMgrService;
import com.p2p.webapp.enroll.enrollmgr.vo.EnrollVo;
import com.p2p.webapp.settle.settlemgr.service.SettleMgrService;
import com.p2p.webapp.settle.settlemgr.vo.SettleVo;
import com.p2p.webapp.site.sitemgr.service.SiteMgrService;
import com.p2p.webapp.site.sitemgr.vo.SiteVo;
import com.p2p.webapp.user.usercenter.service.UserCenterService;
import com.p2p.webapp.user.usercenter.vo.UserCreditDetailVo;
import com.p2p.webapp.user.usercenter.vo.UserCreditVo;

/**
 * @description 报名管理
 * @author
 * @date 2015-10-28 下午4:21:01
 */
public class EnrollMgrAction extends BaseAction {
    // 写日志对象
    public static Logger logger = LoggerFactory.getLogger(ActivityMgrAction.class);
    private static final long serialVersionUID = 1L;
    private EnrollMgrService enrollMgrService;
    private ActivityMgrService activityMgrService;
    private UserCenterService usercService;
    private SettleMgrService settleMgrService;
    private SiteMgrService siteMgrService;
    private EnrollVo enrollVo;
    private SettleVo settleVo;
    private ActivityVo activityVo;
    private UserCreditDetailVo userCreditDetailVo;
    private List<EnrollVo> enrollVoList;
    private List<EnrollVo> payedList;
    private List<EnrollVo> unpayList;
    private List<EnrollVo> refundList;

    private String total_fee;

    private boolean showSettleButton = false;// 显示结算按钮

    /**
     * @description 查询全部报名信息
     * @version
     * @title
     * @author
     */
    public String queryAllEnroll() {
        String activityId = request.getParameter("activityId");
        if (activityId != null && activityId != "") {
            enrollVo = new EnrollVo();
            enrollVo.setActivity_id(activityId);
        }
        logger.debug("查询报名信息" + enrollVo.getActivity_id());
        enrollVoList = enrollMgrService.queryAllEnroll(enrollVo);
        activityVo = new ActivityVo();
        activityVo.setActivity_id(enrollVo.getActivity_id());
        activityVo = activityMgrService.queryActivityDetailInfo(activityVo);
        total_fee = enrollMgrService.querySumMoney(enrollVo);
        if (total_fee == null) {
            total_fee = "0";
        }
        if (activityVo.getActivity_status().equals("1")) {
            showSettleButton = true;
        } else {
            showSettleButton = false;
        }

        return "enrollInit";
    }

    /**
     * @description 查询报名详细信息
     * @version
     * @title
     * @author
     */
    public String queryEnrollDetailInfo() {
        logger.debug("查询详细报名信息");
        enrollVo = enrollMgrService.queryEnrollDetailInfo(enrollVo);
        userCreditDetailVo = new UserCreditDetailVo();
        userCreditDetailVo = usercService.queryCreditDetailByEnrollId(enrollVo.getEnroll_id());
        activityVo = new ActivityVo();
        activityVo.setActivity_id(enrollVo.getActivity_id());
        activityVo = activityMgrService.queryActivityDetailInfo(activityVo);        
        return "myOrder";
    }

    /**
     * @description 查询我的报名信息
     * @version
     * @title
     * @author
     */
    public String queryMyEnroll() {
        logger.debug("查询我的报名信息");
        EnrollVo queryVo = new EnrollVo();
        // 查询全部报名订单
        queryVo.setUser_id(session.get("userid").toString());
        enrollVoList = enrollMgrService.queryMyEnroll(queryVo);

        // 查询待支付报名订单
        queryVo.setStatus(Constant.ENROLL_STATUS_BASE);
        unpayList = enrollMgrService.queryMyEnroll(queryVo);
        // 查询已支付报名订单
        queryVo.setStatus(Constant.ENROLL_STATUS_PAY);
        payedList = enrollMgrService.queryMyEnroll(queryVo);
        // 查询退款报名订单
        queryVo.setStatus(Constant.ENROLL_STATUS_REFUND);
        refundList = enrollMgrService.queryMyEnroll(queryVo);
        return "myEnrollInit";
    }

    /**
     * @description 添加报名记录
     * @version
     * @title
     * @author
     */
    public String addEnroll() {
        logger.debug("报名参加比赛");
        // 创建订单
        Integer intHao = Integer.parseInt(enrollVo.getActivity_id());
        String strHao = intHao.toString();
        while (strHao.length() < 11) {
            strHao = "0" + strHao;
        }
        enrollVo.setUser_id(session.get("userid").toString());
        // 订单号格式 A + 11位比赛号 + 日期时间随机17位
        String out_trade_no = Constant.TRANSACTION_CODE_ENROLL + strHao + UUIDHexGeneratorEx.gen17();
        enrollVo.setPay_id(out_trade_no);// 商户自定交易号
        String enroll_id = enrollMgrService.addEnroll(enrollVo);
        enrollVo.setEnroll_id(enroll_id);
        enrollVo = enrollMgrService.queryEnrollDetailInfo(enrollVo);

        if (!userCreditDetailVo.getAmnt().equals("0")) {
            // 报名使用的积分暂时冻结
            UserCreditVo userCreditVo = new UserCreditVo();
            userCreditVo.setUser_id(session.get("userid").toString());
            userCreditVo = usercService.queryUserCredit(userCreditVo);
            // 总账户
            int acc_freeze = Integer.parseInt(userCreditVo.getAcc_freeze());// 获取原冻结数量
            int acc_balance = Integer.parseInt(userCreditVo.getAcc_balance());// 获取原成绩数量
            logger.debug("原成绩数量为  =====" + acc_balance + "    原冻结数量为  =====" + acc_freeze);
            int amnt = Integer.parseInt(userCreditDetailVo.getAmnt());
            int new_freeze = acc_freeze + amnt;
            userCreditVo.setAcc_freeze(String.valueOf(new_freeze));
            usercService.updateUserCredit(userCreditVo);

            // 账户明细
            userCreditDetailVo.setAccount_id(userCreditVo.getAccount_id());
            userCreditDetailVo.setUser_id(enrollVo.getUser_id());
            userCreditDetailVo.setBs_no(enrollVo.getPay_id());
            userCreditDetailVo.setAmnt(userCreditDetailVo.getAmnt());
            userCreditDetailVo.setAcc_tran_type(Constant.ACC_TRAN_TYPE_FREEZE);
            userCreditDetailVo.setBs_type(Constant.BS_TYPE_ACTIVITY);
            userCreditDetailVo.setEnroll_id(enrollVo.getEnroll_id());
            usercService.addUserCreditDetail(userCreditDetailVo);
        }

        return "myOrder";
    }

    /**
     * @description 修改报名信息
     * @version
     * @title
     * @author
     */
    public String updateEnroll() {
        logger.debug("修改报名信息");
        enrollMgrService.updateEnroll(enrollVo);
        queryAllEnroll();
        return "enrollInit";
    }

    /**
     * @description 取消报名
     * @version
     * @title
     * @author
     */
    public String cancelEnroll() {
        enrollVo.setUser_id(session.get("userid").toString());
        logger.debug("取消报名");
        enrollMgrService.cancelEnroll(enrollVo);
        // 删除enroll_id对应的积分变化记录

        userCreditDetailVo = new UserCreditDetailVo();
        userCreditDetailVo = usercService.queryCreditDetailByEnrollId(enrollVo.getEnroll_id());
        int amnt = Integer.parseInt(userCreditDetailVo.getAmnt());
        // 更新积分记录
        UserCreditVo userCreditVo = new UserCreditVo();
        userCreditVo.setUser_id(session.get("userid").toString());
        userCreditVo = usercService.queryUserCredit(userCreditVo);
        int acc_freeze = Integer.parseInt(userCreditVo.getAcc_freeze());// 获取原冻结数量
        int acc_balance = Integer.parseInt(userCreditVo.getAcc_balance());// 获取原成绩数量
        logger.debug("原成绩数量为  =====" + acc_balance + "    原冻结数量为  =====" + acc_freeze);
        int new_freeze = acc_freeze - amnt;
        // 冻结数量扣除本次使用数量
        userCreditVo.setAcc_freeze(String.valueOf(new_freeze));
        usercService.updateUserCredit(userCreditVo);

        // 修改用户积分
        userCreditDetailVo.setAcc_tran_type("");
        usercService.updateCreditDetailByEnrollId(userCreditDetailVo);

        return "toMyEnroll";
    }

    /**
     * @description 申请退费
     * @version
     * @title
     * @author
     */
    public void refundCheck() {
        Map<String, String> map = new HashMap<String, String>();
        logger.debug("退费");
        try {
            enrollVo = enrollMgrService.queryEnrollDetailInfo(enrollVo);
            activityVo = new ActivityVo();
            activityVo.setActivity_id(enrollVo.getActivity_id());
            activityVo = activityMgrService.queryActivityDetailInfo(activityVo);
            Date nowtime = DateTimeFormatUtil.getCurrentDate();
            // 设定为比赛开始前2小时为退费截至日期
            Date deadline = DateTimeFormatUtil.addTimeBySeconds(activityVo.getActivity_begin(), -2 * 60 * 60);
            if (DateTimeFormatUtil.compareByDate(nowtime, deadline) == "1") {
                map.put("result", "0");
            } else {
                map.put("result", "-1");
            }
            outJson(map);
        } catch (Exception e) {
            logger.debug(e.toString());
            map.put("result", "-1");
            outJson(map);
        }
    }

    /**
     * @description 比赛签到
     * @version
     * @title
     * @author
     */
    public void signIn() {
        Map<String, String> map = new HashMap<String, String>();
        try {
            String activity_id = enrollVo.getActivity_id();
            activityVo = new ActivityVo();
            activityVo.setActivity_id(activity_id);
            activityVo = activityMgrService.queryActivityDetailInfo(activityVo);
            SiteVo siteVo = new SiteVo();
            siteVo.setSite_addr_id(activityVo.getActivity_addr());
            siteVo = siteMgrService.querySiteDetailInfo(siteVo);
            String slng = siteVo.getSite_lng();
            String slat = siteVo.getSite_lat();
            String mylng = request.getParameter("lng");
            String mylat = request.getParameter("lat");
            if (calDistance(slng, slat, mylng, mylat)) {
                enrollMgrService.signIn(enrollVo.getEnroll_id());
                map.put("result", "0");
                outJson(map);
            } else {
                map.put("result", "-1");
                outJson(map);
            }

        } catch (Exception e) {
            map.put("result", "-1");
            outJson(map);
        }

    }

    /**
     * @description 计算两坐标点间距离
     * @version
     * @title
     * @author
     * @return
     */
    public boolean calDistance(String slng, String slat, String mylng, String mylat) {
        logger.info("地点坐标为  " + slng + "  ,  " + slat);
        logger.info("我的坐标为  " + mylng + "  ,  " + mylat);

        double pk = 180 / 3.14169;
        double a1 = new Double(slat).doubleValue() / pk;
        double a2 = new Double(slng).doubleValue() / pk;
        double b1 = new Double(mylat).doubleValue() / pk;
        double b2 = new Double(mylng).doubleValue() / pk;
        double t1 = Math.cos(a1) * Math.cos(a2) * Math.cos(b1) * Math.cos(b2);
        double t2 = Math.cos(a1) * Math.sin(a2) * Math.cos(b1) * Math.sin(b2);
        double t3 = Math.sin(a1) * Math.sin(b1);
        double tt = Math.acos(t1 + t2 + t3);
        double distance = 6366000 * tt;
        long f1 = Math.round(distance);// 四舍五入取整数
        logger.info("与地点距离为" + f1);
        if (f1 < 100) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @description 比赛结算
     * @version
     * @title
     * @author
     */
    public String settleUp() {
        logger.debug("比赛结算");

        // 结算表增加结算记录
        settleVo = new SettleVo();
        settleVo.setActivity_id(enrollVo.getActivity_id());
        settleVo = settleMgrService.querySettleDetailInfo(settleVo);

        Integer intHao = Integer.parseInt(enrollVo.getActivity_id());
        String strHao = intHao.toString();
        while (strHao.length() < 11) {
            strHao = "0" + strHao;
        }
        // 订单号格式 S + 11位比赛号 + 日期时间随机17位
        String pay_no = Constant.TRANSACTION_CODE_SETTLE + strHao + UUIDHexGeneratorEx.gen17();

        if (settleVo.getSettle_id() == null) {// 没有结算信息
            settleVo = new SettleVo();
            settleVo.setSettle_status(Constant.SETTLE_STATUS_BASE);// 结算状态为0
            // 创建结算记录
            settleVo.setPay_no(pay_no);// 结算订单号
            settleVo.setActivity_id(enrollVo.getActivity_id());// 比赛ID
            settleVo.setCost(total_fee);// 结算金额
            settleVo.setUser_id(session.get("userid").toString());
            String settle_id = settleMgrService.addSettle(settleVo);
            settleVo.setSettle_id(settle_id);
            settleVo = settleMgrService.querySettleDetailInfo(settleVo);
        }

        return "enterprisesPay";

    }

    public EnrollMgrService getEnrollMgrService() {
        return enrollMgrService;
    }

    public void setEnrollMgrService(EnrollMgrService enrollMgrService) {
        this.enrollMgrService = enrollMgrService;
    }

    public ActivityMgrService getActivityMgrService() {
        return activityMgrService;
    }

    public void setActivityMgrService(ActivityMgrService activityMgrService) {
        this.activityMgrService = activityMgrService;
    }

    public SettleMgrService getSettleMgrService() {
        return settleMgrService;
    }

    public void setSettleMgrService(SettleMgrService settleMgrService) {
        this.settleMgrService = settleMgrService;
    }

    public EnrollVo getEnrollVo() {
        return enrollVo;
    }

    public void setEnrollVo(EnrollVo enrollVo) {
        this.enrollVo = enrollVo;
    }

    public ActivityVo getActivityVo() {
        return activityVo;
    }

    public void setActivityVo(ActivityVo activityVo) {
        this.activityVo = activityVo;
    }

    public SettleVo getSettleVo() {
        return settleVo;
    }

    public void setSettleVo(SettleVo settleVo) {
        this.settleVo = settleVo;
    }

    public UserCreditDetailVo getUserCreditDetailVo() {
        return userCreditDetailVo;
    }

    public void setUserCreditDetailVo(UserCreditDetailVo userCreditDetailVo) {
        this.userCreditDetailVo = userCreditDetailVo;
    }

    public List<EnrollVo> getEnrollVoList() {
        return enrollVoList;
    }

    public void setEnrollVoList(List<EnrollVo> enrollVoList) {
        this.enrollVoList = enrollVoList;
    }

    public List<EnrollVo> getPayedList() {
        return payedList;
    }

    public void setPayedList(List<EnrollVo> payedList) {
        this.payedList = payedList;
    }

    public List<EnrollVo> getUnpayList() {
        return unpayList;
    }

    public void setUnpayList(List<EnrollVo> unpayList) {
        this.unpayList = unpayList;
    }

    public List<EnrollVo> getRefundList() {
        return refundList;
    }

    public void setRefundList(List<EnrollVo> refundList) {
        this.refundList = refundList;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public boolean isShowSettleButton() {
        return showSettleButton;
    }

    public void setShowSettleButton(boolean showSettleButton) {
        this.showSettleButton = showSettleButton;
    }

    public UserCenterService getUsercService() {
        return usercService;
    }

    public void setUsercService(UserCenterService usercService) {
        this.usercService = usercService;
    }

    //
    // /**
    // * @description 支付报名费用
    // * @version
    // * @title
    // * @author
    // * @throws IOException
    // */
    // public void payEnroll() throws IOException {
    //
    // // 如果是qq 浏览器 的 看cookie 里面是否有手机号，数据手机号，并且发短信验证 。报名成功，但是未交费 ，并在用户表中插入一条数据
    // // 如果有手机号，根据手机号去数据库中查询用户的信息，并且报名成功
    // // 如果是微信的浏览器，判断用户是否是关注，如果关注直接跳入到支付界面，如果未关注的也去支付，然后提示关注。
    // // 用户关注后，直接创建一个网站的用户。
    // // 判断是否是微信浏览器
    // // 获取金额，并且计算出来应付的钱数
    // // String url
    // //
    // ="http://www.shuttler.cn/p2pstock/weixin/Oauth2API_wixinAction.action？money=";
    // String url =
    // "http://www.shuttler.cn/p2pstock/weixin/Oauth2API_weixinAction.action?money=";
    // Object money = enrollVo.getCost();
    // UserInfoVo sessionUser = null;
    // UserInfo userInfo = null;
    // UserInfoVo userInfovo = null;
    //
    // String activityId = enrollVo.getActivity_id();
    // logger.info("eoems ,request th activity_id from activityVO.(" +
    // activityId + ")");
    //
    // logger.info("request get parameter name is enrollvocost :(" + money +
    // ")");
    // if (money != null && money.toString().trim().length() > 0) {
    // url = url + money;
    // } else {
    // logger.error("输入应缴费的钱数");
    // }
    // if (activityId != null && activityId.trim().length() > 0) {
    // url = url + "&activityId=" + activityId;
    // }
    // boolean validation = false;
    // String ua = ((HttpServletRequest)
    // request).getHeader("user-agent").toLowerCase();
    // if (ua.indexOf("micromessenger") > 0) {// 是微信浏览器
    // validation = true;
    // }
    // if (validation) {
    // url = url + "&isWbro" + "1";
    // } else {
    //
    // }
    // url = url + "&isWbro" + "0";
    //
    // Object sessionUserId = null;
    // sessionUserId =
    // request.getSession().getAttribute(Constant.getSession_userid());
    // Object sessionUserPhone = null;
    // sessionUserPhone =
    // request.getSession().getAttribute(Constant.getSession_phone());
    // Object sessionUserWeixinId = null;
    // sessionUserWeixinId =
    // request.getSession().getAttribute(Constant.getSession_user_weixinid());
    // if (sessionUserId != null || sessionUserPhone != null ||
    // sessionUserWeixinId != null) {
    // if (sessionUserId == null && (sessionUserPhone != null ||
    // sessionUserWeixinId != null)) {
    // try {
    // throw new Exception(
    // "eoems programe deal session error userid is null ,weixinid ,userphoe is not null");
    // } catch (Exception e) {
    // logger.info("eoems programe deal session error userid is null ,weixinid ,userphoe is not null");
    // e.printStackTrace();
    // }
    // } else if (sessionUserId != null &&
    // sessionUserId.toString().trim().length() > 0) {
    // sessionUser = usercService.queryUserInfo(sessionUserId.toString());
    // // 更新session 中的用户信息 ，包括 weixinid ，和openid
    // // 存比赛到数据库中和用户关联，
    // saveUserInfoVoToSession(sessionUser, request);
    // }
    // } else {
    // Cookie[] cookies = request.getCookies();
    // String cookieUserPhone = null;
    // String cookieUserId = null;
    // String cookieweixinOpenid = null;
    // if (cookies != null) {
    // for (Cookie cookie : cookies) {
    // logger.info("cookie each name: " + cookie.getName());
    // if (Constant.USER_COOKIE.equals(cookie.getName())) {
    // String value = cookie.getValue();
    // if (value != null && value.length() > 0) {
    // String[] split = value.split(",");
    // if (split[0] != null && split[0].length() > 0) {
    // cookieUserPhone = split[0];
    // }
    // if (split[1] != null && split[1].length() > 0) {
    // cookieUserId = split[1];
    // }
    // if (split[2] != null && split[2].length() > 0) {
    // cookieweixinOpenid = split[1];
    // }
    // }
    // }
    // }
    // logger.info("not fine name is user.cookie in cookies  ");
    // } else {
    // logger.info("not fine name is user.cookie in cookies  ");
    // }
    //
    // if (cookieUserPhone != null) {
    // userInfo = usercService.queryUserInfoByPhoneNum(cookieUserPhone);
    // } else if (cookieUserId != null) {
    // userInfovo = usercService.queryUserInfo(cookieUserId);
    // if (userInfovo != null && userInfovo.getUser_id() != null
    // && userInfovo.getUser_id().trim().length() > 0) {
    // userInfo = UserUtil.transFromUserInfoVo(userInfovo);
    // }
    // } else if (cookieweixinOpenid != null) {
    // userInfovo = usercService.queryUserByWeixinOpenId(cookieweixinOpenid);
    // BeanUtils.copyProperties(userInfovo, userInfo);
    // } else {
    // logger.info("eoems no any effective message");
    // }
    //
    // if (userInfo != null) {
    // saveUserToSession(userInfo, request);
    // } else {
    // logger.info("search userInfo not any info  ");
    // }
    //
    // }
    // if (userInfo != null) {
    // sessionUser = UserUtil.transFromUserInfo(userInfo);
    // }
    //
    // // 方数据到session中
    // // 最好是增加一个是否验证过。就不要取phone了
    //
    // boolean phoneValidated = false;
    // if (sessionUser != null && sessionUser.getPhone() != null &&
    // sessionUser.getPhone().trim().length() > 0) {
    // phoneValidated = true;
    // url = url + "&ph=" + "1";
    // } else {
    // url = url + "&ph=" + "0";
    // }
    //
    // response.sendRedirect(url);
    // return;
    // }
    //
    // public String NotifypayEnroll() throws IOException {
    // logger.debug("支付报名费用回调函数");
    // BufferedReader br = new BufferedReader(new
    // InputStreamReader((ServletInputStream) request.getInputStream()));
    // String line = null;
    // StringBuilder sb = new StringBuilder();
    // while ((line = br.readLine()) != null) {
    // sb.append(line);
    // }
    // return "";
    //
    // }

    public SiteMgrService getSiteMgrService() {
        return siteMgrService;
    }

    public void setSiteMgrService(SiteMgrService siteMgrService) {
        this.siteMgrService = siteMgrService;
    }

    // private Map<String, String> getAccessTokenAndUid(String strCode, String
    // strAppId, String strAppSecret) {
    // String responseDate = "";
    // Map<String, String> token = null;
    //
    // PostMethod postMethod = new PostMethod(Constant.strAccessTokenUrl);
    // postMethod.addParameter("appid", strAppId);
    // postMethod.addParameter("secret", strAppSecret);
    // postMethod.addParameter("code", strCode);
    // postMethod.addParameter("grant_type", "authorization_code");
    //
    // HttpClient client = new HttpClient();
    // try {
    // client.executeMethod(postMethod);
    // responseDate = postMethod.getResponseBodyAsString();
    // } catch (Exception e) {
    // logger.error(e.getMessage());
    // e.printStackTrace();
    // }
    //
    // if (responseDate.trim().length() > 0) {
    // token = new HashMap<String, String>();
    // JSONObject jsonData = JSONObject.fromObject(responseDate);
    // if (jsonData.has("errcode")) {
    // logger.error("Get access token fail,reason:" +
    // jsonData.getString("errmsg"));
    // token.put("errcode", jsonData.getString("errcode"));
    // token.put("errmsg", jsonData.getString("errmsg"));
    // return null;
    // }
    //
    // logger.info("Get access_token:" + jsonData.getString("access_token") +
    // ";openid:"
    // + jsonData.getString("openid"));
    // token.put("access_token", jsonData.getString("access_token"));
    // token.put("openid", jsonData.getString("openid"));
    // }
    // return token;
    // }
    //
    // /**
    // * general order
    // *
    // * @description
    // * @version
    // * @title
    // * @author 2015年11月30日
    // * @return
    // */
    // public String generalOrder() {
    //
    // return "";
    // }
    //
    // /*
    // * 8 test 测试的 getopenid 和pay 的接口 /*if (null == strWeixinCallbackCode ||
    // * strWeixinCallbackCode.equals("authdeny") || null == strParamers ||
    // * strParamers.isEmpty()) {
    // * logger.info("strWeixinCallbackCode ="+strWeixinCallbackCode);
    // * request.setAttribute("errcode", 1); request.setAttribute("errmsg",
    // * "用户拒绝授权"); return "userRefused"; }
    // */
    // public String testGetOpenid() throws Exception {/*
    // * //让用户授权，
    // * 测试在浏览器里面打开和通过分享或是推送的消息中打开
    // * //分享的url为
    // * http://www.shuttler
    // * .cn/p2pstock/activity/
    // * testGetOpenid_activityMgrAction
    // * .action
    // * response.setContentType
    // * ("text/html");
    // * response.setCharacterEncoding
    // * ("UTF-8"); String
    // * strWeixinCallbackCode =
    // * null; String strParamers
    // * = null; logger.info(
    // * "eoems weixin re invoke to  testGetopenid method "
    // * ); strWeixinCallbackCode
    // * =
    // * request.getParameter("code"
    // * ); logger.info(
    // * "eoems return code:" +
    // * strWeixinCallbackCode);
    // * strParamers =
    // * request.getParameter
    // * ("state"); logger.info(
    // * "eoems return stat parameter :"
    // * + strParamers);
    // * if(strWeixinCallbackCode
    // * .equals("authdeny")){
    // * logger.info(
    // * "user autheny is find -----------code"
    // * ); }else{ logger.info(
    // * "user autheny is not fine find -----------code"
    // * ); }
    // *
    // * String strAccessToken =
    // * null; String
    // * strUserOpenId = null;
    // * String strNickName = "";
    // * String strSex = "";//1
    // * man;2 female;3 unknown;
    // * String address = "";
    // * String strAppId =
    // * Constant.APPID;
    // * String strAppSecret =
    // * Constant.APPSECRET;
    // *
    // * Map<String, String>
    // * mapAccessToken =
    // * getAccessTokenAndUid
    // * (strWeixinCallbackCode,
    // * strAppId, strAppSecret);
    // * if (null ==
    // * mapAccessToken || null !=
    // * mapAccessToken
    // * .get("errcode")) {
    // * logger.info(
    // * " get token  -- fail fail fail "
    // * ); if (null !=
    // * mapAccessToken) {
    // * request.
    // * setAttribute("errcode",
    // * mapAccessToken
    // * .get("errcode"));
    // * request.
    // * setAttribute("errmsg",
    // * mapAccessToken
    // * .get("errmsg")); } return
    // * "getUserErr"; }
    // * strAccessToken =
    // * mapAccessToken
    // * .get("access_token");
    // * strUserOpenId =
    // * mapAccessToken
    // * .get("openid");
    // * request.getSession
    // * ().setAttribute(Constant.
    // * getSession_user_weixinid
    // * (),strUserOpenId );
    // * Map<String, String>
    // * mapUserInfo =
    // * getUserInfo(
    // * strAccessToken,
    // * strUserOpenId); if (null
    // * != mapUserInfo) { if(null
    // * !=
    // * mapUserInfo.get("errcode"
    // * )){ logger.info(
    // * " eoems get user error");
    // * request
    // * .setAttribute("errcode",
    // * mapUserInfo
    // * .get("errcode"));
    // * request.
    // * setAttribute("errmsg",
    // * mapUserInfo
    // * .get("errmsg")); return
    // * "getUserErr"; }
    // * strNickName =
    // * mapUserInfo.
    // * get("nickname"); strSex =
    // * mapUserInfo.get("sex");
    // *
    // * }
    // *
    // * logger.info(
    // * " eoems weixin web auth,user:"
    // * +
    // * strNickName+"strUserOpenId:"
    // * +
    // * strUserOpenId+" sex:"+strSex
    // * );
    // *
    // * String userIds =
    // * (String)request
    // * .getSession
    // * ().getAttribute
    // * (Constant.getSession_userid
    // * ()); if(userIds!= null &&
    // * !
    // * "".equals(userIds.trim())
    // * ){ UserInfoVo userInfo =
    // * usercService
    // * .queryUserInfo(userIds);
    // * if(userInfo!= null ){
    // * if(userInfo
    // * .getUser_weixin_id()!=
    // * null &&
    // * userInfo.getUser_weixin_id
    // * (
    // * ).equals(strUserOpenId)){
    // * }else
    // * if(userInfo.getUser_weixin_id
    // * ()!= null &&
    // * !userInfo.getUser_weixin_id
    // * (
    // * ).equals(strUserOpenId)){
    // * logger.info(
    // *
    // "user's weixin open id   different with auth get user weixin open id  find reason "
    // * ); throw new Exception(
    // * "data base  weixin open id be diffent with get from internet"
    // * ); }else
    // * if(userInfo.getUser_weixin_id
    // * () == null){ //update
    // * User
    // * userInfo.setUser_weixin_id
    // * (strUserOpenId);
    // * usercService
    // * .saveUserinfo(userInfo);
    // * logger.info(
    // * "update user to the date base  user id ="
    // * +userIds); }else{ throw
    // * new Exception("no way ");
    // * } }else{ throw new
    // * Exception(
    // * "not find user in database with userid "
    // * +userIds); }
    // *
    // * } //已经获取了openid
    // *
    // *
    // * //获取用户成功后,保存用户,然后
    // * 跳转到pay页面 //获取一个商品id,然后
    // * 走到支付页面 //网页授权后获取传递的参数
    // * String userId =
    // * request.getParameter
    // * ("userId"); String
    // * orderNo =
    // * request.getParameter
    // * ("orderNo"); String money
    // * =
    // * request.getParameter("money"
    // * ); String code =
    // * request.getParameter
    // * ("code"); //金额转化为分为单位
    // * float sessionmoney =
    // * Float.parseFloat(money);
    // * String finalmoney =
    // * String.format("%.2f",
    // * sessionmoney); finalmoney
    // * = finalmoney.replace(".",
    // * "");
    // *
    // * //商户相关资料 String appid =
    // * Constant.APPID; String
    // * appsecret =
    // * "a13c02de7cbc8b8e5c1d299ff719d569"
    // * ; String partner = "";
    // * String partnerkey = "";
    // *
    // *
    // * String openId =""; String
    // * URL =
    // * "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
    // * +
    // * appid+"&secret="+appsecret
    // * +"&code="+code+
    // * "&grant_type=authorization_code"
    // * ;
    // *
    // * JSONObject jsonObject =
    // * CommonUtil
    // * .httpsRequest(URL, "GET",
    // * null); if (null !=
    // * jsonObject) { openId =
    // * jsonObject
    // * .getString("openid"); }
    // *
    // * //获取openId后调用统一支付接口https:/
    // * /
    // * api.mch.weixin.qq.com/pay
    // * /unifiedorder String
    // * currTime =
    // * TenpayUtil.getCurrTime();
    // * //8位日期 String strTime =
    // * currTime.substring(8,
    // * currTime.length());
    // * //四位随机数 String strRandom
    // * =
    // * TenpayUtil.buildRandom(4)
    // * + ""; //10位序列号,可以自行调整。
    // * String strReq = strTime +
    // * strRandom;
    // *
    // * String appid =
    // * Constant.APPID; //商户号
    // * String mch_id =
    // * "1290813501"; //子商户号 非必输
    // * //String sub_mch_id="";
    // * //设备号 非必输 String
    // * device_info=""; //随机数
    // * String nonce_str =
    // * strReq; //商品描述 //String
    // * body = describe;
    // *
    // * //商品描述根据情况修改 String body
    // * = "支付测试啊"; //附加数据 String
    // * attach =
    // * session.get("userid"
    // * ).toString(); //商户订单号
    // * String out_trade_no =
    // * "123456789"; float
    // * sessionmoney =
    // * Float.parseFloat("0.1");
    // * String finalmoney =
    // * String.format("%.2f",
    // * sessionmoney); finalmoney
    // * = finalmoney.replace(".",
    // * "");
    // *
    // * int intMoney =
    // * Integer.parseInt
    // * (finalmoney);
    // * //总金额以分为单位，不带小数点 int
    // * total_fee = intMoney;
    // * //订单生成的机器 IP String
    // * spbill_create_ip =
    // * request.getRemoteAddr();
    // * //订 单 生 成 时 间 非必输 //
    // * String time_start ="";
    // * //订单失效时间 非必输 // String
    // * time_expire = ""; //商品标记
    // * 非必输 // String goods_tag =
    // * "";
    // *
    // * //这里notify_url是
    // * 支付完成后微信发给该链接信息
    // * ，可以判断会员是否支付成功，改变订单状态等。
    // * String notify_url
    // * =Constant
    // * .REDIRECT_URI_PAYNOTIC;
    // *
    // * String trade_type =
    // * "JSAPI"; String openid =
    // * strUserOpenId; //非必输 //
    // * String product_id = "";
    // * SortedMap<String, String>
    // * packageParams = new
    // * TreeMap<String,
    // * String>();
    // * packageParams.put
    // * ("appid",
    // * Constant.APPID);
    // * packageParams
    // * .put("mch_id", mch_id);
    // * packageParams
    // * .put("nonce_str",
    // * nonce_str);
    // * packageParams.put("body",
    // * body);
    // * packageParams.put("attach"
    // * , attach);
    // * packageParams.put
    // * ("out_trade_no",
    // * out_trade_no);
    // *
    // *
    // * //这里写的金额为1 分到时修改
    // * packageParams
    // * .put("total_fee", "1");
    // * //
    // * packageParams.put("total_fee"
    // * , "finalmoney");
    // * packageParams
    // * .put("spbill_create_ip",
    // * spbill_create_ip);
    // * packageParams
    // * .put("notify_url",
    // * notify_url);
    // *
    // * packageParams.put(
    // * "trade_type",
    // * trade_type);
    // * packageParams
    // * .put("openid", openid);
    // *
    // * RequestHandler reqHandler
    // * = new
    // * RequestHandler(request,
    // * response);
    // * reqHandler.init
    // * (Constant.APPID,
    // * Constant.APPSECRET,
    // * "080632");
    // *
    // * String sign =
    // * reqHandler.createSign
    // * (packageParams); String
    // * xml="<xml>"+
    // * "<appid>"+appid
    // * +"</appid>"+
    // * "<mch_id>"+mch_id
    // * +"</mch_id>"+
    // * "<nonce_str>"
    // * +nonce_str+"</nonce_str>"
    // * +
    // * "<sign>"+sign+"</sign>"+
    // * "<body><![CDATA["
    // * +body+"]]></body>"+
    // * "<attach>"
    // * +attach+"</attach>"+
    // * "<out_trade_no>"
    // * +out_trade_no
    // * +"</out_trade_no>"+
    // * //金额，这里写的1 分到时修改
    // * "<total_fee>"
    // * +1+"</total_fee>"+
    // * //"<total_fee>"
    // * +finalmoney
    // * +"</total_fee>"+
    // * "<spbill_create_ip>"
    // * +spbill_create_ip
    // * +"</spbill_create_ip>"+
    // * "<notify_url>"
    // * +notify_url+
    // * "</notify_url>"+
    // * "<trade_type>"
    // * +trade_type+
    // * "</trade_type>"+
    // * "<openid>"
    // * +openid+"</openid>"+
    // * "</xml>";
    // * logger.info(xml); String
    // * allParameters = ""; try {
    // * allParameters =
    // * reqHandler
    // * .genPackage(packageParams
    // * ); } catch (Exception e)
    // * {
    // * catch block
    // * e.printStackTrace(); }
    // * String createOrderURL =
    // * "https://api.mch.weixin.qq.com/pay/unifiedorder"
    // * ; String prepay_id="";
    // * try { prepay_id = new
    // * GetWxOrderno
    // * ().getPayNo(createOrderURL
    // * , xml);
    // * if(prepay_id.equals("")){
    // * request
    // * .setAttribute("ErrorMsg",
    // * "统一支付接口获取预支付订单出错");
    // * response
    // * .sendRedirect("error.jsp"
    // * ); } } catch (Exception
    // * e1) {
    // * Auto-generated catch
    // * block
    // * e1.printStackTrace(); }
    // * SortedMap<String, String>
    // * finalpackage = new
    // * TreeMap<String,
    // * String>(); String appid2
    // * = appid; String timestamp
    // * =
    // * Sha1Util.getTimeStamp();
    // * String nonceStr2 =
    // * nonce_str; String
    // * prepay_id2 =
    // * "prepay_id="+prepay_id;
    // * String packages =
    // * prepay_id2;
    // * finalpackage.put("appId",
    // * appid2);
    // * finalpackage.put(
    // * "timeStamp", timestamp);
    // * finalpackage
    // * .put("nonceStr",
    // * nonceStr2);
    // * finalpackage.put
    // * ("package", packages);
    // * finalpackage
    // * .put("signType", "MD5");
    // * String finalsign =
    // * reqHandler
    // * .createSign(finalpackage
    // * );
    // *
    // * response.sendRedirect(
    // * "/WEB-INF/views/enroll/paymgr/payInit.jsp?appid="
    // * +appid2+"&timeStamp="+
    // * timestamp
    // * +"&nonceStr="+nonceStr2
    // * +"&package="
    // * +packages+"&sign="
    // * +finalsign); return "";
    // */
    // return null;
    // }
    //
    // /**
    // * eoems add , lastest parameteraboutuser 参数 说明 subscribe
    // * 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。 openid 用户的标识，对当前公众号唯一
    // nickname
    // * 用户的昵称 sex 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知 city 用户所在城市 country 用户所在国家
    // * province 用户所在省份 language 用户的语言，简体中文为zh_CN headimgurl
    // * 用户头像，最后一个数值代表正方形头像大小（
    // * 有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
    // * subscribe_time 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间 unionid
    // * 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。详见：获取用户个人信息（UnionID机制） remark
    // * 公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注 groupid 用户所在的分组ID
    // */
    // private Map<String, String> getUserInfo(String strAccessToken, String
    // strOpenId) {
    // String responseDate = "";
    // Map<String, String> token = null;
    //
    // PostMethod postMethod = new PostMethod(Constant.strGetUserInfoUrl);
    // logger.info("get userinfo url ");
    // postMethod.addParameter("access_token", strAccessToken);
    // postMethod.addParameter("openid", strOpenId);
    // postMethod.addParameter("lang", "zh_CN");
    //
    // HttpClient client = new HttpClient();
    // try {
    // client.executeMethod(postMethod);
    // responseDate = postMethod.getResponseBodyAsString();
    // } catch (Exception e) {
    // logger.error(e.getMessage());
    // e.printStackTrace();
    // }
    // if (responseDate.trim().length() > 0) {
    // token = new HashMap<String, String>();
    // JSONObject jsonData = JSONObject.fromObject(responseDate);
    // if (jsonData.has("errcode")) {
    // logger.info("Get user info fail,reason:[" + jsonData.getString("errcode")
    // + "]"
    // + jsonData.getString("errmsg"));
    // // 这个是没用户授权，不能取用户信息的返回错误码，文档没提及，随时可能变的
    // if (48001 == jsonData.getLong("errcode")) {
    // logger.info("Can't get userinfo,but wo can continue.");
    // return null;
    // }
    // token.put("errcode", jsonData.getString("errcode"));
    // token.put("errmsg", jsonData.getString("errmsg"));
    // return token;
    // }
    //
    // if (!jsonData.has("openid") || !jsonData.has("nickname")) {
    // logger.info("No user info available");
    // return null;
    // }
    // logger.info("weixin web auth,user:" + jsonData.getString("nickname") +
    // " sex:" + jsonData.getString("sex")
    // + " province:" + jsonData.getString("province") + " city:" +
    // jsonData.getString("city")
    // + " country:" + jsonData.getString("country"));
    //
    // token.put("openid", jsonData.getString("openid"));
    // token.put("nickname", jsonData.getString("nickname"));
    // token.put("sex", jsonData.getString("sex"));
    // token.put("province", jsonData.getString("province"));
    // token.put("city", jsonData.getString("city"));
    // token.put("country", jsonData.getString("country"));
    //
    // }
    // return token;
    // }
    // public String noticePayResultEnroll() throws IOException {
    // BufferedReader br = new BufferedReader(new
    // InputStreamReader((ServletInputStream) request.getInputStream()));
    // String line = null;
    // StringBuilder sb = new StringBuilder();
    // while ((line = br.readLine()) != null) {
    // sb.append(line);
    // }
    // logger.info("支付结果通知的数据" + sb.toString());
    // return "payfinished";
    // }
    //
    // public String sandPayFortEnroll() throws IOException {
    // BufferedReader br = new BufferedReader(new
    // InputStreamReader((ServletInputStream) request.getInputStream()));
    // String line = null;
    // StringBuilder sb = new StringBuilder();
    // while ((line = br.readLine()) != null) {
    // sb.append(line);
    // }
    // logger.info("支付结果通知的数据" + sb.toString());
    // return "payfinished";
    // }
    //
    // public static void saveUserToSession(UserInfo userInfo,
    // HttpServletRequest req) {
    // HttpSession session = req.getSession();
    // // session.setAttribute(LoginAction.USER_SESSION, user);// 添加用户到session中
    // // return true;
    // /* 关键信息存入session */
    // // 用户id
    // req.getSession().setAttribute(Constant.getSession_userid(),
    // userInfo.getUser_id());
    // // 手机号
    // req.getSession().setAttribute(Constant.getSession_phone(),
    // userInfo.getPhone());
    //
    // if (userInfo.getUser_nickname() != null &&
    // !"".equals(userInfo.getUser_nickname())) {
    // req.getSession().setAttribute(Constant.getSession_username(),
    // userInfo.getUser_nickname());
    // } else if (userInfo.getUser_name() != null &&
    // !"".equals(userInfo.getUser_name())) {
    // req.getSession().setAttribute(Constant.getSession_username(),
    // userInfo.getUser_name());
    // } else {
    // req.getSession().setAttribute(Constant.getSession_username(),
    // userInfo.getPhone());
    // }
    // // 用户类型
    // req.getSession().setAttribute(Constant.getSession_usertyp(),
    // userInfo.getUser_type());
    // req.getSession().setAttribute(Constant.getSession_user_weixinid(),
    // userInfo.getWeixin_id());
    // // 获取用户菜单信息,菜单的就不需要设置了，目前不需要菜单，需要的时候在这个地方增加
    // logger.debug("用户名====" +
    // req.getSession().getAttribute(Constant.getSession_username()));
    // logger.debug("用户类型====" +
    // req.getSession().getAttribute(Constant.getSession_usertyp()));
    // }
    //
    // public static void saveUserInfoVoToSession(UserInfoVo userInfo,
    // HttpServletRequest req) {
    // HttpSession session = req.getSession();
    // // session.setAttribute(LoginAction.USER_SESSION, user);// 添加用户到session中
    // // return true;
    // /* 关键信息存入session */
    // // 用户id
    // req.getSession().setAttribute(Constant.getSession_userid(),
    // userInfo.getUser_id());
    // // 手机号
    // req.getSession().setAttribute(Constant.getSession_phone(),
    // userInfo.getPhone());
    //
    // if (userInfo.getUser_nickname() != null &&
    // !"".equals(userInfo.getUser_nickname())) {
    // req.getSession().setAttribute(Constant.getSession_username(),
    // userInfo.getUser_nickname());
    // } else if (userInfo.getUser_name() != null &&
    // !"".equals(userInfo.getUser_name())) {
    // req.getSession().setAttribute(Constant.getSession_username(),
    // userInfo.getUser_name());
    // } else {
    // req.getSession().setAttribute(Constant.getSession_username(),
    // userInfo.getPhone());
    // }
    // // 用户类型
    // req.getSession().setAttribute(Constant.getSession_usertyp(),
    // userInfo.getUser_type());
    // req.getSession().setAttribute(Constant.getSession_user_weixinid(),
    // userInfo.getUser_weixin_id());
    // // 获取用户菜单信息,菜单的就不需要设置了，目前不需要菜单，需要的时候在这个地方增加
    // logger.debug("用户名====" +
    // req.getSession().getAttribute(Constant.getSession_username()));
    // logger.debug("用户类型====" +
    // req.getSession().getAttribute(Constant.getSession_usertyp()));
    // }

}
