package com.microstone.app.controller;

import com.microstone.app.entity.UserLabel;
import com.microstone.app.param.GetUserInfoParam;
import com.microstone.app.service.IUserLabelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.microstone.core.boot.ctrl.MsController;
import org.microstone.core.tool.api.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/userLabel")
@Api(value = "", tags = "接口")
public class UserLabelController extends MsController {

    private final IUserLabelService userLabelService;

    @PostMapping("saveUserLabel")
    @ApiOperation(value = "保存用户标签")
    public R saveUserLabel(@RequestBody UserLabel entity){
        userLabelService.saveUserLabel(entity);
        return R.status(true);
    }

    @PostMapping("getUserLabelList")
    @ApiOperation(value = "获取用户标签")
    public R<List<UserLabel>> getUserLabelList(@RequestBody GetUserInfoParam param){
        return R.data(userLabelService.getUserLabelList(param));
    }


    @PostMapping("clickLabel")
    @ApiOperation(value = "点赞")
    public R clickLabel(@RequestBody UserLabel label){
        userLabelService.clickLabel(label);
        return R.status(true);
    }

}
