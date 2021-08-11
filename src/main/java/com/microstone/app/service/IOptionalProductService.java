package com.microstone.app.service;

import com.microstone.app.entity.OptionalProduct;
import org.microstone.core.mp.base.BaseService;

public interface IOptionalProductService extends BaseService<OptionalProduct> {

    void saveOptionalProduct(OptionalProduct dto);
}
