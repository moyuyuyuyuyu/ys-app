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
@TableName("app_carousel")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Carousel对象", description = "Carousel对象")
public class Carousel extends TenantEntity {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty("图片")
	private String carouselImage;

	@ApiModelProperty("1板块 2链接")
	private Integer linkType;

	@ApiModelProperty("1文章 2视频 3文档 4海报")
	private Integer labelType;

	@ApiModelProperty("内容")
	private String labelContent;

	@ApiModelProperty("关联的文章、视频、文档、海报 的id")
	private Long relationId;

	private Integer sort;
}
