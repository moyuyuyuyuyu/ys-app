package com.microstone.app.dto;

import com.microstone.app.entity.LabelShow;
import com.microstone.app.entity.Recommend;
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
public class RecommendDTO extends Recommend {
	private static final long serialVersionUID = 1L;

	private Boolean hasTop;
}
