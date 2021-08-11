package com.microstone.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.microstone.app.dto.PlateDTO;
import com.microstone.app.entity.Plate;
import com.microstone.app.query.PlateQuery;
import com.microstone.app.vo.PlateVO;
import com.microstone.app.vo.RecommendVO;
import org.microstone.core.mp.base.BaseService;

import java.util.List;

/**
 *  服务类
 *
 * @author Chalsee
 * @since 2021-05-27
 */
public interface IPlateService extends BaseService<Plate> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param plate
	 * @return
	 */
	IPage<PlateVO> selectPlatePage(IPage<PlateVO> page, PlateVO plate);

    List<Plate> getPlateList(PlateDTO plateDTO);

	Boolean saveOrUpdatePlate(PlateDTO plateDTO);

	Boolean deletePlate(PlateDTO plateDTO);

    Page<RecommendVO> getRecommend(PlateQuery query);

	void generalDelete(Long id);
}
