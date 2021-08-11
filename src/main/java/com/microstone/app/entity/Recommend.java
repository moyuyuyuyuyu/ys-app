package com.microstone.app.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.microstone.core.tenant.mp.TenantEntity;

/**
 * 实体类
 *
 * @author Chalsee
 * @since 2021-05-27
 */
@Data
@TableName("app_recommend")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Recommend对象", description = "Recommend对象")
public class Recommend extends TenantEntity {

	private static final long serialVersionUID = 1L;

		private Long newsId;
		private Integer sort;
		private Integer type;

		@ApiModelProperty("标签名称")
		private String newsName;
		@ApiModelProperty("标签内容")
		private String newsContent;
		@ApiModelProperty("标签id")
		private Long plateId;
		@ApiModelProperty("标题")
		private String name;
}
