package com.microstone.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.microstone.app.entity.ProductTemplate;
import com.microstone.app.mapper.ProductTemplateMapper;
import com.microstone.app.param.GetProductTemplateParam;
import com.microstone.app.service.IProductTemplateService;
import org.microstone.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTemplateServiceImpl extends BaseServiceImpl<ProductTemplateMapper, ProductTemplate> implements IProductTemplateService {


    /**
     * 获取模板列表
     * */
    @Override
    public List<ProductTemplate> getProductTemplate(GetProductTemplateParam param){
        List<ProductTemplate> productTemplateList = this.list(new LambdaQueryWrapper<ProductTemplate>()
                .eq(ProductTemplate::getType, param.getType())
                .eq(ProductTemplate::getIsDeleted, 0)
                .orderByAsc(ProductTemplate::getSort));
        return productTemplateList;
    }







}
