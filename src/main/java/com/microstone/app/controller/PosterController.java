package com.microstone.app.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.microstone.app.dto.ArticleDTO;
import com.microstone.app.dto.DocumentDTO;
import com.microstone.app.dto.PosterDTO;
import com.microstone.app.query.ArticleQuery;
import com.microstone.app.vo.DocumentVO;
import com.microstone.app.vo.PosterVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

import org.microstone.core.boot.ctrl.MsController;
import org.microstone.core.tool.api.R;
import org.springframework.web.bind.annotation.*;
import com.microstone.app.service.IPosterService;

/**
 *  控制器
 *
 * @author Chalsee
 * @since 2021-05-27
 */
@RestController
@AllArgsConstructor
@RequestMapping("/poster")
@Api(value = "海报", tags = "接口")
public class PosterController extends MsController {

	private final IPosterService posterService;

	/**
	 * @author XieXiaoDong
	 * @date 2021/5/28/0028
	 * @description 获取海报
	 */
	@PostMapping("/getPoster")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "获取海报")
	public R<Page<PosterVO>> getPoster(@RequestBody ArticleQuery query) {
		return R.data(posterService.getPoster(query));
	}
	
	/**
	 * @author XieXiaoDong
	 * @date 2021/5/31/0031
	 * @description 新增/编辑 海报
	 */
	@PostMapping("/savePoster")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "新增/编辑 海报")
	public R savePoster(@RequestBody PosterDTO posterDTO) {
		return R.status(posterService.savePoster(posterDTO));
	}


	@PostMapping("/deletePoster")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "删除海报")
	public R deletePoster(@RequestBody PosterDTO posterDTO) {
		return R.status(posterService.deletePoster(posterDTO));
	}


	@PostMapping("/getPosterDetail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "海报详情")
	public R<PosterVO> getPosterDetail(@RequestBody PosterDTO posterDTO) {
		return R.data(posterService.getPosterDetail(posterDTO));
	}


	@PostMapping("/getPosterByTenantId")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "根据租户id获取海报")
	public R<Page<PosterVO>> getPosterByTenantId(@RequestBody ArticleQuery articleQuery) {
		return R.data(posterService.getPosterByTenantId(articleQuery));
	}
}
