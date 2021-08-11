package com.microstone.app.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.microstone.app.util.html2json.api.Params;
import com.microstone.app.util.html2json.core.HtmlToJson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class JsoupUtil {


    public static String getHtmlToJson(String urlStr) throws Exception{
        URL url = new URL(urlStr);
        String source = getURLSource(url);
        Document doc = Jsoup.parse(source); // 使用jsoup 进行语言转换
        String a = doc.outerHtml();
        Params params=new Params();
        params.setType("html");
        String content = HtmlToJson.by(a,params).get();
        JSONArray objects = JSONObject.parseArray(content);
        String contents = objects.toJSONString();
        return contents;
    }

    public static String getHtml(String urlStr) throws Exception{
        URL url = new URL(urlStr);
        String source = getURLSource(url);
        Document doc = Jsoup.parse(source); // 使用jsoup 进行语言转换
        return doc.outerHtml();
    }


    /** *//**
     * 通过网站域名URL获取该网站的源码
     * @param url
     * @return String
     * @throws Exception
     */
    public static String getURLSource(URL url) throws Exception    {
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5 * 1000);
        InputStream inStream =  conn.getInputStream();  //通过输入流获取html二进制数据
        byte[] data = readInputStream(inStream);        //把二进制数据转化为byte字节数据
        String htmlSource = new String(data);
        return htmlSource;
    }

    /** *//**
     * 把二进制流转化为byte字节数组
     * @param instream
     * @return byte[]
     * @throws Exception
     */
    public static byte[] readInputStream(InputStream instream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[]  buffer = new byte[1204];
        int len = 0;
        while ((len = instream.read(buffer)) != -1){
            outStream.write(buffer,0,len);
        }
        instream.close();
        return outStream.toByteArray();
    }
}
