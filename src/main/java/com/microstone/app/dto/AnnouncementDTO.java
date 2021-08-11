package com.microstone.app.dto;

import com.microstone.app.entity.Announcement;
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
public class AnnouncementDTO extends Announcement {
	private static final long serialVersionUID = 1L;

}
