package com.microstone.app.dto.customer;

import com.microstone.app.entity.customer.CbsCustomer;
import io.swagger.annotations.ApiModel;
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
@ApiModel(value = "CustomerDTO", description = "CustomerDTO")
public class CbsCustomerDTO extends CbsCustomer {

	private static final long serialVersionUID = 1L;
	//交往记录
	@ApiModelProperty(value = "交往记录对象")
	private ContactRecordDTO contactRecordDTO;
	//风险评测
	@ApiModelProperty(value = "风险评测对象")
	private RpqDTO rpqDTO;
	//投资者认证
	@ApiModelProperty(value = "投资者认证对象")
	private InvestorCertificationDTO investorCertificationDTO;

	@ApiModelProperty(value = "分页 pageNum")
	private  Integer pageNum;

	@ApiModelProperty(value = "分页 pageSize")
	private Integer pageSize;

	@ApiModelProperty(value = "销售名称")
	private String employeeName;

	@ApiModelProperty(value = "营业执照文件集合")
	private List<FileInfoDTO> licensePictures;

	@ApiModelProperty(value = "省 市集合")
	private List<String> provinceCityList;

	@ApiModelProperty(value = "经办人省 市集合")
	private List<String> operatorProvinceCityList;

	@ApiModelProperty(value = "证件照正面图片对象")
    private FileInfoDTO	credentialPictureFronts;

	@ApiModelProperty(value = "证件照反面图片对象")
	private FileInfoDTO	credentialPictureBacks;

	@ApiModelProperty(value = "实名认证审核类型")
	private Integer	auditType;

	@ApiModelProperty(value = "是否更新")
	private Boolean isNew;
}
