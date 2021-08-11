package com.microstone.app.vo;

import com.microstone.app.entity.ShareRecord;
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
@ApiModel(value = "ShareRecordVO对象", description = "ShareRecordVO对象")
public class ShareRecordVO extends ShareRecord {
	private static final long serialVersionUID = 1L;

}
