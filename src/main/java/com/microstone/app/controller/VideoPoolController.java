package com.microstone.app.controller;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;

import org.microstone.core.boot.ctrl.MsController;
import org.springframework.web.bind.annotation.*;
import com.microstone.app.service.IVideoPoolService;

/**
 *  控制器
 *
 * @author Chalsee
 * @since 2021-05-27
 */
@RestController
@AllArgsConstructor
@RequestMapping("/videopool")
@Api(value = "", tags = "接口")
public class VideoPoolController extends MsController {

	private final IVideoPoolService videoPoolService;

}
