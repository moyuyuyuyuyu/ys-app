package com.microstone.app.service.impl;

import com.microstone.app.entity.VideoPool;
import com.microstone.app.vo.VideoPoolVO;
import com.microstone.app.mapper.VideoPoolMapper;
import com.microstone.app.service.IVideoPoolService;
import org.microstone.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 *  服务实现类
 *
 * @author Chalsee
 * @since 2021-05-27
 */
@Service
public class VideoPoolServiceImpl extends BaseServiceImpl<VideoPoolMapper, VideoPool> implements IVideoPoolService {

	@Override
	public IPage<VideoPoolVO> selectVideoPoolPage(IPage<VideoPoolVO> page, VideoPoolVO videoPool) {
		return page.setRecords(baseMapper.selectVideoPoolPage(page, videoPool));
	}

}
