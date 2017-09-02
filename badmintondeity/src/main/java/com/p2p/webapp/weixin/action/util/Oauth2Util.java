package com.p2p.webapp.weixin.action.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;

import com.p2p.webapp.user.login.action.LoginAction;
import com.p2p.webapp.weixin.bean.WeiXinOauth2Token;
import com.p2p.webapp.weixin.util.ConfigUtil;


public class Oauth2Util {
	   public static Logger logger = LoggerFactory.getLogger(LoginAction.class);
	/**
	 * 获取授权信息
	 * 
	 * @param code
	 * @return
	 */
	public static WeiXinOauth2Token getOauth2AccessToken(String code, StringBuffer json) {
		logger.info("Oauth2Util.getOauth2AccessToken(code, weiXinOauth2TokenJson) code is :"+code );
		logger.info("Oauth2Util.getOauth2AccessToken(code, weiXinOauth2TokenJson) json is :"+json );
		WeiXinOauth2Token wat = new WeiXinOauth2Token();
		String requestUrl = ConfigUtil.OAUTH2_URL.replace("CODE", code).replace("APPID", ConfigUtil.APPID)
				.replace("SECRET", ConfigUtil.APP_SECRECT);
		logger.info( "the url is send to weixin to requestUrl  url "+ requestUrl);
		JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
		logger.info("CommonUtil.httpsRequest(requestUrl, GET, null): return xml string"+jsonObject.toString());
		json.append(jsonObject.toString());
		if (null != jsonObject) {
			try {
				wat = new WeiXinOauth2Token();
				wat.setAccessToken(jsonObject.getString("access_token"));
				wat.setExpiresIn(jsonObject.getInt("expires_in"));
				wat.setRefeshToken(jsonObject.getString("refresh_token"));
				wat.setOpenId(jsonObject.getString("openid"));
				wat.setScope(jsonObject.getString("scope"));
			} catch (Exception e) {
				wat = null;
				String errorCode = jsonObject.getString("errcode");
				String errorMsg = jsonObject.getString("errmsg");
			}

		}
		logger.info("Oauth2Util.getOauth2AccessToken(code, weiXinOauth2TokenJson) return WeiXinOauth2Token"+wat.toString());
		return wat;
	}

}
