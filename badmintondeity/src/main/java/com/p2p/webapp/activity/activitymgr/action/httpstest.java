import com.google.gson.Gson;
import com.oracle.javafx.jmx.json.JSONDocument;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.UUID;

public class httpstest {

    public class  r{

        public String  access_token;
        public long expires_in;
    }


    public class tick{
        public long errcode;
        public String errmsg;
        public String ticket;
        public long expires_in;

       // {"errcode":0,"errmsg":"ok","ticket":"sM4AOVdWfPE4DxkXGEs8VNRzaQqRWS-jDovBaSFZ3XovOjfGFdXLOCTmomgchMMoDjgZH1zJLUzdJluZKuVV0Q","expires_in":7200
    }


    public static String SHA1(String decript) {
        try {
            MessageDigest digest = java.security.MessageDigest.getInstance("SHA-1");
            digest.update(decript.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args){

//        String s ="e783b070889e3ad209457dfefaf95861c3204a1b";
//
//        try {
//            if (!s.equals(SHA1.gen("f0b049e900d7c15b52b6a065716a8599", "1502116589", "746405069"))) {
//
//                return;
//            }
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }

//        byte [] bytes = new byte[0];
//        try {
//            bytes = new String("/1UFAgABAAA=").getBytes("UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        byte[] ff =  Base64.decode("/1UFAgABAAA=");


        HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try{
            httpClient = new SSLClient();
           // HttpGet get = new HttpGet("https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=wx315fa51c9bb4b39e&corpsecret=60f73066a802c386ae26c4dad31800f6");
           HttpGet get = new HttpGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx315fa51c9bb4b39e&secret=60f73066a802c386ae26c4dad31800f6");

            //https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx315fa51c9bb4b39e&secret=60f73066a802c386ae26c4dad31800f6
            //设置参数

            //HttpGet get = new HttpGet("http://yiqidongba.cn/callback?timestamp=1&nonce=1&signature=1&echostr-1");


//            "deviceid": "gh_4e7d2ecab699_dd91a86148ea5826",
//                    "qrticket": "http://we.qq.com/d/AQBja-S3ZnYJHXnAlG6YellaAg8pYmmxiTxESyJ8",
//                    "devicelicence": "522C8F6FB78486A75C6B33D99556239BB09DC1ED7F33547350E2A21860ACB97C15BECDBE6929F33A8440D92E9C21F223105A808FDCCFBDA91687D7FD101F15FA027148BD1F46581DFA39636D47EE961A"
//



//            "deviceid": "gh_4e7d2ecab699_51fe977a8591753c",
//                    "qrticket": "http://we.qq.com/d/AQBja-S3m1ccfAJ0_s6J13mKiItWtpO82nexz7kn",
//                    "devicelicence": "3673F29ADF907A0ED307E7D0B4443B5FAD278B0C3481368B616DB52F8881968EAA3ACB9E0F249FF4EADAB5AB2805131E4CFE101FB8E4965D51A016F581128FD67CB2224998757C66266A2494875AD13B"

            //oY2v0ty8iJBvKIpJZ8ErLd_OpSwY
            //&product_id=37484

            HttpResponse response = httpClient.execute(get);
            if(response != null){
                HttpEntity resEntity = response.getEntity();
                if(resEntity != null){
                result = EntityUtils.toString(resEntity,"utf-8");
                    System.out.println(result);
                }

            }
           System.out.println("token :  "+result);


           Gson g = new Gson();
           r m = g.fromJson(result,r.class);
            System.out.println("token "+m.access_token);

           // String uu = "https://api.weixin.qq.com/device/getqrcode?access_token=" + m.access_token+"&product_id=37484";

//            "deviceid": "gh_4e7d2ecab699_02b71f6028ba5125",
//                    "qrticket": "http://we.qq.com/d/AQBja-S34ylGXe47DzT1axbpqvv4mOQAPzXjzVad",
//                    "devicelicence": "610CFD03CDBF3E4B4077F2067683439CE8B61254464F3957045ABE7DB4C0D0DE6856818F2B5E4BEA80BCDD93DDA69B6FCE19CA793A6A2BCC7FAF9F9B529ADBFFED3A064CB889EDB978C21F2B9BB68215"

             HttpGet get2 = new HttpGet("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+ m.access_token +"&type=jsapi");
           // HttpGet get2 = new HttpGet("https://qyapi.weixin.qq.com/cgi-bin/get_jsapi_ticket?access_token="+m.access_token);

          //  HttpGet get2 = new HttpGet(uu);

           // {"base_resp":{"errcode":0,"errmsg":"ok"},"deviceid":"gh_4e7d2ecab699_f404e360840a3c2f","qrticket":"http:\/\/we.qq.com\/d\/AQBja-S3ir5SXKKYfM6owwVhKg-ZTbzr4RIMsKxk","devicelicence":"DC8CF0F8091626A1164E2F76C50861ECC410BD433A96B74A4030BB592B5A4435C40E10D0295C0CACC7BF2B60485E8F58A7F9CA74D9EF25E2B6BFD4DD2493F0C6AEEE9C7E3134595D96482BC904669524"}
            //设置参数

            HttpResponse response2 = httpClient.execute(get2);
            if(response2 != null){
                String tik =null;
                HttpEntity resEntity = response2.getEntity();
                if(resEntity != null){
                    tik = EntityUtils.toString(resEntity,"utf-8");
                    System.out.println(tik);
                }

                if(null != tik){

                   tick tt =  g.fromJson(tik,tick.class);

                    System.out.println("ttttt : "+tt.ticket);


                    String noncestr = UUID.randomUUID().toString().replace("-", "").substring(0, 16);//随机字符串
                    String timestamp = String.valueOf(System.currentTimeMillis() / 1000);//时间戳
                    String url="http://yiqidongba.cn/KeepTest.html";

                    String str = "jsapi_ticket="+tt.ticket+"&noncestr="+noncestr+"&timestamp="+timestamp+"&url="+url;

                    System.out.println("ssss "+str);


                    String signature =SHA1(str);
                    System.out.println("sign: "+signature);

                }

            }


        }catch(Exception ex){
            ex.printStackTrace();
        }

    }

    }
