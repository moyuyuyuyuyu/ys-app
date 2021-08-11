package com.microstone.app.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.microstone.app.dto.ArticleDTO;
import com.microstone.app.dto.WechatOfficialAccountDTO;
import com.microstone.app.query.ArticleQuery;
import com.microstone.app.service.IArticleService;
import com.microstone.app.service.IVideoPoolService;
import com.microstone.app.service.IWechatOfficialAccountService;
import com.microstone.app.vo.ArticleVO;
import com.microstone.app.vo.VideoVO;
import com.microstone.app.vo.WechatOfficialAccountVO;
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

/**
 *  控制器
 *
 * @author Chalsee
 * @since 2021-05-27
 */
@RestController
@AllArgsConstructor
@RequestMapping("/wechatofficialaccount")
@Api(value = "微信公众号", tags = "接口")
public class WechatOfficialAccountController extends MsController {
	private final IWechatOfficialAccountService wechatOfficialAccountService;

	@PostMapping("/getWechatOfficialAccount")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "获取微信公众号列表")
	public R<Page<WechatOfficialAccountVO>> getWechatOfficialAccount(@RequestBody Query query) {
		return R.data(wechatOfficialAccountService.getWechatOfficialAccount(query));
	}

	@PostMapping("/saveWechatOfficialAccount")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "新增微信公众号")
	public R saveWechatOfficialAccount(@RequestBody WechatOfficialAccountDTO wechatOfficialAccountDTO) {
		return R.data(wechatOfficialAccountService.saveWechatOfficialAccount(wechatOfficialAccountDTO));
	}

	@PostMapping("/deleteWechatOfficialAccount")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "删除微信公众号")
	public R deleteWechatOfficialAccount(@RequestBody WechatOfficialAccountDTO wechatOfficialAccountDTO) {
		return R.data(wechatOfficialAccountService.deleteWechatOfficialAccount(wechatOfficialAccountDTO));
	}
}
