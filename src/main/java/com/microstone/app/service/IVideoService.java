package com.microstone.app.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.microstone.app.dto.VideoDTO;
import com.microstone.app.entity.Video;
import com.microstone.app.query.ArticleQuery;
import com.microstone.app.vo.VideoVO;
import org.microstone.core.mp.base.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 *  服务类
 *
 * @author Chalsee
 * @since 2021-05-27
 */
public interface IVideoService extends BaseService<Video> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param video
	 * @return
	 */
	IPage<VideoVO> selectVideoPage(IPage<VideoVO> page, VideoVO video);

	Page<VideoVO> getVideo(ArticleQuery query);

	Boolean saveVideo(VideoDTO videoDTO);

	Boolean deleteVideo(VideoDTO videoDTO);

    VideoVO getVideoDetail(VideoDTO videoDTO);

    Page<VideoVO> getVideoByTenantId(ArticleQuery articleQuery);
}
