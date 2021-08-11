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
@TableName("app_announcement")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Announcement对象", description = "Announcement对象")
public class Announcement extends TenantEntity {

	private static final long serialVersionUID = 1L;

	/**
	* 标题
	*/
		@ApiModelProperty(value = "标题")
		private String title;
	/**
	* 发布人
	*/
		@ApiModelProperty(value = "发布人")
		private String author;
	/**
	* 内容
	*/
		@ApiModelProperty(value = "内容")
		private String content;
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


}
