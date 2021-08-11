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
@TableName("app_video_pool")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "VideoPool对象", description = "VideoPool对象")
public class VideoPool extends TenantEntity {

	private static final long serialVersionUID = 1L;

	/**
	* 标题
	*/
		@ApiModelProperty(value = "标题")
		private String title;
	/**
	* 链接
	*/
		@ApiModelProperty(value = "链接")
		private String url;
	/**
	* 备注
	*/
		@ApiModelProperty(value = "备注")
		private String note;
	/**
	* 发布日期
	*/
		@ApiModelProperty(value = "发布日期")
		private Date publishDate;
	/**
	* 来源
	*/
		@ApiModelProperty(value = "来源")
		private String source;


}
