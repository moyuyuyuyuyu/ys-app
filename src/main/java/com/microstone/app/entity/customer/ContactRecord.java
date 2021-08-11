package com.microstone.app.entity.customer;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.microstone.core.tenant.mp.TenantEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 实体类
 *
 * @author Ms
 * @since 2020-11-25
 */
@Data
@TableName("customer_contact_record")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ContactRecord对象", description = "ContactRecord对象")
public class ContactRecord extends TenantEntity {

	private static final long serialVersionUID = 1L;

	/**
	* 客户id
	*/
		@ApiModelProperty(value = "客户id")
		private Long customerId;
	/**
	* 交往记录类型
	*/
		@ApiModelProperty(value = "交往记录类型")
		private Integer contactType;
	/**
	* 交往内容
	*/
		@ApiModelProperty(value = "交往内容")
		private String contactContent;
	/**
	* 交往日期
	*/
		@ApiModelProperty(value = "交往日期")
		private Date contactDate;
	/**
	* 交往附件
	*/
		@ApiModelProperty(value = "交往附件")
		private String attachment;
	/**
	* 信任指数
	*/
		@ApiModelProperty(value = "信任指数")
		private BigDecimal trustIndex;
	/**
	* 是否删除
	*/
		@ApiModelProperty(value = "是否删除")
		private Integer isDeleted;

	/**
	* 删除人
	*/
		@ApiModelProperty(value = "删除人")
		private Long deleteUser;
	/**
	* 删除时间
	*/
		@ApiModelProperty(value = "删除时间")
		private Date deleteTime;
	private Integer version;

	private Long visitRecordId;


}
