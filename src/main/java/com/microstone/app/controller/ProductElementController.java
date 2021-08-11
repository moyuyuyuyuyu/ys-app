package com.microstone.app.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.microstone.core.boot.ctrl.MsController;
import org.springframework.web.bind.annotation.*;
import com.microstone.app.service.IProductElementService;

/**
 *  控制器
 *
 * @author Chalsee
 * @since 2021-05-27
 */
@RestController
@AllArgsConstructor
@RequestMapping("/productelement")
@Api(value = "", tags = "接口")
public class ProductElementController extends MsController {

	private final IProductElementService productElementService;

}
