package com.p2p.webapp.weixin.tool.service;

import java.util.Date;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.p2p.webapp.common.constant.Constant;
import com.p2p.webapp.user.regist.dto.UserDto;
import com.p2p.webapp.user.regist.entity.User;
import com.p2p.webapp.user.regist.service.RegistService;
import com.p2p.webapp.weixin.action.WeixinAction;
import com.p2p.webapp.weixin.tool.message.resp.TextMessage;
import com.p2p.webapp.weixin.tool.util.MessageUtil;

/**
 * 核心服务类
 * 
 * @author liufeng
 * @date 2013-12-02
 */
public class CoreService {
	public static Logger logger = LoggerFactory.getLogger(WeixinAction.class);
	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return xml
	 */
	public static String processRequest(HttpServletRequest request) {
		// xml格式的消息数据
		String respXml = null;
		// 默认返回的文本消息内容
		String respContent = "发送任意文本，我们开始聊天吧！";
		try {
			// 调用parseXml方法解析请求消息
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// 发送方帐号
			String fromUserName = requestMap.get("FromUserName");
			// 开发者微信号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			// 消息创建时间
			String createTime = requestMap.get("CreateTime");
			
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

			// 文本消息
		if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
			String content = requestMap.get("Content");
			//respContent = ChatService.chat(fromUserName, createTime, content);
			respContent = "您发送的是文本消息";
		}
		// 图片消息
		else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
			respContent = "您发送的是图片消息！";
		}
		// 语音消息
		else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
			respContent = "您发送的是语音消息！";
		}
		// 视频消息
		else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
			respContent = "您发送的是视频消息！";
		}
		// 地理位置消息
		else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
			respContent = "您发送的是地理位置消息！";
		}
		// 链接消息
		else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
			respContent = "您发送的是链接消息！";
		}
		// 事件推送
		else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
			// 事件类型
			String eventType = requestMap.get("Event");
			// 关注
			if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
				logger.info("eoems event subscribe ========subscribe");
				//search userhas ,if not add user by openid 
				ServletContext  actionContext = request.getSession().getServletContext();
	             //ServletContext context = (ServletContext) actionContext.get(StrutsStatics.SERVLET_CONTEXT);   
	         	ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(actionContext);
	         	RegistService registService =(RegistService)ctx.getBean("registService");
	         	User user  = registService.checkWeiXinUser(fromUserName);
	         	if(user == null){
	         		user = new User();
	        		//用户状态 
	         		user.setUser_status(Constant.getUser_status_active());
	        		//用户类型 A 管理员 G普通用户
	         		user.setUser_type("A");
	         		user.setWeixin_id(fromUserName);    	    
	        	    String regis = registService.registUser(user);
	        	    if(regis !=null &&"0".equals(regis)){
	        	      User user2 = registService.checkWeiXinUser(fromUserName);
	        	     /* request.getSession().setAttribute(Constant.getSession_userid(), user2.getUser_id());  
	        	      request.getSession().setAttribute(Constant.getSession_user_weixinid(), fromUserName); 
	        	      request.getSession().setAttribute(Constant.getSession_phone(),user2.getPhone());*/
	        	    }else{
	        	    	throw new Exception("注册用户出现异常");
	        	    } 
	        	 }else{
	               /* request.getSession().setAttribute(Constant.getSession_userid(), user.getUser_id());  
	       	        request.getSession().setAttribute(Constant.getSession_user_weixinid(), fromUserName); 
	       	        request.getSession().setAttribute(Constant.getSession_phone(),user.getPhone());*/
	        	 }
				respContent = "谢谢您的关注！";
			}
			// 取消关注
			else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
				// TODO 取消订阅后用户不会再收到公众账号发送的消息，因此不需要回复
			}
			// 扫描带参数二维码
			else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {
				// TODO 处理扫描带参数二维码事件
			}
			// 上报地理位置
			else if (eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)) {
				// TODO 处理上报地理位置事件
			}
			// 自定义菜单
			else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
				// TODO 处理菜单点击事件
			}
		}
		// 设置文本消息的内容
		textMessage.setContent(respContent);
		// 将文本消息对象转换成xml
		respXml = MessageUtil.messageToXml(textMessage);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return respXml;
	}
}