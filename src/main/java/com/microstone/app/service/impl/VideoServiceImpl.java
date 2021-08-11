package com.microstone.app.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.microstone.app.dto.VideoDTO;
import com.microstone.app.entity.Video;
import com.microstone.app.mapper.VideoMapper;
import com.microstone.app.query.ArticleQuery;
import com.microstone.app.service.IPlateService;
import com.microstone.app.service.IVideoService;
import com.microstone.app.vo.VideoVO;
import org.microstone.core.mp.base.BaseServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  服务实现类
 *
 * @author Chalsee
 * @since 2021-05-27
 */
@Service
public class VideoServiceImpl extends BaseServiceImpl<VideoMapper, Video> implements IVideoService {

	@Autowired
	private IPlateService plateService;
	@Override
	public IPage<VideoVO> selectVideoPage(IPage<VideoVO> page, VideoVO video) {
		return page.setRecords(baseMapper.selectVideoPage(page, video));
	}

	/**
	 * @author XieXiaoDong
	 * @date 2021/5/31/0031
	 * @description 获取视频
	 */
    @Override
    public Page<VideoVO> getVideo(ArticleQuery query) {
        Page<VideoVO> page = new Page<>(query.getCurrent(),query.getSize());
        List<VideoVO> videoList = baseMapper.getVideo(page,query);
        return page.setRecords(videoList);
    }

    /**
     * @author XieXiaoDong
     * @date 2021/5/31/0031
     * @description 新增/编辑 视频
     */
	@Override
	public Boolean saveVideo(VideoDTO videoDTO) {
		Video video = new Video();
		BeanUtils.copyProperties(videoDTO,video);
		return this.saveOrUpdate(video);
	}

	/**
	 * @author XieXiaoDong
	 * @date 2021/5/31/0031
	 * @description 删除视频
	 */
	@Override
	public Boolean deleteVideo(VideoDTO videoDTO) {
		//验证删除
		plateService.generalDelete(videoDTO.getId());
		return this.removeById(videoDTO.getId());
	}

	/**
	 * @author XieXiaoDong
	 * @date 2021/6/30/0030
	 * @description 详情
	 */
    @Override
    public VideoVO getVideoDetail(VideoDTO videoDTO) {
		Video video = baseMapper.selectById(videoDTO.getId());
		Integer readingQuantity = video.getReadingQuantity();
		video.setReadingQuantity(++readingQuantity);
		this.updateById(video);

		VideoVO videoVO = new VideoVO();
		BeanUtils.copyProperties(video,videoVO);
		return videoVO;
    }

    /**
     * @author XieXiaoDong
     * @date 2021/7/1/0001
     * @description 根据租户id获取视频
     */
    @Override
    public Page<VideoVO> getVideoByTenantId(ArticleQuery articleQuery) {
		Page<VideoVO> page = new Page<>(articleQuery.getCurrent(),articleQuery.getSize());
		List<VideoVO> list = baseMapper.getVideoByTenantId(page,articleQuery);
		return page.setRecords(list);
    }

}
