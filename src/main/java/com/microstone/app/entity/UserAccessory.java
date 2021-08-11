package com.microstone.app.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import org.microstone.core.tenant.mp.TenantEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 实体类
 *
 * @author Chalsee
 * @since 2021-06-07
 */
@Data
@TableName("app_user_accessory")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "UserAccessory对象", description = "UserAccessory对象")
public class UserAccessory extends TenantEntity {

	private static final long serialVersionUID = 1L;

	/**
	* 用户id
	*/
		@ApiModelProperty(value = "用户id")
		private Long userId;
	/**
	* 附件链接
	*/
		@ApiModelProperty(value = "附件链接")
		private String accessory;

		private Long appUserId;

}
