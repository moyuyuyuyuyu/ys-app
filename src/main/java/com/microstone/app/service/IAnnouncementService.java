package com.microstone.app.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.microstone.app.dto.AnnouncementDTO;
import com.microstone.app.entity.Announcement;
import com.microstone.app.query.ArticleQuery;
import com.microstone.app.vo.AnnouncementVO;
import org.microstone.core.mp.base.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 *  服务类
 *
 * @author Chalsee
 * @since 2021-05-27
 */
public interface IAnnouncementService extends BaseService<Announcement> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param announcement
	 * @return
	 */
	IPage<AnnouncementVO> selectAnnouncementPage(IPage<AnnouncementVO> page, AnnouncementVO announcement);

    Page<AnnouncementVO> getAnnouncement(ArticleQuery articleQuery);

	Boolean editAnnouncement(AnnouncementDTO announcementDTO);

	Boolean deleteAnnouncement(AnnouncementDTO announcementDTO);

	AnnouncementVO getAnnouncementDetail(AnnouncementDTO announcementDTO);
}
