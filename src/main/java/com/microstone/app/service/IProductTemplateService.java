package com.microstone.app.service;

import com.microstone.app.entity.ProductTemplate;
import com.microstone.app.param.GetProductTemplateParam;
import org.microstone.core.mp.base.BaseService;

import java.util.List;

public interface IProductTemplateService extends BaseService<ProductTemplate> {

    /**
     * 获取模板列表
     * */
    List<ProductTemplate> getProductTemplate(GetProductTemplateParam param);
}
