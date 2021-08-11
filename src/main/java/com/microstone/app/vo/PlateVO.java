package com.microstone.app.vo;

import com.microstone.app.entity.Plate;
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
@ApiModel(value = "PlateVO对象", description = "PlateVO对象")
public class PlateVO extends Plate {
	private static final long serialVersionUID = 1L;

}
