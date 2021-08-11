package com.microstone.app.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.microstone.app.dto.QrCodeDTO;
import com.microstone.app.entity.WechatOfficialAccount;
import com.microstone.app.mapper.WechatOfficialAccountMapper;
import com.microstone.app.util.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.microstone.core.cache.utils.CacheUtil;
import org.microstone.core.tool.api.R;
import org.microstone.core.tool.utils.Func;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author XieXiaoDong
 * @date 2021/7/23/0023
 * @description 方法描述
 */
@RestController
@AllArgsConstructor
@RequestMapping("/QRCode")
@Api(value = "二维码", tags = "接口")
public class QRCodeController {

    @Autowired
    private QRCodeProperty qrCodeProperty;


    @PostMapping("/getQRCode")
    @ApiOperation("生成二维码")
    public R<String> getUnlimited(@RequestBody QrCodeDTO qrCodeDTO) throws Exception {

        String cacheToken = CacheToken.getQRCodeCache(qrCodeProperty.getAccessTokenPath(), qrCodeProperty.getAppid(), qrCodeProperty.getSecret());
        byte[] result = WXQRCodeUtil.get(qrCodeProperty.getQrCodePath(), cacheToken, qrCodeDTO.getScene(), qrCodeDTO.getPage());
        String base64 = UtilHelper.byte2Base64StringFun(result);
        if(base64.length() < 200) {
            CacheUtil.clear("ms:appqr");
            String result1 = GetWXToken.getToken(qrCodeProperty.getAccessTokenPath(),qrCodeProperty.getAppid(), qrCodeProperty.getSecret());
            Map<String,Object> token = (Map<String, Object>) JSON.parseObject(result1);
            String accessToken = token.get("access_token").toString();
            result = WXQRCodeUtil.get(qrCodeProperty.getQrCodePath(), accessToken, qrCodeDTO.getScene(), qrCodeDTO.getPage());
            base64 = UtilHelper.byte2Base64StringFun(result);
            CacheUtil.put("ms:appqr", "accessTokenqr:id:", "access_tokenqr", accessToken);
        }
        //转base64
        return R.data(base64);
    }
}
