package com.p2p.webapp.common.intercepts;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.p2p.webapp.common.constant.Constant;
import com.p2p.webapp.common.util.WebUtil;
import com.p2p.webapp.user.login.entity.UserInfo;
import com.p2p.webapp.weixin.action.WeixinAction;

/**
 * 所有业务处理公用拦截器 处理用户登录状态等校验
 * 
 */
public class BizIntercept extends MethodFilterInterceptor {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    // 写日志对象
    public static Logger logger = LoggerFactory.getLogger(BizIntercept.class);

    @Override
    protected String doIntercept(ActionInvocation invocation) throws Exception {
        logger.debug("进入拦截器BizIntercept");
        HttpServletRequest request = (HttpServletRequest) invocation.getInvocationContext().get(
                ServletActionContext.HTTP_REQUEST);
        HttpServletResponse respone = (HttpServletResponse) invocation.getInvocationContext().get(
                ServletActionContext.HTTP_RESPONSE);
        String requestURL = request.getRequestURL().toString();
        String requestQueryString = request.getQueryString();
        requestURL = requestURL + "?" + requestQueryString;

        // 除了不需要的url，其他的都判断是否有登录，没有登录 ，直接调微信授权
        // 不进行登录校验的UAL命名空间
        String filterUrl = Constant.getBizinterceptfilter();
        if (checkFilter(requestURL, filterUrl)) {
            // 执行登录状态校验
            String userid = (String) request.getSession().getAttribute("userid");
            logger.info("userid =    " + userid);
            logger.debug("jiang get userid !!!!!!");
            logger.debug(userid);
            if (userid == null || "".equals(userid) ) {
                logger.debug("未登录或登录超时请重新登录");
                request.setAttribute("errmsg", "未登录或登录超时请重新登录");
                // requestURI = "http://www.shuttler.cn" + requestURI;
                // requestURI = "http://localhost" + requestURI;// 本地调试时使用
                request.getSession().setAttribute("requestURL", requestURL);
                logger.info("BizIntercept -- doIntercept() -- 未登录或登录超时请重新登录-- requestURL "
                        + request.getSession().getAttribute("requestURL").toString());

                // 判断浏览器
                String ua = ((HttpServletRequest) request).getHeader("user-agent").toLowerCase();
                logger.debug("浏览器type:");
                logger.debug(ua);
                
                if (ua.indexOf("micromessenger") > 0) {// 是微信浏览器
                    logger.info("微信浏览器");
                    WeixinAction weixinAction = new WeixinAction();
                    weixinAction.Oauth2API();
                } else {
                    logger.info("其他浏览器");
                    // 获取cookie
                    Cookie[] cookies = request.getCookies();
                    if (cookies != null) {
                        // 读cookies
                        String cookieStr = WebUtil.getCookieByName(request, Constant.USER_COOKIE);
                        if (cookieStr != null && !cookieStr.equals("")) {
                            logger.info(cookieStr);
                            String[] split = cookieStr.split(",");
                            String user_id = split[0];
                            request.getSession().setAttribute("userid", user_id);
                        } else {
                            logger.info("cookie无记录 跳转至注册页面");
                            // String backUrl =
                            // "http://www.shuttler.cn/p2pstock/regist/registInit_registAction.action";
                            String backUrl = "http://127.0.0.1：8080/p2pstock/regist/registInit_registAction.action";
                            //String backUrl = "http://yiqidongba.cn/p2pstock/regist/registInit_registAction.action";
                            respone.sendRedirect(backUrl);
                            return null;
                        }
                        request.getSession().setAttribute("city", "北京");
                    }
                }
            }
        }
        String result = invocation.invoke();
        logger.debug("结束拦截器BizIntercept");
        return result;
    }

    /*
     * 验证是否需要进行登录校验 true需要 false 不需要
     */
    private boolean checkFilter(String url, String filter) {
        String[] filterarray = filter.split(",");
        String[] uriarray = url.split("\\/");
        // 首页过滤
        if (uriarray[5].indexOf(("index.action")) != -1) {
            return false;
        }
        // 登录、注册、demo过滤
        for (int i = 0; i < filterarray.length; i++) {
            if (filterarray[i].equals(uriarray[4])) {
                return false;
            }
        }
        return true;
    }

    public static void saveUserToSession(UserInfo userInfo, HttpServletRequest req) {
        // session.setAttribute(LoginAction.USER_SESSION, user);// 添加用户到session中
        // return true;
        /* 关键信息存入session */
        // 用户id
        req.getSession().setAttribute(Constant.getSession_userid(), userInfo.getUser_id());
        // 手机号
        req.getSession().setAttribute(Constant.getSession_phone(), userInfo.getPhone());

        if (userInfo.getUser_nickname() != null && !"".equals(userInfo.getUser_nickname())) {
            req.getSession().setAttribute(Constant.getSession_username(), userInfo.getUser_nickname());
        } else if (userInfo.getUser_name() != null && !"".equals(userInfo.getUser_name())) {
            req.getSession().setAttribute(Constant.getSession_username(), userInfo.getUser_name());
        } else {
            req.getSession().setAttribute(Constant.getSession_username(), userInfo.getPhone());
        }
        // 用户类型
        req.getSession().setAttribute(Constant.getSession_usertyp(), userInfo.getUser_type());
        req.getSession().setAttribute(Constant.getSession_user_weixinid(), userInfo.getWeixin_id());
        // 获取用户菜单信息,菜单的就不需要设置了，目前不需要菜单，需要的时候在这个地方增加
        logger.debug("用户名====" + req.getSession().getAttribute(Constant.getSession_username()));
        logger.debug("用户类型====" + req.getSession().getAttribute(Constant.getSession_usertyp()));
    }

}
