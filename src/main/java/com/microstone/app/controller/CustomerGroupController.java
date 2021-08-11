package com.microstone.app.controller;

import com.microstone.app.dto.CustomerGroupDTO;
import com.microstone.app.service.IArticleService;
import com.microstone.app.service.ICustomerGroupService;
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
@RequestMapping("/customerGroup")
@Api(value = "", tags = "接口")
public class CustomerGroupController extends MsController {

    private final ICustomerGroupService customerGroupService;


    /**
     * 保存客户分组列表
     * */
    @PostMapping("saveCustomerGroup")
    @ApiOperation(value = "保存客户分组列表")
    public R saveCustomerGroup(@RequestBody CustomerGroupDTO dto){
        customerGroupService.saveCustomerGroup(dto);
        return R.data(true);
    }

    /**
     * 获取客户分组列表
     * */
    @PostMapping("getCustomerGroupList")
    @ApiOperation(value = "获取客户分组列表")
    public R<List<CustomerGroupDTO>> getCustomerGroupList(){
        return R.data(customerGroupService.getCustomerGroupList());
    }
}
