package com.bluelight.demo.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.bluelight.demo.consts.WxConfig;
import com.bluelight.demo.service.CallbackService;
import com.bluelight.demo.util.SHA1;


import com.bluelight.demo.web.controller.MenuController;

/**
 * 微信回调请求处理
 */
public class CallbackServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private CallbackService service = new CallbackService();
/*
	private void creatememumain() {  
		  
        //自定义菜单创建接口  
 
        String menuUrl="https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+WxConfig.TOKEN;  
  
        //button array  
        JSONArray btnArray=new JSONArray();  
        
        //首页按钮  
        JSONObject btn1Json=new JSONObject();  
        btn1Json.put("type","view");  
        btn1Json.put("name","一起动吧");  
        btn1Json.put("url","https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx315fa51c9bb4b39e&redirect_uri=http://yiqidongba.cn/p2pstock");  
        
        //我的按钮
        JSONObject btn2Json=new JSONObject();  
        btn2Json.put("type","view");  
        btn2Json.put("name","我的");  
        btn2Json.put("url","https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx315fa51c9bb4b39e&redirect_uri=/p2pstock/usercenter/userInit_userCenterAction.action?menucode=MYPAGE");  
       
        //设备列表按钮
        JSONObject btn3Json=new JSONObject();  
        btn3Json.put("type","view");  
        btn3Json.put("name","设备列表");  
        btn3Json.put("url","https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx315fa51c9bb4b39e&redirect_uri=/p2pstock/usercenter/userInit_userCenterAction.action?menucode=MYPAGE");  
        btnArray.add(btn1Json);  
        btnArray.add(btn2Json);  
        btnArray.add(btn3Json);  
  
        JSONObject json=new JSONObject();  
        json.put("button",btnArray);  
        
        PostMethod postMethod = new PostMethod(menuUrl);
        HttpClient client = new HttpClient();
        
        Logger log = Logger.getLogger("lavasoft");
		log.setLevel(Level.INFO);

		log.info(json.toString());
		
        try {
        	postMethod.setRequestBody(json.toString());
            client.executeMethod(postMethod);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //JSONObject jsonObject = HttpClientUtil.getInstance().httpPostRequest(menuUrl, json.toString());  
 
    }  
	*/
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			// 开发者接入验证
			String timestamp = req.getParameter("timestamp");
			String nonce = req.getParameter("nonce");
			String signature = req.getParameter("signature");
			String echostr = req.getParameter("echostr");
			
			//创建自定义菜单
			

			if (signature.equals(SHA1.gen(WxConfig.TOKEN, timestamp, nonce))) {
				out(echostr, resp);
			} else {
				out("not things", resp);
			}
		} catch (Throwable e) {
			e.printStackTrace();
			out("not things "+WxConfig.TOKEN, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			// 编码格式
			req.setCharacterEncoding("UTF-8");
			
			//创建自定义菜单
			MenuController.createMenu();

			// 验证签名
			String timestamp = req.getParameter("timestamp");
			String nonce = req.getParameter("nonce");
			String signature = req.getParameter("signature");


			Logger log = Logger.getLogger("lavasoft");
			log.setLevel(Level.INFO);

			log.info("doPost timestamp "+timestamp);

			if (!signature.equals(SHA1.gen(WxConfig.TOKEN, timestamp, nonce))) {
				out("", resp);
				return;
			}

			// 解析xml
			Map<String, String> reqMap = parseXml(req.getInputStream());
			System.out.println("reqMap=" + reqMap);
			log.info("reqMap=" + reqMap);

			// 处理请求
			String xmlStr = service.handle(reqMap);

			System.out.println("xmlStr=" + xmlStr);

			log.info("xmlStr=" + xmlStr);

			// null 转为空字符串
			xmlStr = xmlStr == null ? "" : xmlStr;
			
			

			out(xmlStr, resp);
		} catch (Throwable e) {
			e.printStackTrace();
			// 异常时响应空串
			out("not things", resp);
		}
	}

	/**
	 * 输出字符串
	 */
	protected void out(String str, HttpServletResponse response) {
		Writer out = null;
		try {
			response.setContentType("text/xml;charset=UTF-8");
			out = response.getWriter();
			out.append(str);
			out.flush();
		} catch (IOException e) {
			// ignore
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					// ignore
				}
			}
		}
	}

	/**
	 * 解析请求中的xml元素为Map
	 */
	@SuppressWarnings("unchecked")
	private static Map<String, String> parseXml(InputStream in)
			throws DocumentException, IOException {
		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		Document document = reader.read(in);
		Element root = document.getRootElement();
		List<Element> elementList = root.elements();
		for (Element e : elementList) {
			map.put(e.getName(), e.getText());
		}
		return map;
	}

}
