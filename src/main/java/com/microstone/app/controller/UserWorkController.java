package com.microstone.app.controller;

import com.microstone.app.entity.UserWork;
import com.microstone.app.param.GetUserInfoParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.microstone.core.boot.ctrl.MsController;
import org.microstone.core.tool.api.R;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import com.microstone.app.service.IUserWorkService;

import java.util.List;

/**
 *  控制器
 *
 * @author Chalsee
 * @since 2021-06-07
 */
@RestController
@AllArgsConstructor
@RequestMapping("/userWork")
@Api(value = "", tags = "接口")
public class UserWorkController extends MsController {

	private final IUserWorkService userWorkService;


	/**
	 * 保存工作经历
	 */
	@PostMapping("saveUserWork")
	@ApiOperation(value = "保存工作经历")
	public R saveUserWork(@RequestBody UserWork entity){
		userWorkService.saveUserWork(entity);
		return R.status(true);
	}


	/**
	 * 获取工作经历列表
	 */
	@PostMapping("getUserWorkList")
	@ApiOperation(value = "获取工作经历列表")
	public R<List<UserWork>> getUserWorkList(@RequestBody GetUserInfoParam param){
		return R.data(userWorkService.getUserWorkList(param));
	}
}
