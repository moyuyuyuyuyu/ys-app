package com.microstone.app.vo;

import com.microstone.app.entity.Video;
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
@ApiModel(value = "VideoVO对象", description = "VideoVO对象")
public class VideoVO extends Video {
	private static final long serialVersionUID = 1L;

}
