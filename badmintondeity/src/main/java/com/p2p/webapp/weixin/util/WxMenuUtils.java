package com.p2p.webapp.weixin.util;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


/**
 * 微信自定义菜单创建
 */
public class WxMenuUtils {
 // http客户端
 public static DefaultHttpClient httpclient;
 
 static {
  httpclient = new DefaultHttpClient();
  httpclient = (DefaultHttpClient) HttpClientConnectionManager.getSSLInstance(httpclient); 
  // 接受任何证书的浏览器客户端
 }

 /**
  *微信出了个性化菜单
  * @param args
  */
 public static void main(String[] args) {
  try {
   // 获取accessToken -参数appid，secret
   String accessToken = getAccessToken("wx315fa51c9bb4b39e", "60f73066a802c386ae26c4dad31800f6");//appid,
   System.out.println(accessToken);
   // 创建菜单
   String yushenMenu = "{\"button\":[{\"name\":\"首页\",\"type\":\"view\",\"url\":\"http://yiqidongba.cn/p2pstock\"},{\"name\":\"股时候\",\"type\":\"view\",\"url\":\"http://118.89.139.180:80/p2pstock/index.html\"},{\"name\":\"绑定用户\",\"type\":\"view\",\"url\":\"http://yiqidongba.cn/p2pstock/weixin/oauth2_weixinAction.action\"}]}";
  //根据用户的分组创建不同的菜单 ，后期考虑的时候，没有测试 。
//   String yushenMenuadd = "{\"button\":[{\"name\":\"首页\",\"type\":\"view\",\"url\":\"http://www.shuttler.cn/p2pstock\"},{\"name\":\"股时候\",\"type\":\"view\",\"url\":\"http://119.29.103.147:9001/stockApp/vJz2Q3m-zh_CN-/stock/index.w\"},{\"name\":\"绑定用户\",\"type\":\"view\",\"url\":\"http://www.shuttler.cn/p2pstock/weixin/oauth2_weixinAction.action\"}],\"matchrule\":{\"group_id\":\"2\",\"sex\":\"1\",\"country\":\"中国\",\"province\":\"广东\", \"city\":\"广州\", \"client_platform_type\":\"2\"}}";
  //------------------创建菜单的测试 ---start------------------
  String res = createMenu(yushenMenu, accessToken,"0");
   //------------------创建菜单的测试 ---end------------------ 
   
 //------------------获取微信服务器短ip列表 ---start------------------ 
   String weixinServiceIps  =  getWeiXinServerIp(accessToken);
  // System.out.println(res);
   System.out.println(weixinServiceIps); 
   JSONObject  obj = JSONObject.parseObject(weixinServiceIps);
   JSONArray a = obj.getJSONArray("ip_list");

   for (int i = 0; i < a.size(); i++) {
       String jo = (String) a.get(i);
     System.out.println(jo); //取到这里面的没一个ip 
   }
 //------------------获取微信服务器短ip列表 ---end------------------ 

 //------------------验证消息的真实性---start------------------ 
   
 //------------------验证消息的真实性---end------------------
   
  } catch (Exception e) {
   e.printStackTrace();
  }
 }
 
 
 /**
  * 创建菜单
  * menuType 1. 为个性化菜单  -0 为 普通caidan,
  */
 public static String createMenu(String params, String accessToken,String menuType) throws Exception {
    HttpPost httpost = null;
    if("0".equals(menuType.trim())){
	  httpost = HttpClientConnectionManager.getPostMethod("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + accessToken);
    }else{//创建个性化菜单
	 httpost = HttpClientConnectionManager.getPostMethod("https://api.weixin.qq.com/cgi-bin/menu/addconditional?access_token=" + accessToken);
    }
 
    httpost.setEntity(new StringEntity(params, "UTF-8"));
    HttpResponse response = httpclient.execute(httpost);
    String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
    System.out.println(jsonStr);
    JSONObject object = JSON.parseObject(jsonStr);
  return object.getString("errmsg");
 }

 /**
  * 获取accessToken
  * 
  */
 public static String getAccessToken(String appid, String secret) throws Exception {
  HttpGet get = HttpClientConnectionManager.getGetMethod("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appid + "&secret=" + secret);
  HttpResponse response = httpclient.execute(get);
  String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
  JSONObject object = JSON.parseObject(jsonStr);
  return object.getString("access_token");
 }
 
 /**
  * 查询菜单
  */
 public static String getMenuInfo(String accessToken) throws Exception {
  HttpGet get = HttpClientConnectionManager.getGetMethod("https://api.weixin.qq.com/cgi-bin/menu/get?access_token=" + accessToken);
  HttpResponse response = httpclient.execute(get);
  String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
  return jsonStr;
 }
 /**
  * 获取服务器的ip地址
  */
 public static String getWeiXinServerIp(String accessToken) throws Exception {
	 HttpGet get = HttpClientConnectionManager.getGetMethod("https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token=" + accessToken);
	 HttpResponse response = httpclient.execute(get);
	 String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
	 return jsonStr;
 }
 
 /**
  * 删除自定义菜单
  */
 public static String getAccessToken(String accessToken) throws Exception {
  HttpGet get = HttpClientConnectionManager.getGetMethod("https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=" + accessToken);
  HttpResponse response = httpclient.execute(get);
  String jsonStr = EntityUtils.toString(response.getEntity(), "utf-8");
  JSONObject object = JSON.parseObject(jsonStr);
  return object.getString("errmsg");
 }
}
