package com.p2p.webapp.weixin.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.p2p.webapp.activity.activitymgr.service.ActivityMgrService;
import com.p2p.webapp.activity.activitymgr.vo.ActivityVo;
import com.p2p.webapp.common.base.BaseAction;
import com.p2p.webapp.common.constant.Constant;
import com.p2p.webapp.enroll.enrollmgr.service.EnrollMgrService;
import com.p2p.webapp.enroll.enrollmgr.vo.EnrollVo;
import com.p2p.webapp.user.regist.entity.User;
import com.p2p.webapp.user.regist.service.RegistService;
import com.p2p.webapp.user.usercenter.service.UserCenterService;
import com.p2p.webapp.user.usercenter.vo.UserInfoVo;
import com.p2p.webapp.weixin.bean.WeiXinOauth2Token;
import com.p2p.webapp.weixin.tool.service.CoreService;
import com.p2p.webapp.weixin.tool.util.SignUtil;
import com.p2p.webapp.weixin.util.ConfigUtil;
import com.p2p.webapp.weixin.util.GetWeiXinCode;

/**
 * 微信的action
 * 
 * @author eoems
 * 
 */
public class WeixinAction extends BaseAction {

    private static final long serialVersionUID = 1L;
    // 写日志对象
    public static Logger logger = LoggerFactory.getLogger(WeixinAction.class);

    private EnrollMgrService enrollMgrService;
    private ActivityMgrService activityMgrService;
    private RegistService registService;
    private UserCenterService usercService;
    private EnrollVo enrollVo;
    private ActivityVo activityVo;
    private List<EnrollVo> enrollVoList;
    private String oauth2_base;
    private WeiXinOauth2Token weiXinOauth2Token;
    private String code;

    public UserCenterService getUsercService() {
        return usercService;
    }

