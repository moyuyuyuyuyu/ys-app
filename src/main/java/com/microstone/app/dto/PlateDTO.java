package com.microstone.app.dto;

import com.microstone.app.entity.Plate;
import io.swagger.annotations.ApiModelProperty;
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
public class PlateDTO extends Plate {
	private static final long serialVersionUID = 1L;

	private String name;

	@ApiModelProperty("是否禁用")
	private Boolean enabled;
}
