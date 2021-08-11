package com.microstone.app.vo;

import com.microstone.app.entity.Article;
import com.microstone.app.entity.Carousel;
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
@ApiModel(value = "CarouselVO对象", description = "CarouselVO对象")
public class CarouselVO extends Carousel {
	private static final long serialVersionUID = 1L;

	private String updateUserName;
}
