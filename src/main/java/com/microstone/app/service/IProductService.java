package com.microstone.app.service;

import com.microstone.app.dto.ProductDTO;
import com.microstone.app.entity.Product;
import com.microstone.app.param.GetOptionalProductPageListParam;
import com.microstone.app.param.GetProductPageListParam;
import com.microstone.app.param.GetProductParam;
import com.microstone.app.param.SetProductStatusParam;
import com.microstone.app.vo.ProductVO;
import org.microstone.core.mp.base.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.multipart.MultipartFile;

/**
 *  服务类
 *
 * @author Chalsee
 * @since 2021-05-27
 */
public interface IProductService extends BaseService<Product> {


    /**
     * 保存产品
     */
    void saveProduct(ProductDTO dto);

    /**
     * 保存产品
     */
    void saveAppProduct(ProductDTO dto);

    /**
     * 获取产品列表
     * */
    IPage<ProductDTO> getProductPageList(GetProductPageListParam param);

    /**
     * 获取产品详情
     * */
    ProductDTO getProduct(GetProductParam param);

    /**
     * 设置产品状态
     * */
    void setProductStatus(SetProductStatusParam param);

    /**
     * 获取自选产品列表
     * */
    IPage<Product> getOptionalProductPageList(GetOptionalProductPageListParam param);
    /**
     * 导入产品
     * */
    void importProduct(MultipartFile file);

    void syncAllProduct();

    void setAppProductStatus(SetProductStatusParam param);
}
