package com.microstone.app.feign.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ：kevin.chu
 * @date ：Created in 2021/7/9 14:13
 * @description：获取微信用户查询信息
 * @modified By：
 * @version: $
 */
@Data
public class WechatUserInfoRqst {

    /**
     * 租户编号
     */
    private String tenantId;

    /**
     * 微信 openId
     */
    private String openId;


    /**
     * 手机号
     */
    public String phoneNumber;
    /**
     * 纯净手机号
     */
    public String purePhoneNumber;

    /**
     * 城市编码
     */
    public String countryCode;
    /**
     * 微信昵称
     */
    public String nickName;
    /**
     * 性别 0 未知 1男 2女
     */
    public String gender;

    /**
     * 显示 country，province，city 所用的语言
     * en	英文
     * zh_CN	简体中文
     * zh_TW	繁体中文
     */
    public String language;
    /**
     * 用户所在城市
     */
    public String city;
    /**
     * 用户所在省份
     */
    public String province;
    /**
     * 用户所在国家
     */
    public String country;

    /**
     * 用户头像图片的 URL。URL 最后一个数值代表正方形头像大小（有 0、46、64、96、132 数值可选，0 代表 640x640 的正方形头像，46 表示 46x46 的正方形头像，剩余数值以此类推。默认132），用户没有头像时该项为空。若用户更换头像，原有头像 URL 将失效
     */
    public String avatarUrl;




}
