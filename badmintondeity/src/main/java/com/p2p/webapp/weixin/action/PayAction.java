package com.p2p.webapp.weixin.action;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.security.KeyStore;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.struts2.ServletActionContext;
import org.jdom.JDOMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.p2p.webapp.activity.activitymgr.service.ActivityMgrService;
import com.p2p.webapp.activity.activitymgr.vo.ActivityVo;
import com.p2p.webapp.common.base.BaseAction;
import com.p2p.webapp.common.constant.Constant;
import com.p2p.webapp.common.util.UUIDHexGeneratorEx;
import com.p2p.webapp.enroll.enrollmgr.service.EnrollMgrService;
import com.p2p.webapp.enroll.enrollmgr.vo.EnrollVo;
import com.p2p.webapp.settle.settlemgr.service.SettleMgrService;
import com.p2p.webapp.settle.settlemgr.vo.SettleVo;
import com.p2p.webapp.user.regist.service.RegistService;
import com.p2p.webapp.user.usercenter.dao.UserCenterDao;
import com.p2p.webapp.user.usercenter.service.UserCenterService;
import com.p2p.webapp.user.usercenter.vo.UserCreditDetailVo;
import com.p2p.webapp.user.usercenter.vo.UserCreditVo;
import com.p2p.webapp.user.usercenter.vo.UserInfoVo;
import com.p2p.webapp.weixin.util.CommonUtil;
import com.p2p.webapp.weixin.util.ConfigUtil;
import com.p2p.webapp.weixin.util.IpUtil;
import com.p2p.webapp.weixin.util.PayCommonUtil;
import com.p2p.webapp.weixin.util.XMLUtil;

