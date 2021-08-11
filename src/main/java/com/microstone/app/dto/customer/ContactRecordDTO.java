package com.microstone.app.dto.customer;


import com.microstone.app.entity.customer.ContactRecord;
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
public class ContactRecordDTO extends ContactRecord {
	private static final long serialVersionUID = 1L;


	/**
	 * 交往附件数据传输对象
	 */
	@ApiModelProperty(value = "交往附件")
	private List<FileInfoDTO> attachments;
}
