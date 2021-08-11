package com.microstone.app.util;

import com.alibaba.fastjson.JSON;
import com.microstone.app.query.WXQuery;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author XieXiaoDong
 * @date 2021/7/23/0023
 * @description 方法描述
 */
public class WXQRCodeUtil {
    public static byte[] get(String path,String token,String scene,String page) throws Exception {
        String rePath = path + token;
        URL url = new URL(rePath);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("content-type", "application/json;charset=utf-8");
        connection.connect();
        // post发送的参数
        Map<String, Object> map = new HashMap<>();
        map.put("type", "news"); // news表示图文类型的素材，具体看API文档
        map.put("scene", scene);
//        map.put("page", page);
        map.put("width", 230);
        map.put("is_hyaline", true);
        map.put("auto_color", false);
        Map<String, Object> line_color = new HashMap<>();
        line_color.put("r", 255);
        line_color.put("g", 255);
        line_color.put("b", 255);
        map.put("line_color", line_color);
        // 将map转换成json字符串
        String paramBody = JSON.toJSONString(map); // 这里用了Alibaba的fastjson

        OutputStream out = connection.getOutputStream();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
        bw.write(paramBody); // 向流中写入参数字符串
        bw.flush();

        InputStream in = connection.getInputStream();
        byte[] bytes = readInputStream(in);
//        String str = IOUtils.toString(in, StandardCharsets.UTF_8);
        in.close();
        return bytes;
    }

    /**  将流 保存为数据数组
     * @param inStream
     * @return
     * @throws Exception
     */
    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        // 创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        // 每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        // 使用一个输入流从buffer里把数据读取出来
        while ((len = inStream.read(buffer)) != -1) {
            // 用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        // 关闭输入流
        inStream.close();
        // 把outStream里的数据写入内存
        return outStream.toByteArray();
    }
}
