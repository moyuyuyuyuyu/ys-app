package com.microstone.app.vo;

import com.microstone.app.entity.ReadRecord;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;

/**
 * 视图实体类
 *
 * @author Chalsee
 * @since 2021-05-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ReadRecordVO对象", description = "ReadRecordVO对象")
public class ReadRecordVO extends ReadRecord {
	private static final long serialVersionUID = 1L;

}
