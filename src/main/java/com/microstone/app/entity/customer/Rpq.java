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
@TableName("customer_rpq")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Rpq对象", description = "Rpq对象")
public class Rpq extends TenantEntity {

	private static final long serialVersionUID = 1L;

	/**
	* 管理人id
	*/
		@ApiModelProperty(value = "管理人id")
		private Long managerId;
	/**
	* 客户id
	*/
		@ApiModelProperty(value = "客户id")
		private Long customerId;
	/**
	* 题目信息
	*/
		@ApiModelProperty(value = "题目信息 （后端处理）")
		private String questionInfo;
	/**
	* 附件
	*/
		@ApiModelProperty(value = "附件 （后端处理）")
		private String attachment;
	/**
	* 风测分数
	*/
		@ApiModelProperty(value = "风测分数")
		private BigDecimal rpqScore;
	/**
	* 风测等级
	*/
		@ApiModelProperty(value = "风测等级")
		private Integer riskLevel;
	/**
	* 风测等级名称
	*/
		@ApiModelProperty(value = "风测等级名称")
		private String levelName;
	/**
	* 风测等级id
	*/
		@ApiModelProperty(value = "风测等级id")
		private Long levelId;
	/**
	* 版本号
	*/
		@ApiModelProperty(value = "版本号")
		private String versionName;
	/**
	* 生效日期
	*/
		@ApiModelProperty(value = "生效日期")
		private Date effectiveDate;
	/**
	* 到期日期
	*/
		@ApiModelProperty(value = "到期日期")
		private Date expiryDate;
	/**
	* 是否最新
	*/
		@ApiModelProperty(value = "是否最新")
		private Boolean hasNew;
	/**
	* 备注
	*/
		@ApiModelProperty(value = "备注")
		private String note;
	/**
	* 是否客户新增
	*/
		@ApiModelProperty(value = "是否客户新增")
		private Boolean hasCustomer;
	/**
	* 是否启用
	*/
		@ApiModelProperty(value = "是否启用")
		private Boolean hasEnable;

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

	/**
	 * 是否删除
	 */
	@ApiModelProperty(value = "是否删除")
	private Integer isDeleted;

	@ApiModelProperty(value = "审核时间")
	private Date auditTime;

	@ApiModelProperty(value = "审核人")
	private Long auditId;

	@ApiModelProperty(value = "审核备注")
	private String auditNote;
	/**
	 * 审批流程id
	 */
	@ApiModelProperty(value = "审批流程id")
	private Long businessId;

	/**
	 * 版本状态  1审核中 2生效 3失效
	 */
	@ApiModelProperty(value = "版本状态")
	private Integer versionStatus;

	@ApiModelProperty(value = "其他附件 （后端处理）")
	private String elseAccessory;

}
