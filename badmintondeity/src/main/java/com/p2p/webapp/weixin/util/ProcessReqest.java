package com.p2p.webapp.weixin.util;

import java.util.Map;

import javax.jms.TextMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.StrutsStatics;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionContext;
import com.p2p.webapp.common.constant.Constant;
import com.p2p.webapp.user.regist.dto.UserDto;
import com.p2p.webapp.user.regist.service.RegistService;
import com.p2p.webapp.user.usercenter.dao.UserCenterDao;

public class ProcessReqest {
     public static String process(HttpServletRequest request,HttpServletResponse response) throws Exception{
        
         Map<String, String> map = RequestXML2Map.parseXml(request);
         String result = "";
         String msgType = map.get("MsgType");
         String respContent = "";
         //文本消息
         StringBuffer replyMsg = new StringBuffer();
         String returnXml = null;
         if (msgType.equals("text")) {
        	 //eoems 注释掉了，这个地方用户接机器人接口，或是自己根据内容进行回复用户
           /* respContent = TulingRobot.robot(map.get("Content"));
            TextMessage textMessage = Map2Bean.parseText(map,respContent);
            result = Bean2ResponseXML.textMessageToXml(textMessage);
        */
        	 ServletContext  actionContext = request.getSession().getServletContext();
             //ServletContext context = (ServletContext) actionContext.get(StrutsStatics.SERVLET_CONTEXT);   
         	 ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(actionContext);
         	RegistService registService =(RegistService)ctx.getBean("registService");
         	
         	UserDto userdto = new UserDto();
    		//用户状态 
    		userdto.setUser_status(Constant.getUser_status_active());
    		//用户类型 A 管理员 G普通用户
    		userdto.setUser_type("G");
    		userdto.setLogin_pwd("111111");
    	    userdto.setWeixin_id(map.get("FromUserName").toString());
    	    return  "";
    		
        }
        //图片消息
        else if (msgType.equals("image")) {
            respContent = "";
            return null;
        }
        //声音消息
        else if (msgType.equals("voice")) {
            respContent = "";
            return null;
        }
        //视频消息
        else if (msgType.equals("video")) {
            respContent = "";
            return null;
        }
        //地理位置
        else if (msgType.equals("location")) {
            respContent = "";
            return null;
        }
        //事件类型
        else if (msgType.equals("event")) {
            String eventType = map.get("Event");
            //订阅
            if (eventType.equals("subscribe")) {
                respContent = "欢迎订阅我的公众号！";
               /* TextMessage textMessage = Map2Bean.parseText(map,respContent);
                result = Bean2ResponseXML.textMessageToXml(textMessage);*/
           
                //request.getcon
                return  respContent;
            }
            //取消订阅
            else if (eventType.equals("unsubscribe")) {
                // TODO
                return null;
            }
            //点击菜单
            else if (eventType.equals("CLICK")) {
                // TODO 
                return null;
            }
        }
        return result;
    }
}