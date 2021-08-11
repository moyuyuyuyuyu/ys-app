package com.microstone.app.vo;

import com.microstone.app.entity.Article;
import com.microstone.app.entity.LabelShow;
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
@ApiModel(value = "LabelShowVO对象", description = "LabelShowVO对象")
public class LabelShowVO extends LabelShow {
	private static final long serialVersionUID = 1L;

	private String updateUserName;
}
