package com.microstone.app.controller;

import com.microstone.app.entity.UserCertificate;
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
import com.microstone.app.service.IUserCertificateService;

import java.util.List;

/**
 *  控制器
 *
 * @author Chalsee
 * @since 2021-06-07
 */
@RestController
@AllArgsConstructor
@RequestMapping("/userCertificate")
@Api(value = "", tags = "接口")
public class UserCertificateController extends MsController {

	private final IUserCertificateService userCertificateService;

	/**
	 * 保存证书
	 * */
	@PostMapping("saveUserCertificate")
	@ApiOperation(value = "保存证书")
	public R saveUserCertificate(@RequestBody UserCertificate certificate){
		userCertificateService.saveUserCertificate(certificate);
		return R.status(true);
	}


	/**
	 * 获取证书列表
	 * */
	@PostMapping("getUserCertificateList")
	@ApiOperation(value = "获取证书列表")
	public R<List<UserCertificate>> getUserCertificateList(@RequestBody GetUserInfoParam param){
		return R.data(userCertificateService.getUserCertificateList(param));
	}
}
