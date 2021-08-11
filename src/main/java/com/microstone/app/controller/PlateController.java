package com.microstone.app.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.microstone.app.dto.PlateDTO;
import com.microstone.app.entity.Plate;
import com.microstone.app.query.PlateQuery;
import com.microstone.app.vo.RecommendVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

import org.microstone.core.boot.ctrl.MsController;
import org.microstone.core.mp.support.Query;
import org.microstone.core.tool.api.R;
import org.springframework.web.bind.annotation.*;
import com.microstone.app.service.IPlateService;

import java.util.List;

/**
 *  控制器
 *
 * @author Chalsee
 * @since 2021-05-27
 */
@RestController
@AllArgsConstructor
@RequestMapping("/plate")
@Api(value = "板块管理", tags = "接口")
public class PlateController extends MsController {

	private final IPlateService plateService;

	/**
	 * @author XieXiaoDong
	 * @date 2021/5/28/0028
	 * @description 获取板块
	 */
	@PostMapping("/getPlateList")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "获取板块")
	public R<List<Plate>> getPlateList(@RequestBody PlateDTO plateDTO) {
		List<Plate> plateList = plateService.getPlateList(plateDTO);
		return R.data(plateList);
	}


	/**
	 * @author XieXiaoDong
	 * @date 2021/5/28/0028
	 * @description 新增/编辑 板块
	 */
	@PostMapping("/saveOrUpdatePlate")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "新增/编辑 板块")
	public R saveOrUpdatePlate(@RequestBody PlateDTO plateDTO) {
		Boolean flag = plateService.saveOrUpdatePlate(plateDTO);
		return R.status(flag);
	}


	/**
	 * @author XieXiaoDong
	 * @date 2021/5/28/0028
	 * @description 删除板块
	 */
	@PostMapping("/deletePlate")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "删除板块")
	public R deletePlate(@RequestBody PlateDTO plateDTO) {
		Boolean flag = plateService.deletePlate(plateDTO);
		return R.status(flag);
	}

	/**
	 * @author XieXiaoDong
	 * @date 2021/5/28/0028
	 * @description 获取推荐
	 */
	@PostMapping("/getRecommend")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "获取推荐")
	public R<Page<RecommendVO>> getRecommend(PlateQuery query) {
		Page<RecommendVO> page = plateService.getRecommend(query);
		return R.data(page);
	}

}
