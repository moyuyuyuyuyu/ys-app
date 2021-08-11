package com.microstone.app.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.microstone.app.dto.CustomerDTO;
import com.microstone.app.dto.customer.CbsCustomerDTO;
import com.microstone.app.entity.Customer;
import com.microstone.app.param.GetCustomerPageListParam;
import com.microstone.app.param.GetCustomerParam;
import com.microstone.app.param.SetCustomerFocusParam;
import com.microstone.app.service.ICustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.microstone.core.boot.ctrl.MsController;
import org.microstone.core.tool.api.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/customer")
@Api(value = "", tags = "接口")
public class CustomerController extends MsController {

    private final ICustomerService customerService;

    /**
     * 保存客户
     * */
    @PostMapping("saveCustomer")
    @ApiOperation(value = "保存客户")
    public R saveCustomer(@RequestBody CustomerDTO dto){
        customerService.saveCustomer(dto);
        return R.status(true);
    }

    /**
     * 保存客户列表
     * */
    @PostMapping("getCustomerPageList")
    @ApiOperation(value = "获取客户列表")
    public R<IPage<Customer>> getCustomerPageList(@RequestBody GetCustomerPageListParam param){
        return R.data(customerService.getCustomerPageList(param));
    }

    /**
     * 获取客户信息
     * */
    @PostMapping("getCustomer")
    @ApiOperation(value = "获取客户列表")
    public R<CustomerDTO> getCustomer(@RequestBody GetCustomerParam param){
        return R.data(customerService.getCustomer(param));
    }


    /**
     * 同步所有客户
     * */
    @PostMapping("syncAllCustomer")
    @ApiOperation(value = "同步所有客户")
    public R syncAllCustomer(){
        customerService.syncAllCustomer();
        return R.status(true);
    }


    /**
     * 设置是否关注
     * */
    @PostMapping("setCustomerFocus")
    @ApiOperation(value = "设置是否关注")
    public R setCustomerFocus(@RequestBody SetCustomerFocusParam param){
        customerService.setCustomerFocus(param);
        return R.status(true);
    }

    /**
     * 设置是否关注
     * */
    @PostMapping("/addCluesCustomer")
    @ApiOperation(value = "添加线索客户")
    public R<Boolean> addCluesCustomer(@RequestBody CbsCustomerDTO cbsCustomerDTO) throws Exception {
        return R.status(customerService.addCluesCustomer(cbsCustomerDTO));
    }
}
