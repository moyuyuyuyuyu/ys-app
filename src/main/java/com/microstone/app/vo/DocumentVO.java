package com.microstone.app.vo;

import com.microstone.app.entity.Document;
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
@ApiModel(value = "DocumentVO对象", description = "DocumentVO对象")
public class DocumentVO extends Document {
	private static final long serialVersionUID = 1L;

}
