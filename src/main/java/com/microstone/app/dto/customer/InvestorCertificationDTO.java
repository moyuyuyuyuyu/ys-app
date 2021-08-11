package com.microstone.app.dto.customer;


import com.microstone.app.entity.customer.InvestorCertification;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 数据传输对象实体类
 *
 * @author Ms
 * @since 2020-11-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class InvestorCertificationDTO extends InvestorCertification {
	private static final long serialVersionUID = 1L;


	/**
	 * 资产证明
	 */
	@ApiModelProperty(value = "资产证明")
	private List<FileInfoDTO> assetProofs;
	/**
	 * 经历证明
	 */
	@ApiModelProperty(value = "经历证明")
	private List<FileInfoDTO> experienceProofs;
	/**
	 * 其他附件
	 */
	@ApiModelProperty(value = "其他附件")
	private List<FileInfoDTO> attachments;

	@ApiModelProperty(value = "审批任务id")
	private String taskId;





}
