package com.microstone.app.controller;

import com.microstone.app.entity.UserEducation;
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
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.microstone.app.service.IUserEducationService;

import java.util.List;

/**
 *  控制器
 *
 * @author Chalsee
 * @since 2021-06-07
 */
@RestController
@AllArgsConstructor
@RequestMapping("/userEducation")
@Api(value = "", tags = "接口")
public class UserEducationController extends MsController {

	private final IUserEducationService userEducationService;


	/**
	 * 保存教育经历
	 */
	@PostMapping("saveUserEducation")
	@ApiOperation(value = "保存教育经历")
	public R saveUserEducation(@RequestBody UserEducation entity){
		userEducationService.saveUserEducation(entity);
		return R.status(true);
	}

	/**
	 * 获取教育经历列表
	 */
	@PostMapping("getUserEducationList")
	@ApiOperation(value = "获取教育经历列表")
	public R<List<UserEducation>> getUserEducationList(@RequestBody GetUserInfoParam param){
		return R.data(userEducationService.getUserEducationList(param));
	}



}
