package com.microstone.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.microstone.app.cache.CustomerPermissionCache;
import com.microstone.app.dto.CustomerDTO;
import com.microstone.app.dto.CustomerElementDTO;
import com.microstone.app.dto.ProductDTO;
import com.microstone.app.dto.ProductElementDTO;
import com.microstone.app.dto.customer.CbsCustomerDTO;
import com.microstone.app.entity.Customer;
import com.microstone.app.entity.CustomerElement;
import com.microstone.app.entity.Product;
import com.microstone.app.entity.ProductElement;
import com.microstone.app.entity.User;
import com.microstone.app.feign.ICustomerClient;
import com.microstone.app.mapper.CustomerMapper;
import com.microstone.app.mapper.ProductMapper;
import com.microstone.app.param.GetCustomerPageListParam;
import com.microstone.app.param.GetCustomerParam;
import com.microstone.app.param.SetCustomerFocusParam;
import com.microstone.app.service.ICustomerElementService;
import com.microstone.app.service.ICustomerService;
import com.microstone.app.service.IProductService;
import com.microstone.app.service.IUserService;
import org.microstone.core.log.exception.ServiceException;
import org.microstone.core.mp.base.BaseServiceImpl;
import org.microstone.core.mp.support.Condition;
import org.microstone.core.secure.utils.AuthUtil;
import org.microstone.core.tool.api.R;
import org.microstone.core.tool.utils.Func;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.invoke.LambdaConversionException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl extends BaseServiceImpl<CustomerMapper, Customer> implements ICustomerService {

    @Resource
    private ICustomerElementService customerElementService;

    @Resource
    private ICustomerClient customerClient;

    @Resource
    @Lazy
    private IUserService userService;


    /**
     * 保存客户
     */
    @Override
    public void saveCustomer(CustomerDTO dto) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(dto, customer);
        customer.setUserId(AuthUtil.getAppUserId());
        if (Func.isNull(customer.getStockAsset())) {
            customer.setStockAsset(BigDecimal.ZERO);
        }
        if (Func.isNull(customer.getIncomeAsset())) {
            customer.setIncomeAsset(BigDecimal.ZERO);
        }
        if (dto.getIsDeleted() == 1 && dto.getId() != null) {
            this.removeById(dto.getId());
            if (Func.notNull(dto.getCustomerElementList())) {
                for (CustomerElementDTO elementDTO : dto.getCustomerElementList()) {
                    customerElementService.removeById(elementDTO.getId());
                }
            }
        } else {
            this.saveOrUpdate(customer);
            if (Func.notNull(dto.getCustomerElementList())) {
                for (CustomerElementDTO elementDTO : dto.getCustomerElementList()) {
                    elementDTO.setCustomerId(customer.getId());
                    customerElementService.saveOrUpdate(elementDTO);
                }
            }
        }

    }


    /**
     * 获取客户列表
     */
    public IPage<Customer> getCustomerPageList(GetCustomerPageListParam param) {
        List<Long> userIdList = new ArrayList<>();
        List<Long> userIds = CustomerPermissionCache.getCustomerPermission(Func.toLong(AuthUtil.getAppUserId()));
        if (Func.isNull(userIds) || userIds.size() == 0)
            userIdList.add(AuthUtil.getAppUserId());
        else
            userIdList = userIds;

        IPage<Customer> page = Condition.getPage(param);
        LambdaQueryWrapper<Customer> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Customer::getUserId, userIdList).orderByDesc(Customer::getCreateTime);
        //queryWrapper.in(Customer::getUserId, AuthUtil.getAppUserId()).orderByDesc(Customer::getCreateTime);

        if (param.getGroupId() != null) {
            queryWrapper = queryWrapper.eq(Customer::getGroupId, param.getGroupId());
        }
        if (param.getFocus() != null) {
            queryWrapper = queryWrapper.eq(Customer::getFocus, param.getFocus());
        }
        if (param.getName() != null && !param.getName().equals("")) {
            queryWrapper = queryWrapper.like(Customer::getName, param.getName());
        }
        return this.page(page, queryWrapper);
    }

    @Override
    public void setCustomerFocus(SetCustomerFocusParam param) {
        Customer customer = this.getById(param.getId());
        customer.setFocus(param.getFocus());
        this.saveOrUpdate(customer);
    }


    /**
     * 获取客户信息
     */
    public CustomerDTO getCustomer(GetCustomerParam param) {
        Customer customer = this.getById(param.getId());
        CustomerDTO dto = new CustomerDTO();
        BeanUtils.copyProperties(customer, dto);
        List<CustomerElement> productElementList = customerElementService.list(new LambdaQueryWrapper<CustomerElement>()
                .eq(CustomerElement::getIsDeleted, 0)
                .eq(CustomerElement::getCustomerId, customer.getId())
                .orderByAsc(CustomerElement::getSort));
        List<CustomerElementDTO> customerElementDTOList = new ArrayList<>();
        for (CustomerElement element : productElementList) {
            CustomerElementDTO elementDTO = new CustomerElementDTO();
            BeanUtils.copyProperties(element, elementDTO);
            customerElementDTOList.add(elementDTO);
        }
        dto.setCustomerElementList(customerElementDTOList);
        return dto;
    }


    public CustomerDTO getCustomerByRelationId(Long relationId) {
        Customer customer = this.getOne(new LambdaQueryWrapper<Customer>().eq(Customer::getRelationId, relationId));
        if (customer == null) {
            return null;
        }
        CustomerDTO dto = new CustomerDTO();
        BeanUtils.copyProperties(customer, dto);
        List<CustomerElement> productElementList = customerElementService.list(new LambdaQueryWrapper<CustomerElement>()
                .eq(CustomerElement::getIsDeleted, 0)
                .eq(CustomerElement::getCustomerId, customer.getId())
                .orderByAsc(CustomerElement::getSort));
        List<CustomerElementDTO> customerElementDTOList = new ArrayList<>();
        for (CustomerElement element : productElementList) {
            CustomerElementDTO elementDTO = new CustomerElementDTO();
            BeanUtils.copyProperties(element, elementDTO);
            customerElementDTOList.add(elementDTO);
        }
        dto.setCustomerElementList(customerElementDTOList);
        return dto;
    }

    /**
     * 同步所有客户
     */
    @Override
    public void syncAllCustomer() {
        List<CbsCustomerDTO> list = customerClient.syncAllCustomer().getData();
        for (CbsCustomerDTO cbsCustomerDTO : list) {
            CustomerDTO dto = new CustomerDTO();
            User user = userService.getById(cbsCustomerDTO.getId());
            if (user != null) {
                dto.setUserId(user.getId());
            }
            dto.setRelationId(cbsCustomerDTO.getId());
            dto.setCustomerStatus(cbsCustomerDTO.getType());
            dto.setName(cbsCustomerDTO.getName());
            dto.setIncomeAsset(BigDecimal.ZERO);
            dto.setStockAsset(BigDecimal.ZERO);
            dto.setSource(2);
            this.saveCustomer(dto);
        }
    }

    @Override
    public Boolean addCluesCustomer(CbsCustomerDTO cbsCustomerDTO) throws Exception {
        R<Boolean> r = customerClient.addCluesCustomer(cbsCustomerDTO);
        if (!Func.equals(r.getCode(), 200)) throw new ServiceException(r.getMsg());
        return r.isSuccess();
    }

}
