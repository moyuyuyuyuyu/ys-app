package com.microstone.app.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.microstone.core.tenant.mp.TenantEntity;

/**
 * @author ：kevin.chu
 * @date ：Created in 2021/7/7 11:27
 * @description：app用户信息
 * @modified By：
 * @version: $
 */
@Data
public class AppUserInfo extends TenantEntity {
    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String name;

    /**
     * 姓名统一码
     */
    @ApiModelProperty(value = "姓名统一码")
    private String nameUnicode;
    /**
     * 用户头像
     */
    @ApiModelProperty(value = "用户头像")
    private String headIcon;
    /**
     * 公司
     */
    @ApiModelProperty(value = "公司")
    private String company;
    /**
     * 职位
     */
    @ApiModelProperty(value = "职位")
    private String job;
    /**
     * 公司地址
     */
    @ApiModelProperty(value = "公司地址")
    private String companyAddress;
    /**
     * 个人简介
     */
    @ApiModelProperty(value = "个人简介")
    private String selfIntroduce;
    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String mobilePhone;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;
    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;
    /**
     * 微信号
     */
    @ApiModelProperty(value = "微信号")
    private String wechat;
    /**
     * 所在地
     */
    @ApiModelProperty(value = "所在地")
    private String address;
    /**
     * 机构账号
     */
    @ApiModelProperty(value = "机构账号")
    private String organizationAccount;
    /**
     * 个人上传名片地址
     */
    @ApiModelProperty(value = "个人上传名片地址")
    private String uploadBusinessCard;
    /**
     * 个人名片标签code集合
     */
    @ApiModelProperty(value = "个人名片标签code集合")
    private String businessCardTags;
    /**
     * 用户评价code标签集合
     */
    @ApiModelProperty(value = "用户评价code标签集合")
    private String customerEvaluateTags;
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Long userId;


    /**
     * 显示名片
     */
    @ApiModelProperty(value = "显示名片")
    private Boolean showName;




    /**
     * 显示公司
     */
    @ApiModelProperty(value = "显示公司")
    private Boolean showCompany;

    /**
     * 显示职位
     */
    @ApiModelProperty(value = "显示职位")
    private Boolean showJob;

    /**
     * 显示公司地址
     */
    @ApiModelProperty(value = "显示公司地址")
    private Boolean showCompanyAddress;

    /**
     * 显示头像
     */
    @ApiModelProperty(value = "显示头像")
    private Boolean showHeadIcon;

    /**
     * 显示手机
     */
    @ApiModelProperty(value = "显示手机")
    private Boolean showMobilePhone;

    /**
     * 显示邮箱
     */
    @ApiModelProperty(value = "显示邮箱")
    private Boolean showEmail;

    /**
     * 显示微信
     */
    @ApiModelProperty(value = "显示微信")
    private Boolean showWechat;
}
