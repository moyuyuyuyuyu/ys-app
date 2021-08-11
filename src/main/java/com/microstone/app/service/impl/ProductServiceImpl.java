package com.microstone.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.microstone.app.dto.CrmProductDTO;
import com.microstone.app.dto.ImportProductExcel;
import com.microstone.app.dto.ProductDTO;
import com.microstone.app.dto.ProductElementDTO;
import com.microstone.app.entity.OptionalProduct;
import com.microstone.app.entity.Position;
import com.microstone.app.entity.Product;
import com.microstone.app.entity.ProductElement;
import com.microstone.app.feign.IProductClient;
import com.microstone.app.param.GetOptionalProductPageListParam;
import com.microstone.app.param.GetProductPageListParam;
import com.microstone.app.param.GetProductParam;
import com.microstone.app.param.SetProductStatusParam;
import com.microstone.app.service.IOptionalProductService;
import com.microstone.app.service.IPositionService;
import com.microstone.app.service.IProductElementService;
import com.microstone.app.vo.ProductVO;
import com.microstone.app.mapper.ProductMapper;
import com.microstone.app.service.IProductService;
import org.microstone.core.excel.util.ExcelUtil;
import org.microstone.core.log.exception.ServiceException;
import org.microstone.core.mp.base.BaseServiceImpl;
import org.microstone.core.mp.support.Condition;
import org.microstone.core.secure.utils.AuthUtil;
import org.microstone.core.tool.utils.Func;
import org.microstone.core.tool.utils.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 服务实现类
 *
 * @author Chalsee
 * @since 2021-05-27
 */
@Service
public class ProductServiceImpl extends BaseServiceImpl<ProductMapper, Product> implements IProductService {

    @Resource
    private IProductElementService productElementService;

    @Resource
    private IOptionalProductService optionalProductService;

    @Resource
    private IProductClient productClient;

    @Resource
    @Lazy
    private IPositionService positionService;

    /**
     * 保存产品
     */
    @Override
    public void saveProduct(ProductDTO dto) {
        Product product = new Product();
        BeanUtils.copyProperties(dto, product);
        if (product.getId() == null) {
            product.setProductStatus(1);
            product.setLastNetValue(product.getLastNetValue() == null ? BigDecimal.ONE : product.getLastNetValue());
            if (product.getSource() == null) {
                product.setSource(1);
            }
            if (product.getExchangeRate() == null) {
                product.setExchangeRate(BigDecimal.ONE);
            }
        } else {
            if (product.getSource() != 1) {
                throw new ServiceException("无法修改产品信息");
            }
        }
        this.saveOrUpdate(product);
        if (Func.notNull(dto.getProductElementList())) {
            for (ProductElementDTO elementDTO : dto.getProductElementList()) {
                elementDTO.setProductId(product.getId());
                productElementService.saveOrUpdate(elementDTO);
            }
        }

    }


    /**
     * 保存产品
     */
    @Override
    public void saveAppProduct(ProductDTO dto) {
        Product product = new Product();
        BeanUtils.copyProperties(dto, product);
        product.setUserId(AuthUtil.getAppUserId());
        product.setProductStatus(2);
        product.setSource(1);
        if (product.getIsDeleted() == 1 && product.getId() != null) {
            this.removeById(product);
            for (ProductElementDTO elementDTO : dto.getProductElementList()) {
                productElementService.removeById(elementDTO.getId());
            }
        } else {
            this.saveOrUpdate(product);
            for (ProductElementDTO elementDTO : dto.getProductElementList()) {
                elementDTO.setProductId(product.getId());
                productElementService.saveOrUpdate(elementDTO);
            }
        }
    }


    /**
     * 获取产品列表
     */
    @Override
    public IPage<ProductDTO> getProductPageList(GetProductPageListParam param) {
        IPage<Product> page = Condition.getPage(param);
        LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<>();
        if (param.getPublishDateStart() != null) {
            queryWrapper = queryWrapper.gt(Product::getPublishDateEnd, param.getPublishDateStart());
        }
        if (param.getPublishDateEnd() != null) {
            queryWrapper = queryWrapper.lt(Product::getPublishDateStart, param.getPublishDateEnd());
        }
        if (param.getType() != null) {
            queryWrapper = queryWrapper.eq(Product::getType, param.getType());
        }
        if (param.getSelf() != null && param.getSelf()) {
            queryWrapper = queryWrapper.eq(Product::getUserId, AuthUtil.getAppUserId());
        }
        if (param.getSelf() != null && !param.getSelf()) {
            queryWrapper = queryWrapper.isNull(Product::getUserId);
        }
        if (param.getProductStatus() != null) {
            queryWrapper = queryWrapper.eq(Product::getProductStatus, param.getProductStatus());
        }
        if (!StringUtil.isEmpty(param.getName())) {
            queryWrapper = queryWrapper.like(Product::getName, param.getName());
        }
        IPage<Product> result = this.page(page, queryWrapper);
        IPage<ProductDTO> res = new Page<>();
        res.setSize(result.getSize());
        res.setCurrent(result.getCurrent());
        res.setPages(result.getPages());
        res.setTotal(result.getTotal());
        List<ProductDTO> resList = new ArrayList<>();

        List<OptionalProduct> optionalProductList = optionalProductService.list(new LambdaQueryWrapper<OptionalProduct>().eq(OptionalProduct::getIsDeleted, 0).eq(OptionalProduct::getUserId, AuthUtil.getAppUserId()));
        for (Product product : result.getRecords()) {
            ProductDTO dto = new ProductDTO();
            BeanUtils.copyProperties(product, dto);
            Integer count = optionalProductList.stream().filter(t -> t.getProductId().equals(dto.getId())).collect(Collectors.toList()).size();
            dto.setHasOptional(count > 0);
            resList.add(dto);
        }
        res.setRecords(resList);
        return res;
    }


