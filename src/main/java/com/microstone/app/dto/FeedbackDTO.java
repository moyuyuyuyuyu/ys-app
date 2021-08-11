package com.microstone.app.dto;

import com.microstone.app.entity.Feedback;
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
public class FeedbackDTO extends Feedback {
	private static final long serialVersionUID = 1L;

	private List<String> accessorys;
}
