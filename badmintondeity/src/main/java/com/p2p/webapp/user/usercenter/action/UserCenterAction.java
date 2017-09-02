package com.p2p.webapp.user.usercenter.action;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.p2p.webapp.common.base.BaseAction;
import com.p2p.webapp.common.constant.Constant;
import com.p2p.webapp.common.page.Page;
import com.p2p.webapp.common.util.AppUtil;
import com.p2p.webapp.common.util.WebUtil;
import com.p2p.webapp.message.messagemgr.service.MessageMgrService;
import com.p2p.webapp.system.parammgr.entity.SystemParam;
import com.p2p.webapp.system.parammgr.service.ParamMgrService;
import com.p2p.webapp.system.parammgr.service.impl.ParamMgrServiceImpl;
import com.p2p.webapp.user.login.vo.LoginVo;
import com.p2p.webapp.user.usercenter.service.UserCenterService;
import com.p2p.webapp.user.usercenter.vo.UserBankVo;
import com.p2p.webapp.user.usercenter.vo.UserCreditDetailVo;
import com.p2p.webapp.user.usercenter.vo.UserCreditVo;
import com.p2p.webapp.user.usercenter.vo.UserInfoVo;

/**
 * @description 用户中心
 * @author
 * @date 2016-1-2 上午11:06:41
 */
