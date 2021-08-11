package com.microstone.app.util;

import com.alibaba.fastjson.JSON;
import com.microstone.app.query.WXQuery;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author XieXiaoDong
 * @date 2021/5/26/0026
 * @description 获取微信公众号文章
 */
public class GetContent {
    public static String getContentList(String tokenPath,String token, WXQuery query) throws IOException {
        String path = tokenPath + token;
        URL url = new URL(path);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("content-type", "application/json;charset=utf-8");
        connection.connect();
        // post发送的参数
        Map<String, Object> map = new HashMap<>();
        map.put("type", "news"); // news表示图文类型的素材，具体看API文档
        map.put("offset", (query.getCurrent() - 1) * query.getSize());
        map.put("count", query.getSize());
        // 将map转换成json字符串
        String paramBody = JSON.toJSONString(map); // 这里用了Alibaba的fastjson

        OutputStream out = connection.getOutputStream();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
        bw.write(paramBody); // 向流中写入参数字符串
        bw.flush();

        InputStream in = connection.getInputStream();

        String str = IOUtils.toString(in, StandardCharsets.UTF_8);
        in.close();
        return str;
    }
}
