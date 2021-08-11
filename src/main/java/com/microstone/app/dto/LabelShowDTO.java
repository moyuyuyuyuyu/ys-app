package com.microstone.app.dto;

import com.microstone.app.entity.LabelShow;
import com.microstone.app.entity.Plate;
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
public class LabelShowDTO extends LabelShow {
	private static final long serialVersionUID = 1L;
}