    public void setUsercService(UserCenterService usercService) {
        this.usercService = usercService;
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
    public void weixinInit() throws IOException {/*
                                                  * logger.info(
                                                  * "entry the method weixinInit"
                                                  * ); //这个地方单独调用类，根据不同的类型进行一个处理
                                                  * request
                                                  * .setCharacterEncoding(
                                                  * "UTF-8");
                                                  * response.setCharacterEncoding
                                                  * ("UTF-8");
                                                  */
        /** 读取接收到的xml消息 */
        /*
         * String signature = request.getParameter("signature"); String
         * timestamp =request.getParameter("timestamp"); String nonce =
         * request.getParameter("nonce");
         * 
         * StringBuffer sb = new StringBuffer(); InputStream is =
         * request.getInputStream(); InputStreamReader isr = new
         * InputStreamReader(is, "UTF-8"); BufferedReader br = new
         * BufferedReader(isr); String s = ""; while ((s = br.readLine()) !=
         * null) { sb.append(s); } String xml = sb.toString();
         * //次即为接收到微信端发送过来的xml数据
         * 
         * String afterdecry ="";// AuthProcess.decryptMsg(request, xml);
         * 
         * String result = "";
         *//** 判断是否是微信接入激活验证，只有首次接入验证时才会收到echostr参数，此时需要把它直接返回 */
        /*
         * String echostr = request.getParameter("echostr");
         * if(SignUtil.checkSignature(signature, timestamp, nonce)){ if (
         * echostr != null && echostr.length() > 1) { result = echostr; } else {
         * //正常的微信处理流程
         * logger.info("eoems weixininit weixinaction xml message "+xml);
         * logger.info(
         * "-----------------------------com from weixin even end-----------------"
         * );
         * 
         * try { ProcessReqest.process(request, response); } catch (Exception e)
         * { // TODO Auto-generated catch block e.printStackTrace(); } }
         * 
         * }else{ result = null; }
         * 
         * if ( echostr != null && echostr.length() > 1) { result = echostr; }
         * else { //正常的微信处理流程
         * logger.info("eoems weixininit weixinaction xml message "+xml);
         * logger.info(
         * "-----------------------------com from weixin even end-----------------"
         * );
         * 
         * try { ProcessReqest.process(request, response); } catch (Exception e)
         * { // TODO Auto-generated catch block e.printStackTrace(); } }
         * 
         * try { OutputStream os = response.getOutputStream();
         * os.write(result.getBytes("UTF-8")); os.flush(); os.close(); } catch
         * (Exception e) { e.printStackTrace(); }
         * logger.info("out the method weixinInit");
         */

        if ("get".equals(request.getMethod().toLowerCase())) {
            //
            String signature = request.getParameter("signature");
            //
            String timestamp = request.getParameter("timestamp");
            //
            String nonce = request.getParameter("nonce");
            //
            String echostr = request.getParameter("echostr");

            PrintWriter out = response.getWriter();
            //
            if (SignUtil.checkSignature(signature, timestamp, nonce)) {
                out.print(echostr);
            }
            out.close();
            out = null;
        } else {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");

            //
            String signature = request.getParameter("signature");
            String timestamp = request.getParameter("timestamp");
            String nonce = request.getParameter("nonce");

            PrintWriter out = response.getWriter();
            //
            if (SignUtil.checkSignature(signature, timestamp, nonce)) {

                String respXml = CoreService.processRequest(request);
                out.print(respXml);
            }
            out.close();
            out = null;
        }

    }

    /**
     * 获取oauth2授权地址
     * 
     * @return
     */
    public String oauth2() {
        logger.info("eoems entry the method oauth2");
        String code = request.getParameter("code");
        logger.info("get code from reques the parameter  request.getParameterCode value is ：" + code);
        // 获取oauth2的地址 ，这个地址为什么要先给一个值，下次来的时候直接调用不用吗，因为是固定的
        String oauth2_base = ConfigUtil.OAUTH2_BASE;

        logger.info("----------------------------eoems entry the metho  oauth2 -----------------" + oauth2_base);
        request.getSession().setAttribute("oauth2_base", oauth2_base);
        // request.setAttribute("code", code);
        request.getSession().setAttribute("code", code);
        logger.info("when entry code value oauth2 :" + code);
        logger.info("----------------------------eoems set oauth2_base to request  goto /WEB-INF/oauth2.jsp-----oauth2_base:"
                + oauth2_base);
        return "oauth2";
    }

    /**
     * 授权完毕获取openId
     * 
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public void oauth2Base() throws ServletException, IOException {
        // logger.info("进入 WeixinAction ===== oauth2Base()方法");
        logger.info("requestURL = " + request.getSession().getAttribute("requestURL").toString());

        // // 将请求、响应的编码均设置为UTF-8（防止中文乱码）

        request = ServletActionContext.getRequest();
        response = ServletActionContext.getResponse();

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        code = request.getParameter("code");
        logger.info("微信浏览器返回  request.getParameter(code) = " + code);

        String strState = request.getParameter("state");
        logger.info("返回的 state =  " + strState);

        if (null == code || code.equals("authdeny") || null == strState || strState.isEmpty()) {
            logger.info("用户拒绝授权");
            request.setAttribute("errcode", 1);
            request.setAttribute("errmsg", "用户拒绝授权");
            try {
                response.sendRedirect("http://yiqidongba.cn/p2pstock");// 跳回首页
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String strAccessToken = null;
        String strUserOpenId = null;
        String strNickName = "";
        String strSex = "";// 1 man;2 female;3 unknown;
        String strAppId = Constant.APPID;// FIXME
        String strAppSecret = Constant.APPSECRET;
        String strHeadimgurl = "";
        logger.info("获取 AccessTokenAnUid  前准备参数为 parameter code( " + code + " ) " + " strAppId ( " + strAppId + ") "
                + " strAppSecret ( " + strAppSecret + " ) ");
        Map<String, String> mapAccessToken = getAccessTokenAndUid(code, strAppId, strAppSecret);
        if (null == mapAccessToken || null != mapAccessToken.get("errcode")) {
            logger.info("获取访问token错误");
            if (null != mapAccessToken) {
                request.setAttribute("errcode", mapAccessToken.get("errcode"));
                request.setAttribute("errmsg", mapAccessToken.get("errmsg"));
            }
            try {
                response.sendRedirect("http://yiqidongba.cn/p2pstock");// 跳回首页
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        strAccessToken = mapAccessToken.get("access_token");
        strUserOpenId = mapAccessToken.get("openid");
        logger.info("返回 access_token = " + strAccessToken + "  openid = " + strUserOpenId);
        request.getSession().setAttribute(Constant.getSession_user_weixinid(), strUserOpenId);
        Map<String, String> mapUserInfo = getUserInfo(strAccessToken, strUserOpenId);
        if (null != mapUserInfo) {
            if (null != mapUserInfo.get("errcode")) {
                logger.info("获取用户信息错误");
                request.setAttribute("errcode", mapUserInfo.get("errcode"));
                request.setAttribute("errmsg", mapUserInfo.get("errmsg"));
                try {
                    response.sendRedirect("http://yiqidongba.cn/p2pstock");// 跳回首页
                    return;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            //TODO 城市判定，暂时写为北京
            request.getSession().setAttribute("city", "北京");
            
//            if (mapUserInfo.get("province") != null && !mapUserInfo.get("province").equals("")) {
//                logger.info("session save city   " + mapUserInfo.get("province"));
//                request.getSession().setAttribute("city", mapUserInfo.get("province"));
//            } else {
//                request.getSession().setAttribute("city", "北京");
//            }

            strNickName = mapUserInfo.get("nickname");
            strSex = mapUserInfo.get("sex");
            strHeadimgurl = mapUserInfo.get("headimgurl");
            if (strHeadimgurl != null & !"".equals(strHeadimgurl)) {
                strHeadimgurl = strHeadimgurl.substring(0, strHeadimgurl.lastIndexOf("/") + 1);
                strHeadimgurl = strHeadimgurl + "46";
                // province:北京 city:朝阳 country:中国
            }
        }
        logger.info("nickName: " + strNickName + "  strUserOpenId: " + strUserOpenId + "  sex: " + strSex
                + "  strHeadimgurl: " + strHeadimgurl);

        // 根据OpenId查询用户是否存在
        // String strUserOpenId = "owKODv9bmHnaMyaTrB4eJACYZcO8";// 测试用，我的OpenId
        // String strNickName = "YF";
        // String strHeadimgurl =
        // "http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0";
        // strHeadimgurl = strHeadimgurl.substring(0,
        // strHeadimgurl.lastIndexOf("/") + 1);
        // strHeadimgurl = strHeadimgurl + "46";
        // request.getSession().setAttribute("city", "北京");
        // session 存储OpenId

        request.getSession().setAttribute(Constant.getSession_user_weixinid(), strUserOpenId);
        UserInfoVo userVo = usercService.queryUserByWeixinOpenId(strUserOpenId);
        if (userVo.getUser_id() != null && !"".equals(userVo.getUser_id())) {
            // 如果有OpenId对应的用户，取出userId存入session
            request.getSession().setAttribute("userid", userVo.getUser_id());
        } else {
            // 如果OpenId没有对应的用户，自动创建一个
            User user = new User();
            user.setWeixin_id(strUserOpenId);
            user.setUser_name(strNickName);
            user.setUser_nickname(strNickName);
            user.setUser_headimgurl(strHeadimgurl);
            String userid = registService.registUser(user);
            request.getSession().setAttribute("userid", userid);
            logger.info("创建新用户  userid =" + userid);
            logger.info("创建新用户  userOpenid =" + strUserOpenId);
        }

        String requestURL = request.getSession().getAttribute("requestURL").toString();
        logger.info("返回原地址  requestURI = " + requestURL);
        try {
            response.sendRedirect(requestURL);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public RegistService getRegistService() {
        return registService;
    }

    public void setRegistService(RegistService registService) {
        this.registService = registService;
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

    public List<EnrollVo> getEnrollVoList() {
        return enrollVoList;
    }

    public void setEnrollVoList(List<EnrollVo> enrollVoList) {
        this.enrollVoList = enrollVoList;
    }

    public String getOauth2_base() {
        return oauth2_base;
    }

    public void setOauth2_base(String oauth2_base) {
        this.oauth2_base = oauth2_base;
    }

    public WeiXinOauth2Token getWeiXinOauth2Token() {
        return weiXinOauth2Token;
    }

    public void setWeiXinOauth2Token(WeiXinOauth2Token weiXinOauth2Token) {
        this.weiXinOauth2Token = weiXinOauth2Token;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private Map<String, String> getAccessTokenAndUid(String strCode, String strAppId, String strAppSecret) {
        String responseDate = "";
        Map<String, String> token = null;

        PostMethod postMethod = new PostMethod(Constant.strAccessTokenUrl);
        postMethod.addParameter("appid", strAppId);
        postMethod.addParameter("secret", strAppSecret);
        postMethod.addParameter("code", strCode);
        postMethod.addParameter("grant_type", "authorization_code");

        HttpClient client = new HttpClient();
        try {
            client.executeMethod(postMethod);
            logger.info("postMethod " + postMethod);
            responseDate = postMethod.getResponseBodyAsString();
            logger.info("responseDate = postMethod.getResponseBodyAsString()");
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }

        if (responseDate.trim().length() > 0) {
            token = new HashMap<String, String>();
            JSONObject jsonData = JSONObject.fromObject(responseDate);
            if (jsonData.has("errcode")) {
                logger.error("Get access token fail,reason:" + jsonData.getString("errmsg"));
                token.put("errcode", jsonData.getString("errcode"));
                token.put("errmsg", jsonData.getString("errmsg"));
                return null;
            }

            logger.info("Get access_token:" + jsonData.getString("access_token") + ";openid:"
                    + jsonData.getString("openid"));
            token.put("access_token", jsonData.getString("access_token"));
            token.put("openid", jsonData.getString("openid"));
        }
        return token;
    }

    /**
     * 参数 说明 subscribe 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。 openid
     * 用户的标识，对当前公众号唯一 nickname 用户的昵称 sex 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知 city
     * 用户所在城市 country 用户所在国家 province 用户所在省份 language 用户的语言，简体中文为zh_CN
     * headimgurl
     * 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空
     * 。若用户更换头像，原有头像URL将失效。 subscribe_time 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
     * unionid 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。详见：获取用户个人信息（UnionID机制） remark
     * 公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注 groupid 用户所在的分组ID
     */
    private Map<String, String> getUserInfo(String strAccessToken, String strOpenId) {
        String responseDate = "";
        Map<String, String> token = null;

        PostMethod postMethod = new PostMethod(Constant.strGetUserInfoUrl);
        logger.info("get userinfo url ");
        postMethod.addParameter("access_token", strAccessToken);
        postMethod.addParameter("openid", strOpenId);
        postMethod.addParameter("lang", "zh_CN");

        HttpClient client = new HttpClient();
        try {
            client.executeMethod(postMethod);
            responseDate = new String(postMethod.getResponseBody(), "utf-8");
            logger.info("postMethod.getResponseBody() = " + postMethod.getResponseBody());
            logger.info("-------------------------");
            logger.info("responseDate = " + responseDate);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        if (responseDate.trim().length() > 0) {
            token = new HashMap<String, String>();
            JSONObject jsonData = JSONObject.fromObject(responseDate);
            if (jsonData.has("errcode")) {
                logger.info("Get user info fail,reason:[" + jsonData.getString("errcode") + "]"
                        + jsonData.getString("errmsg"));
                // 这个是没用户授权，不能取用户信息的返回错误码，文档没提及，随时可能变的
                if (48001 == jsonData.getLong("errcode")) {
                    logger.info("Can't get userinfo,but wo can continue.");
                    return null;
                }
                token.put("errcode", jsonData.getString("errcode"));
                token.put("errmsg", jsonData.getString("errmsg"));
                return token;
            }

            if (!jsonData.has("openid") || !jsonData.has("nickname")) {
                logger.info("No user info available");
                return null;
            }
            logger.info("weixin web auth,user:" + jsonData.getString("nickname") + " sex:" + jsonData.getString("sex")
                    + " province:" + jsonData.getString("province") + " city:" + jsonData.getString("city")
                    + " country:" + jsonData.getString("country"));

            token.put("openid", jsonData.getString("openid"));
            token.put("nickname", jsonData.getString("nickname"));
            token.put("sex", jsonData.getString("sex"));
            token.put("province", jsonData.getString("province"));
            token.put("city", jsonData.getString("city"));
            token.put("country", jsonData.getString("country"));
            token.put("headimgurl", jsonData.getString("headimgurl"));
            // token.put("subscribe", jsonData.getString("subscribe"));//用户是否关注

        }
        return token;
    }

    public String test2() {
        String url = GetWeiXinCode.getCodeRequest(Constant.SCOPE, Constant.REDIRECT_URI);
        logger.info("jumpCodeRequet ==  url eoems" + url);

        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader((ServletInputStream) request.getInputStream()));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String line = null;
        StringBuilder sb = new StringBuilder();
        try {
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("支付结果通知的数据" + sb.toString());

        return "oauth2_success";
    }

    public String testGetOpenid() throws Exception {
        // 让用户授权，测试在浏览器里面打开和通过分享或是推送的消息中打开
        // 分享的url为
        // http://www.shuttler.cn/p2pstock/activity/testGetOpenid_activityMgrAction.action
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        String strWeixinCallbackCode = null;
        String strParamers = null;
        logger.info("eoems weixin re invoke to  testGetopenid method ");
        strWeixinCallbackCode = request.getParameter("code");
        logger.info("eoems return code:" + strWeixinCallbackCode);
        strParamers = request.getParameter("state");
        logger.info("eoems return stat parameter :" + strParamers);
        if (strWeixinCallbackCode.equals("authdeny")) {
            logger.info("user autheny is find -----------code");
        } else {
            logger.info("user autheny is not fine find -----------code");
        }

        String strAccessToken = null;
        String strUserOpenId = null;
        String strNickName = "";
        String strSex = "";// 1 man;2 female;3 unknown;
        String strAppId = Constant.APPID;// FIXME
        String strAppSecret = Constant.APPSECRET;

        Map<String, String> mapAccessToken = getAccessTokenAndUid(strWeixinCallbackCode, strAppId, strAppSecret);
        if (null == mapAccessToken || null != mapAccessToken.get("errcode")) {
            logger.info(" get token  -- fail fail fail ");
            if (null != mapAccessToken) {
                request.setAttribute("errcode", mapAccessToken.get("errcode"));
                request.setAttribute("errmsg", mapAccessToken.get("errmsg"));
            }
            return "getUserErr";
        }
        strAccessToken = mapAccessToken.get("access_token");
        strUserOpenId = mapAccessToken.get("openid");
        request.getSession().setAttribute(Constant.getSession_user_weixinid(), strUserOpenId);
        Map<String, String> mapUserInfo = getUserInfo(strAccessToken, strUserOpenId);
        if (null != mapUserInfo) {
            if (null != mapUserInfo.get("errcode")) {
                logger.info(" eoems get user error");
                request.setAttribute("errcode", mapUserInfo.get("errcode"));
                request.setAttribute("errmsg", mapUserInfo.get("errmsg"));
                return "getUserErr";
            }
            strNickName = mapUserInfo.get("nickname");
            strSex = mapUserInfo.get("sex");

        }

        logger.info(" eoems weixin web auth,user:" + strNickName + "strUserOpenId:" + strUserOpenId + " sex:" + strSex);

        String userIds = (String) request.getSession().getAttribute(Constant.getSession_userid());
        if (userIds != null && !"".equals(userIds.trim())) {
            UserInfoVo userInfo = usercService.queryUserInfo(userIds);
            if (userInfo != null) {
                if (userInfo.getUser_weixin_id() != null && userInfo.getUser_weixin_id().equals(strUserOpenId)) {
                } else if (userInfo.getUser_weixin_id() != null && !userInfo.getUser_weixin_id().equals(strUserOpenId)) {
                    logger.info("user's weixin open id   different with auth get user weixin open id  find reason ");
                    throw new Exception("data base  weixin open id be diffent with get from internet");
                } else if (userInfo.getUser_weixin_id() == null) {
                    // update User
                    userInfo.setUser_weixin_id(strUserOpenId);
                    usercService.saveUserinfo(userInfo);
                    logger.info("update user to the date base  user id =" + userIds);
                } else {
                    throw new Exception("no way ");
                }
            } else {
                throw new Exception("not find user in database with userid " + userIds);
            }

        }
        return "";

    }

    /**
     * 授权页面
     * 
     * @description
     * @version
     * @title
     * @author
     * @return
     */
    public void Oauth2API() {
        request = ServletActionContext.getRequest();
        // 拼接微信回调地址

        // String backUrl =
        // "http://localhost/p2pstock/weixin/oauth2Base_weixinAction.action";
        String backUrl = Constant.REDIRECT_URI;

        String url = GetWeiXinCode.getCodeRequest(Constant.SCOPE, backUrl);
        logger.info("jumpCodeRequet ==" + url);
        HttpServletResponse response = ServletActionContext.getResponse();
        try {
            response.sendRedirect(url);
            // response.sendRedirect(backUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String failedpay() {

        try {
            response.sendRedirect("www.baidu.com");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    public String unWeixinBrowser() {
        return "unweixin";
    }

}
