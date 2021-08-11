package com.microstone.app.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * @author XieXiaoDong
 * @date 2021/5/26/0026
 * @description 获取微信token
 */
public class GetWXToken {
    public static String getToken(String path,String appid,String secret) throws MalformedURLException, IOException, ProtocolException {
        // access_token接口https请求方式: GET https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
//        String path = " https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";
//        String appid = "wx030ee0f971d24f5a";
//        String secret = "5ace9267cde234905fd52a77f967a7c3";
        URL url = new URL(path +"&appid=" + appid + "&secret=" + secret);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        InputStream in = connection.getInputStream();
        byte[] b = new byte[100];
        int len = -1;
        StringBuilder sb = new StringBuilder();
        while((len = in.read(b)) != -1) {
            sb.append(new String(b,0,len));
        }

        System.out.println(sb.toString());
        in.close();
        return sb.toString();
    }
}
