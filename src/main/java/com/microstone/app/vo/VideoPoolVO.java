package com.microstone.app.vo;

import com.microstone.app.entity.VideoPool;
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
@ApiModel(value = "VideoPoolVO对象", description = "VideoPoolVO对象")
public class VideoPoolVO extends VideoPool {
	private static final long serialVersionUID = 1L;

}
