package com.microstone.app.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.microstone.app.cache.CustomerPermissionCache;
import com.microstone.app.dto.GetUserListDTO;
import com.microstone.app.dto.UserDTO;
import com.microstone.app.param.GetUserInfoParam;
import com.microstone.app.param.GetUserListParam;
import com.microstone.system.feign.IUserClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

import org.microstone.core.boot.ctrl.MsController;
import org.microstone.core.cache.utils.CacheUtil;
import org.microstone.core.secure.utils.AuthUtil;
import org.microstone.core.tool.api.R;
import org.microstone.core.tool.utils.Func;
import org.springframework.web.bind.annotation.*;
import com.microstone.app.service.IUserService;

import java.util.List;

import static org.microstone.core.cache.constant.CacheConstant.APP_CACHE;

/**
 *  控制器
 *
 * @author Chalsee
 * @since 2021-06-07
 */
@RestController
@AllArgsConstructor
@RequestMapping("/user")
@Api(value = "", tags = "接口")
public class UserController extends MsController {

	private final IUserService userService;
	private final IUserClient userClient;



	/**
	 * 保存用户信息
	 * */
	@PostMapping("saveUser")
	@ApiOperation(value = "分享")
	public R<UserDTO> saveUser(@RequestBody UserDTO dto){
		CacheUtil.clear(APP_CACHE,Boolean.FALSE);
		return R.data(userService.saveUser(dto));
	}


	/**
	 * 获取用户信息
	 * */
	@PostMapping("getUserInfo")
	@ApiOperation(value = "分享")
	public R<UserDTO> getUserInfo(@RequestBody GetUserInfoParam param){
		return R.data(userService.getUser(param));
	}



	/**
	 * 获取用户分页
	 * */
	@PostMapping("getUserList")
	@ApiOperation(value = "获取用户分页")
	public R<IPage<GetUserListDTO>> getUserList(@RequestBody GetUserListParam param){
		return R.data(userService.getUserList(param));
	}

	/**
	 * 获取用户分页
	 * */
	@GetMapping("test")
	@ApiOperation(value = "获取用户分页")
	public R<List<Long>> getUserList(@RequestParam String appUserId){
		return R.data(userClient.appCustomerPermission(Func.toLong(appUserId)).getData());
	}


//	/**
//	 * 获取用户分页
//	 * */
//	@GetMapping("test2")
//	@ApiOperation(value = "获取用户分页")
//	public R<Boolean> getHasApp(){
//		Boolean hasApp = AuthUtil.hasApp();
//		return R.data(hasApp);
//	}
}
