package com.microstone.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.microstone.app.dto.CustomerGroupDTO;
import com.microstone.app.entity.Customer;
import com.microstone.app.entity.CustomerGroup;
import com.microstone.app.mapper.CustomerGroupMapper;
import com.microstone.app.mapper.CustomerMapper;
import com.microstone.app.service.ICustomerGroupService;
import com.microstone.app.service.ICustomerService;
import org.microstone.core.mp.base.BaseServiceImpl;
import org.microstone.core.secure.utils.AuthUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerGroupServiceImpl extends BaseServiceImpl<CustomerGroupMapper, CustomerGroup> implements ICustomerGroupService {


    /**
     * 保存分组
     * */
    @Override
    public void saveCustomerGroup(CustomerGroupDTO dto){
        CustomerGroup customerGroup = new CustomerGroup();
        BeanUtils.copyProperties(dto, customerGroup);
        if(customerGroup.getId() == null){
            customerGroup.setUserId(AuthUtil.getAppUserId());
            customerGroup.setCount(0);
        }
        if(customerGroup.getIsDeleted() == 0 && customerGroup.getId() != null){
            this.removeById(customerGroup.getId());
        }else{
            this.saveOrUpdate(dto);
        }
    }

    /**
     * 获取客户分组
     * */
    @Override
    public List<CustomerGroupDTO> getCustomerGroupList(){
        List<CustomerGroup> list = this.list(new LambdaQueryWrapper<CustomerGroup>()
                .eq(CustomerGroup::getIsDeleted, 0)
                .eq(CustomerGroup::getUserId, AuthUtil.getAppUserId())
                .orderByAsc(CustomerGroup::getSort));
        List<CustomerGroupDTO> result = new ArrayList<>();
        for(CustomerGroup group : list){
            CustomerGroupDTO dto = new CustomerGroupDTO();
            BeanUtils.copyProperties(group, dto);
            result.add(dto);
        }
        return result;
    }

}
