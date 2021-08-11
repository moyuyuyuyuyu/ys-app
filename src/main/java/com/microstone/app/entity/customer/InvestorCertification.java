package com.microstone.app.entity.customer;

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
 * @author Ms
 * @since 2020-11-25
 */
@Data
@TableName("customer_investor_certification")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "InvestorCertification对象", description = "InvestorCertification对象")
public class InvestorCertification extends TenantEntity {

	private static final long serialVersionUID = 1L;

	/**
	* 客户id
	*/
		@ApiModelProperty(value = "客户id")
		private Long customerId;
	/**
	* 投资者认证类型
	*/
		@ApiModelProperty(value = "投资者认证类型")
		private Integer certificationType;
	/**
	* 资产证明
	*/
		@ApiModelProperty(value = "资产证明")
		private String assetProof;
	/**
	* 经历证明
	*/
		@ApiModelProperty(value = "经历证明")
		private String experienceProof;
	/**
	* 其他附件
	*/
		@ApiModelProperty(value = "其他附件")
		private String attachment;
	/**
	* 版本名称
	*/
		@ApiModelProperty(value = "版本名称")
		private String versionName;
	/**
	* 生效日期
	*/
		@ApiModelProperty(value = "生效日期")
		private Date effectiveDate;
	/**
	* 失效日期
	*/
		@ApiModelProperty(value = "失效日期")
		private Date expiryDate;
	/**
	* 到期日
	*/
		@ApiModelProperty(value = "到期日")
		private Date termValidityDate;
	/**
	* 是否客户新增
	*/
		@ApiModelProperty(value = "是否客户新增")
		private Boolean hasCustomer;
	/**
	* 审核状态
	*/
		@ApiModelProperty(value = "审核状态")
		private Integer auditStatus;
	/**
	* 审核人
	*/
		@ApiModelProperty(value = "审核人")
		private Long auditId;
	/**
	* 审核时间
	*/
		@ApiModelProperty(value = "审核时间")
		private Date auditTime;
	/**
	* 审核备注
	*/
		@ApiModelProperty(value = "审核备注")
		private String auditNote;

		@ApiModelProperty(value = "备注")
		private String note;

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

	/**
	 * 审批流程id
	 */
	@ApiModelProperty(value = "审批流程id")
	private Long businessId;

	@ApiModelProperty("租户id")
	private String tenantId;

}