    /**
     * 获取自选产品列表
     */
    @Override
    public IPage<Product> getOptionalProductPageList(GetOptionalProductPageListParam param) {


        List<Long> productIds = optionalProductService.list(new LambdaQueryWrapper<OptionalProduct>()
                .eq(OptionalProduct::getIsDeleted, 0)
                .eq(OptionalProduct::getUserId, AuthUtil.getAppUserId())).stream().map(t -> t.getProductId()).collect(Collectors.toList());
        IPage<Product> page = Condition.getPage(param);
        if (productIds.isEmpty()) {
            page.setRecords(new ArrayList<>());
            page.setTotal(0);
            return page;
        }
        LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<>();
        if (param.getType() != null) {
            queryWrapper = queryWrapper.eq(Product::getType, param.getType());
        }
        if (!StringUtil.isEmpty(param.getName())) {
            queryWrapper = queryWrapper.like(Product::getName, param.getName());
        }

        IPage<Product> result = this.page(page, queryWrapper.eq(Product::getIsDeleted, 0)
                .in(Product::getId, productIds));
        return result;
    }


    /**
     * 获取产品详情
     */
    @Override
    public ProductDTO getProduct(GetProductParam param) {
        Product product = this.getById(param.getId());
        ProductDTO dto = new ProductDTO();
        BeanUtils.copyProperties(product, dto);
        List<ProductElement> productElementList = productElementService.list(new LambdaQueryWrapper<ProductElement>()
                .eq(ProductElement::getIsDeleted, 0)
                .eq(ProductElement::getProductId, product.getId())
                .orderByAsc(ProductElement::getSort));
        List<ProductElementDTO> productElementDTOList = new ArrayList<>();
        for (ProductElement element : productElementList) {
            ProductElementDTO elementDTO = new ProductElementDTO();
            BeanUtils.copyProperties(element, elementDTO);
            productElementDTOList.add(elementDTO);
        }
        dto.setProductElementList(productElementDTOList);
        int count = positionService.count(new LambdaQueryWrapper<Position>().eq(Position::getIsDeleted, 0)
                .eq(Position::getProductId, product.getId()));
        dto.setHasTrade(count > 0);
        return dto;
    }


    /**
     * 设置产品状态
     */
    @Override
    public void setProductStatus(SetProductStatusParam param) {
        Product product = this.getById(param.getId());
        product.setProductStatus(param.getProductStatus());
        this.saveOrUpdate(product);
    }

    /**
     * 设置cbs产品状态
     */
    @Override
    public void setAppProductStatus(SetProductStatusParam param) {
        Product product = this.getOne(new LambdaQueryWrapper<Product>().eq(Product::getRelationId, param.getId()));
        product.setProductStatus(param.getProductStatus());
        this.saveOrUpdate(product);
    }


    /**
     * 导入产品
     */
    @Override
    public void importProduct(MultipartFile file) {
        List<ImportProductExcel> dtoList = ExcelUtil.read(file, ImportProductExcel.class);
        for (ImportProductExcel dto : dtoList) {
            Product product = new Product();
            product.setName(dto.getName());
            product.setMaxIncomeRate(dto.getMaxIncomeRate());
            product.setMinIncomeRate(dto.getMinIncomeRate());
            product.setNote(dto.getNote());
            product.setProductStatus(1);
            product.setSource(1);
            if (dto.getType() != null && dto.getType().equals("地产"))
                product.setType(1);
            if (dto.getType() != null && dto.getType().equals("股权"))
                product.setType(2);
            if (dto.getType() != null && dto.getType().equals("证券"))
                product.setType(3);
            if (dto.getType() != null && dto.getType().equals("其它"))
                product.setType(4);

            if (dto.getCategory() != null && dto.getCategory().equals("公募"))
                product.setCategory(1);
            if (dto.getCategory() != null && dto.getCategory().equals("私募"))
                product.setCategory(2);
            this.saveOrUpdate(product);
        }
    }


    /**
     * 同步所有产品
     */
    @Override
    public void syncAllProduct() {
        List<CrmProductDTO> productList = productClient.syncAllProduct();
        for (CrmProductDTO productDTO : productList) {
            ProductDTO dto = new ProductDTO();
            dto.setName(productDTO.getName());
            dto.setCode(productDTO.getCode());
            dto.setType(productDTO.getType());
            dto.setProductScale(productDTO.getProductScale());
            dto.setStartScale(productDTO.getStartScale());
            dto.setInvestmentTermSum(productDTO.getInvestmentTermSum());
            dto.setMinIncomeRate(productDTO.getMinIncomeRate());
            dto.setMaxIncomeRate(productDTO.getMaxIncomeRate());
            dto.setSource(2);
            dto.setRelationId(productDTO.getProductId());
            this.saveProduct(dto);
        }
    }


}
