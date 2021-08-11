package com.microstone.app.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.microstone.app.entity.Video;
import com.microstone.app.query.ArticleQuery;
import com.microstone.app.vo.VideoVO;
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
public interface VideoMapper extends BaseMapper<Video> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param video
	 * @return
	 */
	List<VideoVO> selectVideoPage(IPage page, VideoVO video);

    List<VideoVO> getVideo(Page<VideoVO> page,@Param("query") ArticleQuery query);

    List<VideoVO> getVideoByTenantId(Page<VideoVO> page,@Param("articleQuery") ArticleQuery articleQuery);
}
