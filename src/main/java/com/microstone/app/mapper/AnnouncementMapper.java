package com.microstone.app.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.microstone.app.entity.Announcement;
import com.microstone.app.query.ArticleQuery;
import com.microstone.app.vo.AnnouncementVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  Mapper 接口
 *
 * @author Chalsee
 * @since 2021-05-27
 */
public interface AnnouncementMapper extends BaseMapper<Announcement> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param announcement
	 * @return
	 */
	List<AnnouncementVO> selectAnnouncementPage(IPage page, AnnouncementVO announcement);

    List<AnnouncementVO> getAnnouncement(Page<AnnouncementVO> page,@Param("articleQuery") ArticleQuery articleQuery);
}
