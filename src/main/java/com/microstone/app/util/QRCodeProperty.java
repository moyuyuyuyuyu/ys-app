package com.microstone.app.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author XieXiaoDong
 * @date 2021/7/23/0023
 * @description 方法描述
 */
@Component
@ConfigurationProperties(prefix = "qrcode")
@Data
public class QRCodeProperty {
    private String appid;
    private String secret;
    private String accessTokenPath;
    private String qrCodePath;
}
