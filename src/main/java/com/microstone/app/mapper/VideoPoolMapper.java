package com.microstone.app.mapper;

import com.microstone.app.entity.VideoPool;
import com.microstone.app.vo.VideoPoolVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

/**
 *  Mapper 接口
 *
 * @author Chalsee
 * @since 2021-05-27
 */
public interface VideoPoolMapper extends BaseMapper<VideoPool> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param videoPool
	 * @return
	 */
	List<VideoPoolVO> selectVideoPoolPage(IPage page, VideoPoolVO videoPool);

}
