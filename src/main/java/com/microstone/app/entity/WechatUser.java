package com.microstone.app.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.microstone.core.mp.base.BaseEntity;
import org.microstone.core.tenant.mp.TenantEntity;

@Data
@TableName("app_wechat_user")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "WechatUser", description = "WechatUser")
public class WechatUser extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * openid
     */
    @ApiModelProperty(value = "openid")
    private String openId;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String name;

    /**
     * 头像
     */
    private String headImage;

    private String phone;
}
