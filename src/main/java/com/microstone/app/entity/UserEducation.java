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
 * @since 2021-06-07
 */
@Data
@TableName("app_user_education")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "UserEducation对象", description = "UserEducation对象")
public class UserEducation extends TenantEntity {

	private static final long serialVersionUID = 1L;

	/**
	* 用户id
	*/
		@ApiModelProperty(value = "用户id")
		private Long userId;
	/**
	* 学校
	*/
		@ApiModelProperty(value = "学校")
		private String school;
	/**
	* 专业
	*/
		@ApiModelProperty(value = "专业")
		private String major;
	/**
	* 学历：1-高中 2-大专 3-本科 4-硕士 5-博士 6-其他
	*/
		@ApiModelProperty(value = "学历：1-高中 2-大专 3-本科 4-硕士 5-博士 6-其他")
		private Integer educationType;
	/**
	* 学习开始时间
	*/
		@ApiModelProperty(value = "学习开始时间")
		private Date startTime;
	/**
	* 学习结束时间
	*/
		@ApiModelProperty(value = "学习结束时间")
		private Date endTime;


		private Long appUserId;


}