public class UserCenterAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    // 写日志对象
    public static Logger logger = LoggerFactory.getLogger(UserCenterAction.class);

    // service
    private UserCenterService userCenterService;
    private MessageMgrService messageMgrService;
    // vo
    private UserInfoVo userInfoVo;
    private UserCreditVo userCreditVo;
    private List<UserCreditDetailVo> userCreditDetailList;
    // banklist
    private List<SystemParam> banklist;
    // bankinfolist
    private List<UserBankVo> userBankList;
    private UserBankVo userbankvo;
    private String secbankname;
    private String tranflag;
    private String chainflag;
    private Page page;
    private String queryuserid;
    private LoginVo loginvo;
    private int unreadMessage;

    /**
     * @description 我的账户页面跳转
     * @version
     * @title
     * @author
     * @return
     */
    public String userInit() {
        logger.debug("用户查询开始");
        String userid = (String) request.getSession().getAttribute(Constant.getSession_userid());
        userInfoVo = userCenterService.queryUserInfo(userid);
        userCreditVo = new UserCreditVo();
        userCreditVo.setUser_id(userid);
        userCreditVo = userCenterService.queryUserCredit(userCreditVo);
        unreadMessage = messageMgrService.queryUnreadMessageCount(userid);
        logger.debug("用户查询结束");
        return "userininit";
    }

    /**
     * @description 用户名片
     * @version
     * @title
     * @author
     * @return
     */
    public String userCard() {
        logger.debug("用户名片查询开始");
        String user_id = userInfoVo.getUser_id();
        userInfoVo = userCenterService.queryUserInfo(user_id);
        logger.debug("用户查询结束");
        return "userCard";
    }

    /**
     * @description 查询用户成绩明细
     * @version
     * @title
     * @author
     * @return
     */
    public String queryUserCredit() {
        String user_id = request.getSession().getAttribute(Constant.getSession_userid()).toString();
        userCreditVo = new UserCreditVo();
        userCreditVo.setUser_id(user_id);
        userCreditVo = userCenterService.queryUserCredit(userCreditVo);
        UserCreditDetailVo userCreditDetailVo = new UserCreditDetailVo();
        userCreditDetailVo.setAccount_id(userCreditVo.getAccount_id());
        userCreditDetailVo.setUser_id(user_id);
        userCreditDetailList = userCenterService.queryUserCreditDetail(userCreditDetailVo);
        return "myCredit";
    }

    /**
     * @description 用户信息维护
     * @version
     * @title
     * @author
     * @return
     */
    public String userEdit() {
        logger.debug("用户查询开始");
        String userid = (String) request.getSession().getAttribute(Constant.getSession_userid());
        userInfoVo = userCenterService.queryUserInfo(userid);
        logger.debug("用户查询结束");
        return "useredit";
    }

    /**
     * @description 保存用户信息
     * @version
     * @title
     * @author
     * @return
     */
    public String userSave() {
        logger.debug("用户保存开始");
        logger.debug("start get all parameters url:" + request.getRequestURI());
        Enumeration pNames = request.getParameterNames();
        while(pNames.hasMoreElements())
        {
        	String name = (String)pNames.nextElement();
        	String value = request.getParameter(name);
        	logger.debug(name + "=" + value);
        }
        String userid = (String) request.getSession().getAttribute(Constant.getSession_userid());
        userInfoVo.setUser_id(userid);
        userCenterService.saveUserinfo(userInfoVo);
        userInfoVo = userCenterService.queryUserInfo(userid);
        // 更新session中的用户名
        if (userInfoVo.getUser_nickname() != null && !"".equals(userInfoVo.getUser_nickname())) {
            request.getSession().setAttribute("username", userInfoVo.getUser_nickname());
        } else if (userInfoVo.getUser_name() != null && !"".equals(userInfoVo.getUser_name())) {
            request.getSession().setAttribute("username", userInfoVo.getUser_name());
        }
        logger.debug("用户保存结束");
        return "saveok";
    }
    
    /**
     * @description 设置我的球龄
     * @version
     * @title
     * @author 
     * @return 
    */
    public String myStartPlayingTime(){
        logger.debug("我的球龄");
        String userid = (String) request.getSession().getAttribute(Constant.getSession_userid());
        return "myStartPlayingTime";
    }
    

    // --------------------------------------- 以下方法暂未使用  ---------------------------------------
    /*
     * 找回密码
     */
    public String updatePhoneNum() throws Exception {

        // 验证手机验证码是否正确
        String phoneNum = request.getParameter("cptcusMobile");

        msgCode = AppUtil.jugeCpt(session, loginvo.getCusCpt());
        // if(!"".equals(msgCode)){
        // return "findpwdERR";
        // }
        // 验证用户状态
        String sessionUserid = WebUtil.getObject(request, Constant.getSession_userid());
        // sessionUserid = "0";
        if (sessionUserid != null && sessionUserid.trim().length() > 0) {

            UserInfoVo userinfo = userCenterService.queryUserInfo(sessionUserid);
            if (userinfo != null) {
                userinfo.setPhone(phoneNum);
                userCenterService.saveUserinfo(userinfo);
                WebUtil.putObject(request, Constant.getSession_phone(), phoneNum);
                String loginSource = WebUtil.getObject(request, "loginjumpSour");
                response.sendRedirect(loginSource);
            }
        } else {
            throw new Exception("no user in system " + sessionUserid);
        }
        return null;

    }

    /*
     * 银行信息维护初始化
     */
    public String bankEdit() {
        logger.debug("银行初始化开始");
        String userid = (String) request.getSession().getAttribute(Constant.getSession_userid());
        userInfoVo = userCenterService.queryUserInfo(userid);
        if (userInfoVo.getUser_name() == null || "".equals(userInfoVo.getUser_name())) {
            msgCode = "MSG0004";
            return "bankiniterror";
        }
        // 获取客户银行卡信息
        userBankList = new ArrayList<UserBankVo>();
        userBankList = userCenterService.queryBankInfo(userid);
        banklist = new ArrayList<SystemParam>();
        ParamMgrService ps = new ParamMgrServiceImpl();
        banklist = ps.querySystemByTyp("BANK");
        logger.debug("银行初始化结束");
        return "bankinit";
    }

    /*
     * 银行信息查询
     */
    public String bankQuery() {
        logger.debug("银行信息查询开始");
        // 获取客户银行卡信息
        userBankList = new ArrayList<UserBankVo>();
        userBankList = userCenterService.queryBankInfo(queryuserid);
        logger.debug("银行信息查询结束");
        return "bankQueryOk";
    }

    /*
     * 增加银行卡信息
     */
    public String bankAdd() {
        logger.debug("增加银行卡信息开始");
        String userid = (String) request.getSession().getAttribute(Constant.getSession_userid());
        // 验证手机验证码
        msgCode = AppUtil.jugeCpt(session, userbankvo.getCptno());
        if (msgCode != null && !"".equals(msgCode)) {
            return "addbankok";
        }
        ParamMgrService ps = new ParamMgrServiceImpl();
        SystemParam sp = ps.querySystemByName(secbankname);
        userbankvo.setBank_code(sp.getPara_code());
        userbankvo.setBank_name(secbankname);
        userbankvo.setUser_id(userid);
        userCenterService.addBankInfo(userbankvo);

        msgCode = "MSG0005";
        logger.debug("增加银行卡信息结束");
        return "addbankok";
    }

    /*
     * 更新银行卡信息
     */
    public String bankEditInfo() {
        logger.debug("更新银行卡信息开始");
        // 验证手机验证码
        msgCode = AppUtil.jugeCpt(session, userbankvo.getCptno());
        if (msgCode != null && !"".equals(msgCode)) {
            return "editbankok";
        }
        ParamMgrService ps = new ParamMgrServiceImpl();
        SystemParam sp = ps.querySystemByName(userbankvo.getBank_name());
        userbankvo.setBank_code(sp.getPara_code());
        userCenterService.editBankInfo(userbankvo);
        msgCode = "MSG0006";
        logger.debug("更新银行卡信息结束");
        return "editbankok";
    }

    /*
     * 删除银行卡信息
     */
    public String bankDelInfo() {
        logger.debug("删除银行卡信息开始");
        userCenterService.delBankInfo(userbankvo.getUser_bank_acc_id());
        msgCode = "MSG0007";
        logger.debug("删除银行卡信息结束");
        return "delbankok";
    }

    /*
     * 设置默认银行卡
     */
    public String setDefaultBank() {
        String userid = (String) request.getSession().getAttribute(Constant.getSession_userid());
        userbankvo.setUser_id(userid);
        userCenterService.setDefaultBank(userbankvo);
        return "setDefaultBankok";
    }

    /*
     * 修改登录密码初始化
     * 
     * @return
     */
    public String loginPwdEdit() {
        logger.debug("修改登录密码初始化开始");

        logger.debug("修改登录密码初始化结束");
        return "editpwdok";
    }

    /*
     * 修改登录密码提交
     * 
     * @return
     */
    public String loginPwdEditSave() {
        logger.debug("修改登录密码开始");
        String userid = (String) request.getSession().getAttribute(Constant.getSession_userid());
        if (!userCenterService.checkUserLogin(userid, userInfoVo.getLogin_pwd())) {
            msgCode = "ERR0001";
            return "editpwdok";
        }
        userInfoVo.setUser_id(userid);
        userCenterService.editUserLoginPwd(userInfoVo);
        msgCode = "MSG0001";
        logger.debug("修改登录密码结束");
        return "editpwdok";
    }

    /*
     * 修改交易密码初始化
     * 
     * @return
     */
    public String tranPwdEdit() {
        logger.debug("修改交易密码初始化开始");
        String userid = (String) request.getSession().getAttribute(Constant.getSession_userid());
        UserInfoVo uvo = new UserInfoVo();
        uvo = userCenterService.queryUserInfo(userid);
        if (uvo.getTran_pwd() == null || "".equals(uvo.getTran_pwd())) {
            // 设置密码
            tranflag = "0";
        } else {
            // 修改密码
            tranflag = "1";
        }
        logger.debug("修改交易密码初始化结束");
        return "edittranpwdok";
    }

    /*
     * 设置交易密码
     */
    public String tranPwdSetSub() {
        logger.debug("设置交易密码开始");
        String userid = (String) request.getSession().getAttribute(Constant.getSession_userid());
        userInfoVo.setUser_id(userid);
        userCenterService.editUserTranPwd(userInfoVo);
        msgCode = "MSG0002";
        logger.debug("设置交易密码结束");
        return "edittranpwdsubok";
    }

    /*
     * 修改交易密码
     */
    public String tranPwdEditSub() {
        logger.debug("修改交易密码开始");
        String userid = (String) request.getSession().getAttribute(Constant.getSession_userid());
        // 获取用户原交易密码
        UserInfoVo uvo = new UserInfoVo();
        uvo = userCenterService.queryUserInfo(userid);
        // 比较是否一致
        if (!userInfoVo.getTran_pwd().equals(uvo.getTran_pwd())) {
            msgCode = "ERR0002";
            return "edittranpwdok";
        }
        userInfoVo.setUser_id(userid);
        userCenterService.editUserTranPwd(userInfoVo);
        msgCode = "MSG0003";
        logger.debug("修改交易密码结束");
        return "edittranpwdsubok";
    }

    public UserCenterService getUserCenterService() {
        return userCenterService;
    }

    public void setUserCenterService(UserCenterService userCenterService) {
        this.userCenterService = userCenterService;
    }

    public MessageMgrService getMessageMgrService() {
        return messageMgrService;
    }

    public void setMessageMgrService(MessageMgrService messageMgrService) {
        this.messageMgrService = messageMgrService;
    }

    public UserInfoVo getUserInfoVo() {
        return userInfoVo;
    }

    public void setUserInfoVo(UserInfoVo userInfoVo) {
        this.userInfoVo = userInfoVo;
    }

    public UserCreditVo getUserCreditVo() {
        return userCreditVo;
    }

    public void setUserCreditVo(UserCreditVo userCreditVo) {
        this.userCreditVo = userCreditVo;
    }

    public List<UserCreditDetailVo> getUserCreditDetailList() {
        return userCreditDetailList;
    }

    public void setUserCreditDetailList(List<UserCreditDetailVo> userCreditDetailList) {
        this.userCreditDetailList = userCreditDetailList;
    }

    public List<SystemParam> getBanklist() {
        return banklist;
    }

    public void setBanklist(List<SystemParam> banklist) {
        this.banklist = banklist;
    }

    public List<UserBankVo> getUserBankList() {
        return userBankList;
    }

    public void setUserBankList(List<UserBankVo> userBankList) {
        this.userBankList = userBankList;
    }

    public UserBankVo getUserbankvo() {
        return userbankvo;
    }

    public void setUserbankvo(UserBankVo userbankvo) {
        this.userbankvo = userbankvo;
    }

    public String getSecbankname() {
        return secbankname;
    }

    public void setSecbankname(String secbankname) {
        this.secbankname = secbankname;
    }

    public String getTranflag() {
        return tranflag;
    }

    public void setTranflag(String tranflag) {
        this.tranflag = tranflag;
    }

    public String getChainflag() {
        return chainflag;
    }

    public void setChainflag(String chainflag) {
        this.chainflag = chainflag;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public String getQueryuserid() {
        return queryuserid;
    }

    public void setQueryuserid(String queryuserid) {
        this.queryuserid = queryuserid;
    }

    public LoginVo getLoginvo() {
        return loginvo;
    }

    public void setLoginvo(LoginVo loginvo) {
        this.loginvo = loginvo;
    }

    public int getUnreadMessage() {
        return unreadMessage;
    }

    public void setUnreadMessage(int unreadMessage) {
        this.unreadMessage = unreadMessage;
    }

}
