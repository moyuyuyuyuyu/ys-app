package com.microstone.app.dto.customer;


import com.microstone.app.entity.customer.Rpq;
import com.microstone.app.vo.customer.RpqQuestionRecordVO;
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
public class RpqDTO extends Rpq {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty("题目信息")
	private List<RpqQuestionRecordVO> questionInfoList;

	/**
	 * 文件接收参数定义 by kevin
	 */
	@ApiModelProperty("风测附件")
	private List<FileInfoDTO> attachments;
	@ApiModelProperty("风测面签附件")
	private List<FileInfoDTO> elseAccessoryList;

	@ApiModelProperty("管理人类型")
	private Integer managerType;


	@ApiModelProperty(value = "审批任务id")
	private String taskId;

}
