package com.microstone.app.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.microstone.app.dto.PosterDTO;
import com.microstone.app.dto.VideoDTO;
import com.microstone.app.entity.Video;
import com.microstone.app.query.ArticleQuery;
import com.microstone.app.vo.VideoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

import org.microstone.core.boot.ctrl.MsController;
import org.microstone.core.tool.api.R;
import org.springframework.web.bind.annotation.*;
import com.microstone.app.service.IVideoService;

/**
 *  控制器
 *
 * @author Chalsee
 * @since 2021-05-27
 */
@RestController
@AllArgsConstructor
@RequestMapping("/video")
@Api(value = "", tags = "接口")
public class VideoController extends MsController {

	private final IVideoService videoService;

	@PostMapping("/getVideo")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "获取视频")
	public R<Page<VideoVO>> getVideo(@RequestBody ArticleQuery query) {
		return R.data(videoService.getVideo(query));
	}

	@PostMapping("/saveVideo")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "新增/编辑")
	public R saveVideo(@RequestBody VideoDTO videoDTO) {
		return R.status(videoService.saveVideo(videoDTO));
	}

	@PostMapping("/deleteVideo")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "删除视频")
	public R deleteVideo(@RequestBody VideoDTO videoDTO) {
		return R.status(videoService.deleteVideo(videoDTO));
	}

	@PostMapping("/getVideoDetail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情")
	public R<VideoVO> getVideoDetail(@RequestBody VideoDTO videoDTO) {
		return R.data(videoService.getVideoDetail(videoDTO));
	}

	@PostMapping("/getVideoByTenantId")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "根据租户id获取视频")
	public R<Page<VideoVO>> getVideoByTenantId(@RequestBody ArticleQuery articleQuery) {
		return R.data(videoService.getVideoByTenantId(articleQuery));
	}
}
