package com.microstone.app.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.microstone.app.dto.AnnouncementDTO;
import com.microstone.app.dto.FeedbackDTO;
import com.microstone.app.entity.Feedback;
import com.microstone.app.query.ArticleQuery;
import com.microstone.app.query.FeedbackQuery;
import com.microstone.app.service.IAnnouncementService;
import com.microstone.app.service.IFeedbackService;
import com.microstone.app.vo.AnnouncementVO;
import com.microstone.app.vo.FeedbackVO;
import com.microstone.app.vo.LabelShowVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.microstone.core.boot.ctrl.MsController;
import org.microstone.core.mp.support.Query;
import org.microstone.core.tool.api.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *  控制器
 *
 * @author Chalsee
 * @since 2021-05-27
 */
@RestController
@AllArgsConstructor
@RequestMapping("/feedback")
@Api(value = "", tags = "接口")
public class FeedbackController extends MsController {

	private final IFeedbackService feedbackService;

	@PostMapping("/getFeedback")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "获取意见反馈列表")
	public R<Page<FeedbackVO>> getFeedback(@RequestBody FeedbackQuery query) {
		return R.data(feedbackService.getFeedback(query));
	}

	@PostMapping("/submitFeedback")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "提交意见反馈")
	public R submitFeedback(@RequestBody FeedbackDTO feedbackDTO) {
		return R.status(feedbackService.submitFeedback(feedbackDTO));
	}

	@PostMapping("/getFeedbackDetail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "意见反馈详情")
	public R<FeedbackVO> getFeedbackDetail(@RequestBody FeedbackDTO feedbackDTO) {
		return R.data(feedbackService.getFeedbackDetail(feedbackDTO));
	}

	@PostMapping("/deleteFeedback")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "删除意见反馈")
	public R deleteFeedback(@RequestBody FeedbackDTO feedbackDTO) {
		return R.status(feedbackService.deleteFeedback(feedbackDTO));
	}

	@PostMapping("/hasContact")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "设置为已联系")
	public R hasContact(@RequestBody FeedbackDTO feedbackDTO) {
		return R.status(feedbackService.hasContact(feedbackDTO));
	}
}
