package com.microstone.app.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.util.Date;

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
@TableName("app_video")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Video对象", description = "Video对象")
public class Video extends TenantEntity {

	private static final long serialVersionUID = 1L;

	/**
	* 标题
	*/
		@ApiModelProperty(value = "标题")
		private String title;
	/**
	* 作者
	*/
		@ApiModelProperty(value = "作者")
		private String author;
	/**
	* 板块id
	*/
		@ApiModelProperty(value = "板块id")
		private Long plateId;
	/**
	* 封面
	*/
		@ApiModelProperty(value = "封面")
		private String cover;
	/**
	* 内容
	*/
		@ApiModelProperty(value = "内容")
		private String content;
	/**
	* 链接
	*/
		@ApiModelProperty(value = "链接")
		private String url;
	/**
	* 发布日期
	*/
		@ApiModelProperty(value = "发布日期")
		private Date publishDate;
	/**
	* 排序
	*/
		@ApiModelProperty(value = "排序")
		private Integer sort;
	/**
	* 是否启用
	*/
		@ApiModelProperty(value = "是否启用")
		private Boolean enabled;

		@ApiModelProperty("简介")
		private String description;
		@ApiModelProperty("是否推荐")
		private Boolean isRecommend;
		@ApiModelProperty("推荐理由")
		private String recommendReason;
		@ApiModelProperty("类型：视频")
		private Integer type;
		@ApiModelProperty("阅读量/查看次数")
		private Integer readingQuantity;

	@ApiModelProperty("分享人数")
	private Integer shareCount;

	@ApiModelProperty("获客人数")
	private Integer customerCount;

	private Boolean hasTop;
}
