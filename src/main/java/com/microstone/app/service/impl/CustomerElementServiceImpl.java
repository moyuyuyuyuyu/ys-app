package com.microstone.app.service.impl;

import com.microstone.app.entity.CustomerElement;
import com.microstone.app.entity.CustomerGroup;
import com.microstone.app.mapper.CustomerElementMapper;
import com.microstone.app.mapper.CustomerGroupMapper;
import com.microstone.app.service.ICustomerElementService;
import com.microstone.app.service.ICustomerGroupService;
import org.microstone.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CustomerElementServiceImpl extends BaseServiceImpl<CustomerElementMapper, CustomerElement> implements ICustomerElementService {
}
