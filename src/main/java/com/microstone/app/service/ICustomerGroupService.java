package com.microstone.app.service;

import com.microstone.app.dto.CustomerGroupDTO;
import com.microstone.app.entity.Customer;
import com.microstone.app.entity.CustomerGroup;
import org.microstone.core.mp.base.BaseService;

import java.util.List;

public interface ICustomerGroupService extends BaseService<CustomerGroup> {

    /**
     * 保存分组
     * */
    void saveCustomerGroup(CustomerGroupDTO dto);

    /**
     * 获取客户分组
     * */
    List<CustomerGroupDTO> getCustomerGroupList();
}
