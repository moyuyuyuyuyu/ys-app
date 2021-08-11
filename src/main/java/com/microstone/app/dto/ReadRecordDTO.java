package com.microstone.app.dto;

import com.microstone.app.entity.ReadRecord;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 数据传输对象实体类
 *
 * @author Chalsee
 * @since 2021-05-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ReadRecordDTO extends ReadRecord {
	private static final long serialVersionUID = 1L;

}
