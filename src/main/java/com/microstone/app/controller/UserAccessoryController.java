package com.microstone.app.controller;

import com.microstone.app.entity.UserAccessory;
import com.microstone.app.param.GetUserInfoParam;
import com.microstone.app.service.IUserAccessoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import lombok.AllArgsConstructor;

import javax.validation.Valid;

import org.microstone.core.boot.ctrl.MsController;
import org.microstone.core.tool.api.R;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 控制器
 *
 * @author Chalsee
 * @since 2021-06-07
 */
@RestController
@AllArgsConstructor
@RequestMapping("/userAccessory")
@Api(value = "", tags = "接口")
public class UserAccessoryController extends MsController {

    private final IUserAccessoryService userAccessoryService;

    /**
     * 保存用户附件
     */
    @PostMapping("saveUserAccessory")
    @ApiOperation(value = "保存用户附件")
    public R saveUserAccessory(@RequestBody UserAccessory entity) {
        userAccessoryService.saveUserAccessory(entity);
        return R.status(true);
    }


    /**
     * 保存用户附件list
     */
    @PostMapping("saveUserAccessoryList")
    @ApiOperation(value = "保存用户附件")
    public R saveUserAccessoryList(@RequestBody List<UserAccessory> entityList) {
        for (UserAccessory entity : entityList) {
            userAccessoryService.saveUserAccessory(entity);
        }
        return R.status(true);
    }


    /**
     * 获取用户附件列表
     */
    @PostMapping("getUserAccessoryList")
    @ApiOperation(value = "获取用户附件列表")
    public R<List<UserAccessory>> getUserAccessoryList(@RequestBody GetUserInfoParam param) {
        return R.data(userAccessoryService.getUserAccessoryList(param));
    }
}
