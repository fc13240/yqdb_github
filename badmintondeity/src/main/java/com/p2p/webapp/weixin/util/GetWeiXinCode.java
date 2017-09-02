package com.p2p.webapp.weixin.util;
 
import java.net.URLEncoder;

import com.p2p.webapp.common.constant.Constant;
/** 
 * @description 
 * @author 
 * @date 2015-12-18 下午2:43:51  
*/
public class GetWeiXinCode {
    public static String  GetCodeRequest = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
    public static String getCodeRequest(String scope,String redirect_url){
        String result = null;
        GetCodeRequest  = GetCodeRequest.replace("APPID", urlEnodeUTF8(Constant.APPID));
        GetCodeRequest  = GetCodeRequest.replace("REDIRECT_URI",urlEnodeUTF8(redirect_url));
        GetCodeRequest = GetCodeRequest.replace("SCOPE", scope);
        result = GetCodeRequest;
        return result;
    }
    public static String urlEnodeUTF8(String str){
        String result = str;
        try {
            result = URLEncoder.encode(str,"UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public static void main(String[] args) {
        System.out.println(getCodeRequest(Constant.SCOPE,Constant.REDIRECT_URI));
    }
}