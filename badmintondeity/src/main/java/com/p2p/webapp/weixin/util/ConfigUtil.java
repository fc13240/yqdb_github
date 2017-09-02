package com.p2p.webapp.weixin.util;

import com.p2p.webapp.common.constant.Constant;

public class ConfigUtil {
	/**
	 * 服务号相关信息
	 */
	public final static String	APPID				= Constant.APPID;																														// 服务号的应用号
	public final static String	APP_SECRECT			= Constant.APPSECRET;																														// 服务号的应用密码
	public final static String	TOKEN				= Constant.TOKEN;																														// 服务号的配置token
	public final static String	MCH_ID				= "1290813501";																														// 商户号
	public final static String	API_KEY				= "wxef18a13927bc52cdmoorecubemoore";																														// API密钥
	public final static String	SIGN_TYPE			= "MD5";																													// 微信支付证书存放路径地址

	// 以上为需要改的内容

	// 微信支付统一接口的回调action
	public final static String	NOTIFY_URL			= "http://yiqidongba.cn/p2pstock/weixin/paySuccess_payAction.action";
	// 微信支付成功支付后跳转的地址
	public final static String	SUCCESS_URL			= "http://yiqidongba.cn/p2pstock/weixin/gotopaysuccessful_payAction.action";
	// oauth2授权时回调action
	public final static String	REDIRECT_URI_BASE	= "http://yiqidongba.cn/p2pstock/weixin/oauth2Base_weixinAction";

	// oauth2授权（第一步获取code用到的地址）
	public final static String	OAUTH2_BASE			= "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
															+ APPID
															+ "&redirect_uri="
															+ CommonUtil.urlEncodeUTF8(REDIRECT_URI_BASE)
															+ "&response_type=code&scope=snsapi_base&state=pay#wechat_redirect";

	// oauth2授权接口(GET)
	public final static String	OAUTH2_URL			= "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

	// 微信支付统一接口(POST)
	public final static String	UNIFIED_ORDER_URL	= "https://api.mch.weixin.qq.com/pay/unifiedorder";

}