public class PayAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    public static Logger logger = LoggerFactory.getLogger(PayAction.class);

    private EnrollMgrService enrollMgrService;
    private ActivityMgrService activityMgrService;
    private UserCenterService userCenterService;// 用户中心Service
    private SettleMgrService settleMgrService;
    private EnrollVo enrollVo;
    private ActivityVo activityVo;
    private UserCreditDetailVo userCreditDetailVo;
    private List<EnrollVo> enrollVoList;
    private String preOpenId_String;

    public String getPreOpenId_String() {
        return preOpenId_String;
    }

    public void setPreOpenId_String(String preOpenId_String) {
        this.preOpenId_String = preOpenId_String;
    }

    public void pay() {
        logger.info("entry payaction  pay 方法");
        enrollVo = enrollMgrService.queryEnrollDetailInfo(enrollVo);
        logger.info(userCreditDetailVo.getAmnt());
        String openid = request.getSession().getAttribute(Constant.getSession_user_weixinid()).toString();// 授权后获得
        String out_trade_no = enrollVo.getPay_id();
        // 微信支付以分计算
        BigDecimal bi = new BigDecimal(enrollVo.getCost());
        String total_fee = String.valueOf(bi.multiply(new BigDecimal(100)).intValue());
        logger.info("enrollVo.getCost()= " + enrollVo.getCost() + "  total_fee = " + total_fee);
        logger.info("openid = " + openid);
        HttpServletRequest request = ServletActionContext.getRequest();

        SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
        parameters.put("appid", ConfigUtil.APPID);
        parameters.put("mch_id", ConfigUtil.MCH_ID);
        parameters.put("nonce_str", PayCommonUtil.CreateNoncestr());
        parameters.put("body", "一起动吧-比赛报名");
        parameters.put("out_trade_no", out_trade_no);// 订单号，每次都不能一样
        parameters.put("total_fee", total_fee);
        parameters.put("spbill_create_ip", IpUtil.getIpAddrByRequest(request));
        parameters.put("notify_url", ConfigUtil.NOTIFY_URL);
        parameters.put("trade_type", "JSAPI");
        parameters.put("openid", openid);

        // 必填————签名
        String sign = PayCommonUtil.createSign("UTF-8", parameters);
        parameters.put("sign", sign);
        logger.info("第一次签名 : " + sign);
        String requestXML = PayCommonUtil.getRequestXml(parameters);
        String result = CommonUtil.httpsRequest(ConfigUtil.UNIFIED_ORDER_URL, "POST", requestXML);
        logger.info("eoems order return  reult : " + result);
        try {
            Map<String, String> map = XMLUtil.doXMLParse(result);

            SortedMap<Object, Object> params = new TreeMap<Object, Object>();
            params.put("appId", ConfigUtil.APPID);
            params.put("timeStamp", Long.toString(new Date().getTime()));
            params.put("nonceStr", PayCommonUtil.CreateNoncestr());
            params.put("package", "prepay_id=" + map.get("prepay_id"));
            params.put("signType", ConfigUtil.SIGN_TYPE);
            String paySign = PayCommonUtil.createSign("UTF-8", params);
            logger.info("第二次签名  : " + paySign);
            params.put("packageValue", "prepay_id=" + map.get("prepay_id")); // 这里用packageValue是预防package是关键字在js获取值出错
            params.put("paySign", paySign); // paySign的生成规则和Sign的生成规则一致
            params.put("sendUrl", ConfigUtil.SUCCESS_URL); // 付款成功后跳转的页面
            String userAgent = request.getHeader("user-agent");
            char agent = userAgent.charAt(userAgent.indexOf("MicroMessenger") + 15);
            params.put("agent", new String(new char[] { agent }));// 微信版本号，用于前面提到的判断用户手机微信的版本是否是5.0以上版本。
            String json = JSONObject.fromObject(params).toString();
            logger.info("retrun json content  is " + json);

            response = ServletActionContext.getResponse();
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
            logger.info("send json content to page to call weixin pay");
            response.getWriter().flush();
            response.getWriter().close();

        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }// 解析微信返回的信息，以Map形式存储便于取值

    }

    public void paySuccess() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        InputStream inStream = request.getInputStream();
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, len);
        }
        logger.info("-------支付成功-------");

        outSteam.close();
        inStream.close();
        String result = new String(outSteam.toByteArray(), "utf-8");// 获取微信调用我们notify_url的返回信息
        Map<Object, Object> map = XMLUtil.doXMLParse(result);

        if (map.get("result_code").toString().equalsIgnoreCase("SUCCESS")) {
            String orderId = "";
            String transactionId = "";
            for (Object keyValue : map.keySet()) {
                if (keyValue.equals("out_trade_no")) {
                    orderId = (String) map.get(keyValue);// 订单号
                }
                if (keyValue.equals("transaction_id")) {
                    transactionId = (String) map.get(keyValue);// 交易号
                }
                logger.info(keyValue + "=" + map.get(keyValue));
            }

            // 支付成功修改订单状态
            enrollVo.setSettle_id(transactionId);// 结算编号 微信提供
            enrollVo.setStatus(Constant.ENROLL_STATUS_PAY);// 状态改为1
            enrollVo.setPay_date(new Date());
            enrollVo.setPay_type(Constant.PAY_TYPE_WEIXIN);// 微信支付为1
            enrollMgrService.updateEnroll(enrollVo);// 更新报名记录
            enrollVo = enrollMgrService.queryEnrollDetailInfo(enrollVo);
            logger.debug("使用成绩数量为  =====" + userCreditDetailVo.getAmnt());
            if (!userCreditDetailVo.getAmnt().equals("0")) {
                int amnt = Integer.parseInt(userCreditDetailVo.getAmnt());
                // 更新积分记录
                UserCreditVo userCreditVo = new UserCreditVo();
                userCreditVo.setUser_id(enrollVo.getUser_id());
                userCreditVo = userCenterService.queryUserCredit(userCreditVo);
                int acc_freeze = Integer.parseInt(userCreditVo.getAcc_freeze());// 获取原冻结数量
                int acc_balance = Integer.parseInt(userCreditVo.getAcc_balance());// 获取原成绩数量
                logger.debug("原成绩数量为  =====" + acc_balance + "    原冻结数量为  =====" + acc_freeze);
                int new_balance = acc_balance - amnt;
                int new_freeze = acc_freeze - amnt;
                // 总数量、冻结数量扣除本次使用数量
                userCreditVo.setAcc_balance(String.valueOf(new_balance));
                userCreditVo.setAcc_freeze(String.valueOf(new_freeze));
                userCenterService.updateUserCredit(userCreditVo);// 修改用户积分
                logger.debug("扣除用户成绩数量  ===== 当前数量可用数量为    " + new_balance);

                // 修改积分使用记录
                userCreditDetailVo.setEnroll_id(enrollVo.getEnroll_id());
                userCreditDetailVo.setAcc_tran_type(Constant.ACC_TRAN_TYPE_DECREASE);
                userCenterService.updateCreditDetailByEnrollId(userCreditDetailVo);

            }

            response.getWriter().write(PayCommonUtil.setXML("SUCCESS", "")); // 告诉微信服务器，我收到信息了，不要在调用回调action了
            System.out.println("-------------" + PayCommonUtil.setXML("SUCCESS", ""));
        }
    }

    public static void main(String arg[]) {
        PayAction payAction = new PayAction();
        payAction.refund();
    }

    /**
     * @description 退款
     * @version
     * @title
     * @author
     */
    public void refund() {
        logger.info("退款refund()开始");
        Map<String, String> backMap = new HashMap<String, String>();
        enrollVo = enrollMgrService.queryEnrollDetailInfo(enrollVo);
        SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
        parameters.put("appid", ConfigUtil.APPID);
        parameters.put("mch_id", ConfigUtil.MCH_ID);
        parameters.put("nonce_str", PayCommonUtil.CreateNoncestr());
        parameters.put("transaction_id", enrollVo.getSettle_id());
        parameters.put("out_trade_no", enrollVo.getPay_id());
        // 退款订单号 将原订单号A改为R
//        String out_refund_no = enrollVo.getPay_id().replace(Constant.TRANSACTION_CODE_ENROLL,
//                Constant.TRANSACTION_CODE_REFUND);
        parameters.put("out_refund_no", UUIDHexGeneratorEx.gen17());
        // 微信支付单位为分
        BigDecimal bi = new BigDecimal(enrollVo.getCost());
        String total_fee = String.valueOf(bi.multiply(new BigDecimal(100)).intValue());
        parameters.put("total_fee", total_fee);
        parameters.put("refund_fee", total_fee);
        parameters.put("op_user_id", ConfigUtil.MCH_ID);
        // 必填————签名
        String sign = PayCommonUtil.createSign("UTF-8", parameters);
        parameters.put("sign", sign);
        String reuqestXml = PayCommonUtil.getRequestXml(parameters);
        try {
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            String certPath = getCertPath();// 证书的路径
            FileInputStream instream = new FileInputStream(new File(certPath));
            keyStore.load(instream, ConfigUtil.MCH_ID.toCharArray());
            instream.close();
            SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, ConfigUtil.MCH_ID.toCharArray())
                    .build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" },
                    null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
            CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
            // 退款接口
            HttpPost httpPost = new HttpPost("https://api.mch.weixin.qq.com/secapi/pay/refund");
            logger.info("executing request" + httpPost.getRequestLine());
            StringEntity reqEntity = new StringEntity(reuqestXml);
            // 设置类型
            reqEntity.setContentType("application/x-www-form-urlencoded");
            httpPost.setEntity(reqEntity);
            CloseableHttpResponse res = httpclient.execute(httpPost);
            HttpEntity entity = res.getEntity();
            if (entity != null) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
                String result = "";
                String text = "";
                while ((text = bufferedReader.readLine()) != null) {
                    result = result + text;
                }
                logger.info(result);
                Map<Object, Object> map = XMLUtil.doXMLParse(result);
                if (map.get("result_code").toString().equalsIgnoreCase("SUCCESS")) {
                    // 支付成功修改订单状态
                    String transactionId = "";
                    for (Object keyValue : map.keySet()) {
                        if (keyValue.equals("transaction_id")) {
                            transactionId = (String) map.get(keyValue);// 交易号
                        }
                        logger.info(keyValue + "=" + map.get(keyValue));
                    }
                    // enrollVo.setSettle_id(transactionId);// 结算编号 微信提供
                    enrollVo.setStatus(Constant.ENROLL_STATUS_REFUND);// 状态改为2退款
                    enrollVo.setPay_date(new Date());
                    enrollVo.setPay_type(Constant.PAY_TYPE_WEIXIN);// 微信支付为1
                    enrollMgrService.updateEnroll(enrollVo);// 更新报名记录

                    userCreditDetailVo = new UserCreditDetailVo();
                    userCreditDetailVo = userCenterService.queryCreditDetailByEnrollId(enrollVo.getEnroll_id());
                    if (userCreditDetailVo.getAmnt() != null) {
                        int amnt = Integer.parseInt(userCreditDetailVo.getAmnt());
                        // 更新积分记录
                        UserCreditVo userCreditVo = new UserCreditVo();
                        userCreditVo.setUser_id(enrollVo.getUser_id());
                        userCreditVo = userCenterService.queryUserCredit(userCreditVo);
                        int acc_balance = Integer.parseInt(userCreditVo.getAcc_balance());// 获取原成绩数量
                        logger.debug("原成绩数量为  =====" + acc_balance);
                        int new_balance = acc_balance + amnt;
                        // 总数量增加订单使用的数量
                        userCreditVo.setAcc_balance(String.valueOf(new_balance));
                        userCenterService.updateUserCredit(userCreditVo);// 修改用户积分
                        logger.debug("退还用户成绩数量  ===== 当前数量可用数量为    " + new_balance);

                        // 增加积分退还记录
                        UserCreditDetailVo newCreditDetailVo = new UserCreditDetailVo();
                        newCreditDetailVo.setAccount_id(userCreditVo.getAccount_id());
                        newCreditDetailVo.setUser_id(enrollVo.getUser_id());
                        newCreditDetailVo.setBs_type(Constant.BS_TYPE_SYSTEM);
                        newCreditDetailVo.setBs_no("0");
                        newCreditDetailVo.setEnroll_id("");
                        newCreditDetailVo.setAcc_tran_type(Constant.ACC_TRAN_TYPE_INCREASE);
                        newCreditDetailVo.setAmnt(userCreditDetailVo.getAmnt());
                        userCenterService.addUserCreditDetail(newCreditDetailVo);
                    }

                }
            }
            EntityUtils.consume(entity);
            httpclient.close();
            res.close();
            backMap.put("result", "0");
        } catch (Exception e) {
            logger.info(e.toString());
            backMap.put("result", "-1");
            e.printStackTrace();
        } finally {
            logger.info("退款refund()结束");
            outJson(backMap);
        }
    }

    /**
     * @description 结算 企业付款
     * @version
     * @title
     * @author
     */
    public void enterprisesPay() {
        logger.info("企业付款enterprisesPay()开始");
        Map<String, String> backMap = new HashMap<String, String>();
        String activityId = request.getParameter("activityId");
        SettleVo queryVo = new SettleVo();
        queryVo.setActivity_id(activityId);
        queryVo = settleMgrService.querySettleDetailInfo(queryVo);
        UserInfoVo userInfoVo = userCenterService.queryUserInfo(queryVo.getUser_id());
        SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
        parameters.put("mch_appid", ConfigUtil.APPID);// 微信分配的公众账号ID（企业号corpid即为此appId）
        parameters.put("mchid", ConfigUtil.MCH_ID);// 微信支付分配的商户号
        parameters.put("nonce_str", PayCommonUtil.CreateNoncestr());// 随机串
        parameters.put("partner_trade_no", queryVo.getPay_no());// 退款订单号
        parameters.put("openid", userInfoVo.getUser_weixin_id());
        parameters.put("check_name", "NO_CHECK"); // 不校验真实姓名
        enrollVo = new EnrollVo();
        enrollVo.setActivity_id(activityId);
        // 微信支付单位为分
        BigDecimal bi = new BigDecimal(enrollMgrService.querySumMoney(enrollVo));
        String total_fee = String.valueOf(bi.multiply(new BigDecimal(100)).intValue());
        parameters.put("amount", total_fee); // 单位为分最低1元
        parameters.put("desc", "比赛结算"); // TODO 中文导致签名错误
        // parameters.put("spbill_create_ip",
        // IpUtil.getIpAddrByRequest(request));
        parameters.put("spbill_create_ip", "10.8.24.34");
        // 必填————签名
        String sign = PayCommonUtil.createSign("utf-8", parameters);
        parameters.put("sign", sign);
        String reuqestXml = PayCommonUtil.getRequestXml(parameters);
        try {
            reuqestXml = new String(reuqestXml.toString().getBytes(), "ISO8859-1");
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            String certPath = getCertPath();// 证书的路径
            FileInputStream instream = new FileInputStream(new File(certPath));
            keyStore.load(instream, ConfigUtil.MCH_ID.toCharArray());
            instream.close();

            SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, ConfigUtil.MCH_ID.toCharArray())
                    .build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" },
                    null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
            CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
            // 企业支付接口
            HttpPost httpPost = new HttpPost("https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers");
            logger.info("executing request" + httpPost.getRequestLine());
            StringEntity reqEntity = new StringEntity(reuqestXml);
            // 设置类型
            reqEntity.setContentType("application/x-www-form-urlencoded");
            httpPost.setEntity(reqEntity);
            CloseableHttpResponse res = httpclient.execute(httpPost);

            HttpEntity entity = res.getEntity();

            if (entity != null) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
                String result = "";
                String text = "";
                while ((text = bufferedReader.readLine()) != null) {
                    result = result + text;
                }
                logger.info(result);
                Map<Object, Object> map = XMLUtil.doXMLParse(result);
                if (map.get("result_code").toString().equalsIgnoreCase("SUCCESS")) {
                    SettleVo settleVo = new SettleVo();
                    settleVo.setActivity_id(activityId);
                    settleVo = settleMgrService.querySettleDetailInfo(settleVo);
                    settleVo.setSettle_status(Constant.SETTLE_STATUS_OK);// 结算状态为1:成功
                    settleVo.setPay_type(Constant.PAY_TYPE_WEIXIN);// 微信
                    settleMgrService.updateSettle(settleVo);

                    activityVo = new ActivityVo();
                    activityVo.setActivity_id(settleVo.getActivity_id());
                    activityVo = activityMgrService.queryActivityDetailInfo(activityVo);
                    activityVo.setActivity_status(Constant.ACTIVITY_STATUS_SETTLE);// 比赛状态为2-已结算
                    activityMgrService.updateActivity(activityVo);

                    logger.info("结算成功   settle 状态变为1 activity_status状态改为2");
                }
            }
            EntityUtils.consume(entity);
            httpclient.close();
            res.close();
            backMap.put("result", "0");
        } catch (Exception e) {
            logger.info(e.toString());
            backMap.put("result", "-1");
            e.printStackTrace();
        } finally {
            logger.info("企业付款enterprisesPay()结束");
            outJson(backMap);
        }
    }

    /**
     * 付款成功后的successful 界面
     * 
     * @return
     */
    public String gotopaysuccessful() {
        logger.info("微信支付成功支付后跳转的地址");
        return "gotopaysuccessful";
    }

    /**
     * @description 获取证书路径
     * @version
     * @title
     * @author
     * @return
     */
    public String getCertPath() {
        String classPath = PayAction.class.getResource("").getPath();
        logger.info("classPath---" + classPath);
        String certPath = "";
        // windows下
        if ("\\".equals(File.separator)) {
            logger.info("windows system");
            certPath = classPath.substring(1, classPath.indexOf("action"));
            certPath = certPath + "cert/apiclient_cert.p12";
            certPath = certPath.replace("/", "\\");
        }
        // linux下
        if ("/".equals(File.separator)) {
            logger.info("linux system");
            certPath = classPath.substring(0, classPath.indexOf("action"));
            certPath = certPath + "cert/apiclient_cert.p12";
            certPath = certPath.replace("\\", "/");
        }

        logger.info("certPath = " + certPath);
        return certPath;
    }

    private UserCenterDao usercenDao;

    public UserCenterDao getUsercenDao() {
        return usercenDao;
    }

    public void setUsercenDao(UserCenterDao usercenDao) {
        this.usercenDao = usercenDao;
    }

    private RegistService registService;

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

    public UserCenterService getUserCenterService() {
        return userCenterService;
    }

    public void setUserCenterService(UserCenterService userCenterService) {
        this.userCenterService = userCenterService;
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

}
