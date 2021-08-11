package com.microstone.app.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.microstone.app.dto.AnnouncementDTO;
import com.microstone.app.query.ArticleQuery;
import com.microstone.app.vo.AnnouncementVO;
import com.microstone.app.vo.ArticleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

import org.microstone.core.boot.ctrl.MsController;
import org.microstone.core.tool.api.R;
import org.springframework.web.bind.annotation.*;
import com.microstone.app.service.IAnnouncementService;

/**
 *  控制器
 *
 * @author Chalsee
 * @since 2021-05-27
 */
@RestController
@AllArgsConstructor
@RequestMapping("/announcement")
@Api(value = "", tags = "接口")
public class AnnouncementController extends MsController {

	private final IAnnouncementService announcementService;


	@PostMapping("/getAnnouncement")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "获取公告")
	public R<Page<AnnouncementVO>> getAnnouncement(@RequestBody ArticleQuery articleQuery) {
		return R.data(announcementService.getAnnouncement(articleQuery));
	}


	@PostMapping("/editAnnouncement")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "编辑新增")
	public R editAnnouncement(@RequestBody AnnouncementDTO announcementDTO) {
		return R.status(announcementService.editAnnouncement(announcementDTO));
	}

	@PostMapping("/deleteAnnouncement")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "删除")
	public R deleteAnnouncement(@RequestBody AnnouncementDTO announcementDTO) {
		return R.status(announcementService.deleteAnnouncement(announcementDTO));
	}

	@PostMapping("/getAnnouncementDetail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情")
	public R<AnnouncementVO> getAnnouncementDetail(@RequestBody AnnouncementDTO announcementDTO) {
		return R.data(announcementService.getAnnouncementDetail(announcementDTO));
	}
}
