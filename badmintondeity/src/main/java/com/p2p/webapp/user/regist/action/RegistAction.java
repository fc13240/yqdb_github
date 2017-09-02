package com.p2p.webapp.user.regist.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.p2p.webapp.common.base.BaseAction;
import com.p2p.webapp.common.constant.Constant;
import com.p2p.webapp.common.util.WebUtil;
import com.p2p.webapp.user.regist.entity.User;
import com.p2p.webapp.user.regist.service.RegistService;
import com.p2p.webapp.user.regist.vo.RegistVo;

/**
 * 用户注册模块
 * 
 * @author Administrator
 * 
 */
public class RegistAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    // 写日志对象
    public static Logger logger = LoggerFactory.getLogger(RegistAction.class);
    private RegistVo registvo;
    // service bean
    private RegistService registService;

    /**
     * @description 注册页面跳转
     * @version
     * @title
     * @author
     * @return
     */
    public String registInit() {
        logger.debug("注册init开始执行");
        logger.debug("注册init结束执行");
        return "registinit";
    }

    /**
     * @description 根据手机号检查用户是否存在
     * @version
     * @title
     * @author
     */
    public void checkPhone() {
        Map<String,String> map = new HashMap<String,String>();
        registvo = new RegistVo();
        String cusMobile = request.getParameter("mobile");
        registvo.setCusMobile(cusMobile);
        User user = registService.checkPhone(registvo.getCusMobile());
        if (user == null) {// 没有手机对应的用户
            map.put("result", "0");
            outJson(map);
        } else {
            map.put("result", "-1");
            outJson(map);
            
        }
    }

    /**
     * @description 用户注册
     * @version
     * @title
     * @author
     * @return
     */
    public void regist() {
        logger.debug("注册Action开始执行");
        // 验证手机号码是否存在
        User user = registService.checkPhone(registvo.getCusMobile());
        String userId = "";
        if (user == null) {// 用户未注册
            // 插入数据
            user = new User();
            user.setUser_name(registvo.getCusMobile());
            user.setUser_nickname(registvo.getCusMobile());
            user.setUser_headimgurl("/p2pstock/static/images/rrd/ucenter-man.png");
            user.setPhone(registvo.getCusMobile());
            userId = registService.registUser(user);
            user = registService.checkPhone(registvo.getCusMobile());
        } else {
            logger.debug("用户已注册");
            userId = user.getUser_id();
        }

        try {
            request.getSession().setAttribute(Constant.getSession_user_weixinid(),user.getWeixin_id());// 授权后获得
            request.getSession().setAttribute("userid", userId);
            request.getSession().setAttribute("city", "北京");
            String cookieStr = user.getUser_id() + "," + user.getPhone() + "," + "北京";
            //jiangxian
            logger.debug(cookieStr);
            //WebUtil.addCookie(response, Constant.USER_COOKIE, cookieStr, -1);
            response.sendRedirect(request.getSession().getAttribute("requestURL").toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.debug("注册Action结束");
    }

    public RegistVo getRegistvo() {
        return registvo;
    }

    public void setRegistvo(RegistVo registvo) {
        this.registvo = registvo;
    }

    public RegistService getRegistService() {
        return registService;
    }

    public void setRegistService(RegistService registService) {
        this.registService = registService;
    }
}
