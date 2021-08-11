package com.microstone.app.dto;

import com.microstone.app.entity.Product;
import com.microstone.app.entity.ProductElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 数据传输对象实体类
 *
 * @author Chalsee
 * @since 2021-05-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductDTO extends Product {
	private static final long serialVersionUID = 1L;

	private List<ProductElementDTO> productElementList;

	private Boolean hasOptional;

	private Boolean hasTrade;
}
