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
@TableName("app_document")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Document对象", description = "Document对象")
public class Document extends TenantEntity {

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

		private String filePath;
	/**
	 * 文档路径
	 */
	@ApiModelProperty(value = "文档路径")
	private String originalPath;
	/**
	 * 分类
	 */
	@ApiModelProperty(value = "分类")
		private Integer category;
	/**
	 * 是否推荐
	 */
	@ApiModelProperty(value = "推荐理由")
		private String recommendReason;
	/**
	 * 是否精选推荐
	 */
	@ApiModelProperty(value = "是否精选推荐")
		private Boolean isRecommend;
	/**
	 * 类型：文档
	 */
	@ApiModelProperty(value = "文档")
		private Integer type;
	/**
	 * 阅读量/查看次数
	 */
	@ApiModelProperty(value = "阅读量/查看次数")
		private Integer readingQuantity;

	@ApiModelProperty("分享人数")
	private Integer shareCount;

	@ApiModelProperty("获客人数")
	private Integer customerCount;

	private String cbsId;

	private String picturePath;

	private Boolean hasTop;
}
