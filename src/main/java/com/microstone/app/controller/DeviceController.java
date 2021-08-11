package com.microstone.app.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.microstone.app.dto.DeviceDTO;
import com.microstone.app.dto.DocumentDTO;
import com.microstone.app.query.ArticleQuery;
import com.microstone.app.service.IDeviceService;
import com.microstone.app.service.IDocumentService;
import com.microstone.app.vo.DocumentVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.microstone.core.boot.ctrl.MsController;
import org.microstone.core.tool.api.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 *  控制器
 *
 * @author Chalsee
 * @since 2021-05-27
 */
@RestController
@AllArgsConstructor
@RequestMapping("/device")
@Api(value = "", tags = "接口")
public class DeviceController extends MsController {

	private final IDeviceService deviceService;

	/**
	 * @author XieXiaoDong
	 * @date 2021/8/9/0009
	 * @description 保存设备
	 */
	@PostMapping("/saveDevice")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "保存设备")
	public R saveDevice(@RequestBody DeviceDTO deviceDTO) {
		return R.status(deviceService.saveDevice(deviceDTO));
	}

	/**
	 * @author XieXiaoDong
	 * @date 2021/8/9/0009
	 * @description 保存设备
	 */
	@PostMapping("/clearDevice")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "解除绑定")
	public R clearDevice(@RequestBody DeviceDTO deviceDTO) {
		return R.status(deviceService.clearDevice(deviceDTO));
	}

}
