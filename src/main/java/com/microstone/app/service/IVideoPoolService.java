package com.microstone.app.service;

import com.microstone.app.entity.VideoPool;
import com.microstone.app.vo.VideoPoolVO;
import org.microstone.core.mp.base.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 *  服务类
 *
 * @author Chalsee
 * @since 2021-05-27
 */
public interface IVideoPoolService extends BaseService<VideoPool> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param videoPool
	 * @return
	 */
	IPage<VideoPoolVO> selectVideoPoolPage(IPage<VideoPoolVO> page, VideoPoolVO videoPool);

}
