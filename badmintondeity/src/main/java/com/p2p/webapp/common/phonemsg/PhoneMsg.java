package com.p2p.webapp.common.phonemsg;


import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.p2p.webapp.common.phonemsg.SmsSendResponse;
import com.p2p.webapp.common.phonemsg.ChuangLanSmsUtil;
import com.p2p.webapp.common.constant.Constant;
import com.p2p.webapp.common.http.HttpTools;
import com.p2p.webapp.common.http.impl.HttpToolsImpl;
import com.p2p.webapp.common.phonemsg.SmsSendRequest;


public class PhoneMsg {

	//写日志对象
	public static Logger logger = LoggerFactory.getLogger(PhoneMsg.class);
	/**
	 * 实时发送单条手机短信
	 * @param phoneno
	 * @param msg
	 * @return
	 */
	/*
	public String sendMsg(String phoneno,String msg){
		
		HttpTools httptool = new HttpToolsImpl();
		
		String url = Constant.getCptsendadress();
		Map<String,Object> sendMap = new HashMap<String,Object>();
		
		try{
			sendMap.put("account", Constant.getCptaccount());
			sendMap.put("password", Constant.getCptpwd());
			sendMap.put("product", Constant.getProductid());
			sendMap.put("phone", phoneno);
			sendMap.put("msg", msg);
			if("0".equals(Constant.getCptflag())){
				String msgrlt = httptool.postRequest(url, sendMap);
				logger.debug("短信返回结果："+msgrlt);
			}else{
				logger.debug("模拟发送短信");
				return "0";
			}
		}catch(Exception e){
			return "-1";
		}
		return "0";
	}
	*/
	public String sendMsg(String phoneno,String msg) {

		//状态报告
		String report= "true";
		
		SmsSendRequest smsSingleRequest = new SmsSendRequest(Constant.getCptaccount(), Constant.getCptpwd(), msg, phoneno,report);
		
		String requestJson = JSON.toJSONString(smsSingleRequest);
		
		String response = ChuangLanSmsUtil.sendSmsByPost(Constant.getCptsendadress(), requestJson);
		
		SmsSendResponse smsSingleResponse = JSON.parseObject(response, SmsSendResponse.class);
		
		if("0".equals(Constant.getCptflag())){
			String msgrlt = response;
			logger.debug("短信返回结果："+ smsSingleResponse);
		}else{
			logger.debug("模拟发送短信");
			return "0";
		}
		
		return "0";

	}
	
	public String sendMsgList(List<MsgPo> msgList){
		
		
		return "0";
	}

}
