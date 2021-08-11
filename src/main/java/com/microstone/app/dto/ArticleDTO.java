package com.microstone.app.dto;

import com.microstone.app.entity.Article;
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
public class ArticleDTO extends Article {
	private static final long serialVersionUID = 1L;

}
