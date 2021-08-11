package com.microstone.app.vo;

import com.microstone.app.entity.Product;
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
@ApiModel(value = "ProductVO对象", description = "ProductVO对象")
public class ProductVO extends Product {
	private static final long serialVersionUID = 1L;

}
