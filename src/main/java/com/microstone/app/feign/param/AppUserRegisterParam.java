package com.microstone.app.feign.param;

import lombok.Data;
import org.microstone.core.sms.model.SmsCode;

/**
 * @author ：kevin.chu
 * @date ：Created in 2021/6/2 16:47
 * @description：
 * @modified By：
 * @version: $
 */
@Data
public class AppUserRegisterParam {

    private SmsCode smsCode;

    private String userName;

    /**
     * 密码
     */
    private String password;

    private String tenantId;
}
