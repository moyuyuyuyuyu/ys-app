package com.microstone.app.vo;

import com.microstone.app.entity.Product;
import com.microstone.app.entity.Recommend;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 视图实体类
 *
 * @author Chalsee
 * @since 2021-05-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Recommend对象", description = "Recommend对象")
public class RecommendsVO extends Recommend {
	private static final long serialVersionUID = 1L;

	private String updateUserName;

	private Boolean hasTop;
}
