package com.microstone.app.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.microstone.core.tenant.mp.TenantEntity;

import java.util.Date;

/**
 * 实体类
 *
 * @author Chalsee
 * @since 2021-05-27
 */
@Data
@TableName("app_wechat_official_account")
@EqualsAndHashCode(callSuper = true)
public class WechatOfficialAccount extends TenantEntity {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty("微信公众号备注名称")
	private String name;
	@ApiModelProperty("微信唯一标识")
	private String appid;
	@ApiModelProperty("微信唯一标识秘钥")
	private String secret;
	@ApiModelProperty("是否启用")
	private Boolean hasEnable;
	@ApiModelProperty("请求地址")
	private String path;
	@ApiModelProperty("请求地址")
	private String tokenPath;
}
