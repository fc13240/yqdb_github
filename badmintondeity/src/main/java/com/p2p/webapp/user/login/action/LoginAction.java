package com.p2p.webapp.user.login.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.p2p.webapp.common.base.BaseAction;
import com.p2p.webapp.common.constant.Constant;
import com.p2p.webapp.common.util.AppUtil;
import com.p2p.webapp.system.menu.entity.MenuInfo;
import com.p2p.webapp.user.login.service.LoginService;
import com.p2p.webapp.user.login.vo.LoginVo;
import com.p2p.webapp.user.regist.dto.UserDto;

/**
 * @description 用户登录模块
 * @author
 * @date 2016-1-20 上午10:34:29
 */
public class LoginAction extends BaseAction {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    // 写日志对象
    public static Logger logger = LoggerFactory.getLogger(LoginAction.class);
    // service
    private LoginService loginService;
    // 表单
    private LoginVo loginvo;

    /*
     * 登录按钮页面跳转
     * 
     * @return
     */
    public String loginInit() {
        String openid = request.getParameter("openid");
        String openkey = request.getParameter("openkey");
        return "logininit";
    }

    /*
     * QQ登录按钮页面跳转
     * 
     * @return
     */
    public String qqLogin() {
        return "qqLogin";
    }

    /**
     * 在微信的服务器配置页面配置的路径 ,用于处理微信首次调用和接收微信的消息
     * 
     * @description
     * @version
     * @title
     * @author 2015年12月2日
     * @throws IOException
     */
    public void weixinInit() throws IOException {
        // 这个地方单独调用类，根据不同的类型进行一个处理
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        /** 读取接收到的xml消息 */
        StringBuffer sb = new StringBuffer();
        InputStream is = request.getInputStream();
        InputStreamReader isr = new InputStreamReader(is, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String s = "";
        while ((s = br.readLine()) != null) {
            sb.append(s);
        }
        String xml = sb.toString(); // 次即为接收到微信端发送过来的xml数据

        String afterdecry = "";// AuthProcess.decryptMsg(request, xml);

        String result = "";
        /** 判断是否是微信接入激活验证，只有首次接入验证时才会收到echostr参数，此时需要把它直接返回 */
        String echostr = request.getParameter("echostr");
        if (echostr != null && echostr.length() > 1) {
            result = echostr;
        } else {
            // 正常的微信处理流程

        }

        try {
            OutputStream os = response.getOutputStream();
            os.write(result.getBytes("UTF-8"));
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * 找回密码按钮页面跳转
     * 
     * @return
     */
    public String findpwdInit() {
        return "findpwdinit";
    }

    /*
     * 找回密码
     */
    public String findpwd() {

        // 验证手机验证码是否正确
        msgCode = AppUtil.jugeCpt(session, loginvo.getCusCpt());
        // if(!"".equals(msgCode)){
        // return "findpwdERR";
        // }
        // 验证用户状态
        String userstatus = loginService.queryUserStatus(loginvo.getUserno());
        // 验证失败
        if ("1".equals(userstatus)) {
            msgCode = "ERR0007";
            return "findpwdERR";
        }
        if ("3".equals(userstatus)) {
            msgCode = "ERR0009";
            return "findpwdERR";
        }
        if ("4".equals(userstatus)) {
            msgCode = "ERR0010";
            return "findpwdERR";
        }

        return "findpwdOK";
    }

    /*
     * 设置登录密码
     */
    public String setnewpwd() {

        // 执行登录
        String clientip = AppUtil.getIpAddr(request);
        // 执行登录业务服务
        UserDto udto = loginService.updatePwd(loginvo, clientip);
        // 登录后数据存放session
        saveSeesion(request, udto);
        return "setnewpwdOK";
    }

    public String qqLoginCheck() {
        String openId = request.getParameter("openId");
        String accessToken = request.getParameter("accessToken");

        if (true) {
            return "loginok";
        } else {
            return "registinit";
        }

    }

    /*
     * 登录
     */
    public String login() {
        logger.debug("进入登录验证action");
        // 后台表单检查
        if ("".equals(loginvo.getUserno()) || "".equals(loginvo.getPasswd())) {
            msgCode = "ERR0005";
            return "loginerr";
        }
        String clientip = AppUtil.getIpAddr(request);
        // 执行登录业务服务
        UserDto udto = loginService.checkUser(loginvo.getUserno(), loginvo.getPasswd(), clientip);
        // 验证成功
        if ("0".equals(udto.getLog_result())) {
            /* 关键信息存入session */
            saveSeesion(request, udto);
            return "loginok";
        }
        // 验证失败
        if ("1".equals(udto.getLog_result())) {
            msgCode = "ERR0007";
        }
        if ("2".equals(udto.getLog_result())) {
            msgCode = "ERR0008";
        }
        if ("3".equals(udto.getLog_result())) {
            msgCode = "ERR0009";
        }
        if ("4".equals(udto.getLog_result())) {
            msgCode = "ERR0010";
        }
        if ("5".equals(udto.getLog_result())) {
            msgCode = "ERR0011";
        }
        return "loginerr";
    }

    public String saveSeesion(HttpServletRequest request, UserDto udto) {

        /* 关键信息存入session */
        // 用户id
        request.getSession().setAttribute(Constant.getSession_userid(), udto.getUser_id());
        // 手机号
        request.getSession().setAttribute(Constant.getSession_phone(), udto.getPhone());
        // 用户名
        if (udto.getUser_nickname() != null && !"".equals(udto.getUser_nickname())) {
            request.getSession().setAttribute(Constant.getSession_username(), udto.getUser_nickname());
        } else if (udto.getUser_name() != null && !"".equals(udto.getUser_name())) {
            request.getSession().setAttribute(Constant.getSession_username(), udto.getUser_name());
        } else {
            request.getSession().setAttribute(Constant.getSession_username(), udto.getPhone());
        }
        // 用户类型
        request.getSession().setAttribute(Constant.getSession_usertyp(), udto.getUser_type());
        // 获取用户菜单信息
        List<MenuInfo> menulist = loginService.queryUserMenu(udto.getUser_id());
        request.getSession().setAttribute(Constant.getSession_mymenulist(), menulist);
        request.getSession().setAttribute("menusec", "MYACOUNT");
        request.getSession().setAttribute("menusecname", "我的账户");
        logger.debug("用户名====" + request.getSession().getAttribute(Constant.getSession_username()));
        logger.debug("用户类型====" + request.getSession().getAttribute(Constant.getSession_usertyp()));
        return "0";
    }

    /*
     * 退出登录销毁session
     */
    public String logout() {
        logger.debug("开始销毁session");
        request.getSession().invalidate();
        logger.debug("成功销毁session");
        return "logout";
    }

    public LoginVo getLoginvo() {
        return loginvo;
    }

    public void setLoginvo(LoginVo loginvo) {
        this.loginvo = loginvo;
    }

    public LoginService getLoginService() {
        return loginService;
    }

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

}
