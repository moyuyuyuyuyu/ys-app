package com.microstone.app.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.microstone.app.entity.WechatOfficialAccount;
import com.microstone.app.mapper.WechatOfficialAccountMapper;
import org.apache.tomcat.util.codec.binary.Base64;
import org.microstone.core.cache.utils.CacheUtil;
import org.microstone.core.secure.utils.AuthUtil;
import org.microstone.core.tool.utils.Func;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class QrCodeUtil {

    @Resource
    private WechatOfficialAccountMapper wechatOfficialAccountMapper;

    public String getUnlimited(String sceneStr) throws IOException {
        String tenantId = AuthUtil.getTenantId();
        WechatOfficialAccount wechatOfficialAccount = wechatOfficialAccountMapper.selectOne(new LambdaQueryWrapper<WechatOfficialAccount>().eq(WechatOfficialAccount::getTenantId, tenantId).eq(WechatOfficialAccount::getIsDeleted, 0));
        //获取token缓存
        String cacheToken = CacheToken.getCacheToken(wechatOfficialAccount.getPath(), wechatOfficialAccount.getAppid(), wechatOfficialAccount.getSecret());
        RestTemplate rest = new RestTemplate();
        InputStream inputStream = null;
        OutputStream outputStream = null;
        String res = null;
        try {
            String url = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?" + cacheToken;
            Map<String, Object> param = new HashMap<>();
            param.put("scene", sceneStr);
            param.put("page", "pages/index/index");
            param.put("width", 270);
            param.put("auto_color", false);
            Map<String, Object> line_color = new HashMap<>();
            line_color.put("r", 0);
            line_color.put("g", 0);
            line_color.put("b", 0);
            param.put("line_color", line_color);
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            HttpEntity requestEntity = new HttpEntity(param, headers);
            ResponseEntity<byte[]> entity = rest.exchange(url, HttpMethod.POST, requestEntity, byte[].class, new Object[0]);
            byte[] result = entity.getBody();
            res = Base64.encodeBase64String(result);
            return res;
        } catch (Exception e) {
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}
