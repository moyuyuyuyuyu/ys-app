package com.microstone.app.vo;

import com.microstone.app.entity.Announcement;
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
@ApiModel(value = "AnnouncementVO对象", description = "AnnouncementVO对象")
public class AnnouncementVO extends Announcement {
	private static final long serialVersionUID = 1L;

}
