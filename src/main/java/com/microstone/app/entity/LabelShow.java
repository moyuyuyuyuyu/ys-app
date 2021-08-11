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
@TableName("app_label_show")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "LabelShow对象", description = "LabelShow对象")
public class LabelShow extends TenantEntity {

	private static final long serialVersionUID = 1L;

	/**
	* 板块id
	*/
		@ApiModelProperty(value = "板块id")
		private Long plateId;
	/**
	* 排序
	*/
		@ApiModelProperty(value = "排序")
		private Integer sort;

	/**
	 * 类型：文章、视频、文档、海报
 	 */
		@ApiModelProperty(value = "类型：文档、视频、海报、文档")
		private Integer type;

	/**
	 * 标签名称
 	 */
		@ApiModelProperty(value = "标签名称")
		private String plateName;


}
