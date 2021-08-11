package com.microstone.app.util;

import com.alibaba.fastjson.JSON;
import org.microstone.core.cache.utils.CacheUtil;
import org.microstone.core.tool.utils.Func;

import java.io.IOException;
import java.util.Map;

/**
 * @author XieXiaoDong
 * @date 2021/5/31/0031
 * @description 缓存获取token
 */
public class CacheToken {
    public static String getCacheToken(String path,String appid,String secret) throws IOException {
        String cacheToken = CacheUtil.get("ms:app", "accessToken:id:", "access_token", String.class);
        if(!Func.isNull(cacheToken)){
            return cacheToken;
        }else {
            String result1 = GetWXToken.getToken(path,appid,secret);
            Map<String,Object> token = (Map<String, Object>) JSON.parseObject(result1);
            String accessToken = token.get("access_token").toString();
            CacheUtil.put("ms:app", "accessToken:id:", "access_token", accessToken);
            return accessToken;
        }
    }

    public static String getQRCodeCache(String path,String appid,String secret) throws IOException {
        String cacheToken = CacheUtil.get("ms:appqr", "accessTokenqr:id:", "access_tokenqr", String.class);
        if(!Func.isNull(cacheToken)){
            return cacheToken;
        }else {
            String result1 = GetWXToken.getToken(path,appid,secret);
            Map<String,Object> token = (Map<String, Object>) JSON.parseObject(result1);
            String accessToken = token.get("access_token").toString();
            CacheUtil.put("ms:appqr", "accessTokenqr:id:", "access_tokenqr", accessToken);
            return accessToken;
        }
    }
}
