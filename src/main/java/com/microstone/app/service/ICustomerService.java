package com.microstone.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.microstone.app.dto.CustomerDTO;
import com.microstone.app.dto.customer.CbsCustomerDTO;
import com.microstone.app.entity.Customer;
import com.microstone.app.entity.Product;
import com.microstone.app.param.GetCustomerPageListParam;
import com.microstone.app.param.GetCustomerParam;
import com.microstone.app.param.SetCustomerFocusParam;
import org.microstone.core.mp.base.BaseService;

public interface ICustomerService extends BaseService<Customer> {

    /**
     * 保存客户
     * */
    void saveCustomer(CustomerDTO dto);

    /**
     * 获取客户列表
     * */
    IPage<Customer> getCustomerPageList(GetCustomerPageListParam param);

    /**
     * 获取客户信息
     * */
    CustomerDTO getCustomer(GetCustomerParam param);

    CustomerDTO getCustomerByRelationId(Long relationId);


    void setCustomerFocus(SetCustomerFocusParam param);
    /**
     * 同步所有客户
     * */
    void syncAllCustomer();

    Boolean addCluesCustomer(CbsCustomerDTO cbsCustomerDTO) throws Exception;
}
