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
 * @since 2021-05-27
 */
@Data
@TableName("app_plate")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Plate对象", description = "Plate对象")
public class Plate extends TenantEntity {

	private static final long serialVersionUID = 1L;

	/**
	* 名称
	*/
		@ApiModelProperty(value = "名称")
		private String name;
	/**
	* 类型
	*/
		@ApiModelProperty(value = "类型")
		private Integer type;
	/**
	* 备注
	*/
		@ApiModelProperty(value = "备注")
		private String note;
	/**
	* 是否启用
	*/
		@ApiModelProperty(value = "是否启用")
		private Boolean enabled;
	/**
	* 排序
	*/
		@ApiModelProperty(value = "排序")
		private Integer sort;

		private String cbsId;

}
