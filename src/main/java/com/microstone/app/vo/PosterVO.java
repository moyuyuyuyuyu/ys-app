package com.microstone.app.vo;

import com.microstone.app.entity.Poster;
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
@ApiModel(value = "PosterVO对象", description = "PosterVO对象")
public class PosterVO extends Poster {
	private static final long serialVersionUID = 1L;

}
