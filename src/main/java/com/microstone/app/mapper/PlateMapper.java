package com.microstone.app.mapper;

import com.microstone.app.entity.Plate;
import com.microstone.app.query.PlateQuery;
import com.microstone.app.vo.PlateVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.microstone.app.vo.RecommendVO;
import org.apache.ibatis.annotations.Param;
import org.microstone.core.mp.support.Query;

import java.util.List;

/**
 *  Mapper 接口
 *
 * @author Chalsee
 * @since 2021-05-27
 */
public interface PlateMapper extends BaseMapper<Plate> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param plate
	 * @return
	 */
	List<PlateVO> selectPlatePage(IPage page, PlateVO plate);

    List<RecommendVO> getRecommend();
}
