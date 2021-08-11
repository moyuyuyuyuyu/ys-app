package com.microstone.app.vo;

import com.microstone.app.entity.ProductElement;
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
@ApiModel(value = "ProductElementVO对象", description = "ProductElementVO对象")
public class ProductElementVO extends ProductElement {
	private static final long serialVersionUID = 1L;

}
