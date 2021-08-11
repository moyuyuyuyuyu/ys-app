package com.microstone.app.vo;

import com.microstone.app.entity.Article;
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
@ApiModel(value = "ArticleVO对象", description = "ArticleVO对象")
public class ArticleVO extends Article {
	private static final long serialVersionUID = 1L;

}
