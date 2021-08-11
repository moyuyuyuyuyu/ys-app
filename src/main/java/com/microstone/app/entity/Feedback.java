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
@TableName("app_feedback")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Feedback", description = "Feedback")
public class Feedback extends TenantEntity {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty("提交人")
	private String submitter;

	@ApiModelProperty("提交人id")
	private Long submitterId;

	@ApiModelProperty("手机号")
	private String mobile;

	@ApiModelProperty("内容")
	private String content;

	@ApiModelProperty("是否联系")
	private Boolean hasContact;

	@ApiModelProperty("发布时间")
	private Date publishDate;

	@ApiModelProperty("附件")
	private String accessory;
}
