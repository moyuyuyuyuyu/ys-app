package com.microstone.app.controller;

import com.microstone.app.entity.Version;
import com.microstone.app.service.IVersionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.microstone.core.boot.ctrl.MsController;
import org.microstone.core.tool.api.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/version")
@Api(value = "", tags = "接口")
public class VersionController extends MsController {

    private IVersionService versionService;


    @PostMapping("getVersion")
    @ApiOperation(value = "获取版本号信息")
    public R<Version> getVersion(){
        return R.data(versionService.list().get(0));
    }
}
