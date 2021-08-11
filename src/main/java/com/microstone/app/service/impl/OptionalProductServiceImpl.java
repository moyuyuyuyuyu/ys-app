package com.microstone.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.microstone.app.entity.OptionalProduct;
import com.microstone.app.mapper.OptionalProductMapper;
import com.microstone.app.service.IOptionalProductService;
import lombok.Data;
import org.microstone.core.mp.base.BaseServiceImpl;
import org.microstone.core.secure.utils.AuthUtil;
import org.springframework.stereotype.Service;

@Service
public class OptionalProductServiceImpl extends BaseServiceImpl<OptionalProductMapper, OptionalProduct> implements IOptionalProductService {


    @Override
    public void saveOptionalProduct(OptionalProduct dto){
        dto.setUserId(AuthUtil.getAppUserId());
        OptionalProduct optionalProduct = this.getOne(new LambdaQueryWrapper<OptionalProduct>()
                .eq(OptionalProduct::getProductId, dto.getProductId())
                .eq(OptionalProduct::getUserId, AuthUtil.getAppUserId())
                .eq(OptionalProduct::getIsDeleted, 0));
        if(dto.getIsDeleted() == 1){
            this.removeById(optionalProduct.getId());
        }else{
            this.saveOrUpdate(dto);
        }
    }
}
