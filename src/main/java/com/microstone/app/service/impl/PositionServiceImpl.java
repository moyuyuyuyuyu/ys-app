package com.microstone.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.microstone.app.dto.CurrentPositionDTO;
import com.microstone.app.dto.HistoryPositionDTO;
import com.microstone.app.dto.PositionCountDTO;
import com.microstone.app.dto.PositionDTO;
import com.microstone.app.entity.Customer;
import com.microstone.app.entity.Position;
import com.microstone.app.entity.PositionCount;
import com.microstone.app.entity.Product;
import com.microstone.app.feign.ITradeClient;
import com.microstone.app.mapper.PositionMapper;
import com.microstone.app.param.GetCurrentPositionListParam;
import com.microstone.app.param.GetCustomerPositionParam;
import com.microstone.app.param.GetPositionCountParam;
import com.microstone.app.param.GetProductPositionListParam;
import com.microstone.app.service.ICustomerService;
import com.microstone.app.service.IPositionCountService;
import com.microstone.app.service.IPositionService;
import com.microstone.app.service.IProductService;
import org.microstone.core.excel.util.ExcelUtil;
import org.microstone.core.mp.base.BaseServiceImpl;
import org.microstone.core.mp.support.Condition;
import org.microstone.core.tool.utils.Func;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PositionServiceImpl extends BaseServiceImpl<PositionMapper, Position> implements IPositionService {

    @Resource
    @Lazy
    private IProductService productService;

    @Lazy
    @Resource
    private ICustomerService customerService;

    @Resource
    private ITradeClient tradeClient;

    @Resource
    private IPositionCountService positionCountService;

    /**
     * 保存持仓
     */
    @Override
    public void savePosition(PositionDTO dto) {
        Position position = new PositionDTO();
        BeanUtils.copyProperties(dto, position);
        Product product = productService.getOne(new LambdaQueryWrapper<Product>().eq(Product::getId, dto.getProductId()));
        position.setProductName(product.getName());
        position.setProductType(product.getType());
        position.setOrderStatus(Func.isNull(dto.getOrderStatus()) ? 2 : dto.getOrderStatus());
        position.setTransShare(dto.getTransShare() == null ? false : dto.getTransShare());
        position.setCurrencyId(product.getCurrencyId());
        position.setCurrencyName(product.getCurrencyName());
        position.setCurrencyCharacter(product.getCurrencyCharacter());
        position.setExchangeRate(product.getExchangeRate());
        Customer customer = customerService.getById(dto.getCustomerId());
        //customer.setStockAsset(customer.getStockAsset().add(dto.getType().equals(1) ? dto.getMoney() : (BigDecimal.ZERO.subtract(dto.getMoney()))));
        //customerService.saveOrUpdate(customer);
        this.saveOrUpdate(position);

        PositionCount count = positionCountService.getOne(new LambdaQueryWrapper<PositionCount>()
                .eq(PositionCount::getProductId, position.getProductId())
                .eq(PositionCount::getCustomerId, position.getCustomerId()));
        if (count == null) {
            count = new PositionCount();
            count.setMoney(BigDecimal.ZERO);
            count.setShare(BigDecimal.ZERO);
            count.setSumMoney(BigDecimal.ZERO);
            count.setTransShare(false);
            count.setCustomerId(dto.getCustomerId());
            count.setProductId(dto.getProductId());
            count.setProductType(product.getType());
            count.setLastNetValue(product.getLastNetValue());
            count.setCurrentBook(true);
            count.setCurrencyId(product.getCurrencyId());
            count.setCurrencyName(product.getCurrencyName());
            count.setCurrencyCharacter(product.getCurrencyCharacter());
            count.setExchangeRate(product.getExchangeRate());
        }
        if (position.getOrderStatus() == 2) {
            if (position.getType() == 1) {
                if (position.getCategory() == 2 || position.getCategory() == 6) {
                    count.setCurrentBook(false);
                    if (position.getTransShare()) {
                        count.setShare(count.getShare().add(position.getShare()));
                    } else {
                        count.setMoney(count.getMoney().add(position.getMoney()));
                        count.setSumMoney(count.getSumMoney().add(position.getMoney()));

                    }
                }
            } else {
                if (position.getTransShare()) {
                    count.setShare(count.getShare().subtract(position.getShare()));
                } else {
                    count.setMoney(count.getMoney().subtract(position.getMoney()));
                }
            }
        }
        positionCountService.saveOrUpdate(count);
    }


    /**
     * 获取持仓统计
     */
    @Override
    public List<PositionCountDTO> getPositionCount(GetPositionCountParam param) {
        List<PositionCount> list = positionCountService.list(new LambdaQueryWrapper<PositionCount>()
                .eq(PositionCount::getIsDeleted, 0)
                .eq(PositionCount::getCustomerId, param.getCustomerId()));
        if (list.isEmpty()) {
            return new ArrayList<>();
        }
        Map<Integer, List<PositionCount>> positionList = list.stream().collect(Collectors.groupingBy(PositionCount::getProductType));
        List<PositionCountDTO> result = new ArrayList<>();
        for (Map.Entry<Integer, List<PositionCount>> entry : positionList.entrySet()) {
            PositionCountDTO dto = new PositionCountDTO();
            dto.setType(entry.getKey());
            List<PositionCount> moneyCount = entry.getValue().stream().filter(t -> !t.getTransShare()).collect(Collectors.toList());
            BigDecimal money = moneyCount.isEmpty() ? BigDecimal.ZERO : moneyCount.stream().map(t -> t.getMoney().multiply(BigDecimal.valueOf(10000)).multiply(Func.isNull(t.getExchangeRate()) ? BigDecimal.ONE : t.getExchangeRate())).reduce(BigDecimal::add).get();
            List<PositionCount> shareCount = entry.getValue().stream().filter(t -> t.getTransShare()).collect(Collectors.toList());
            BigDecimal share = shareCount.isEmpty() ? BigDecimal.ZERO : shareCount.stream().map(t -> t.getShare().multiply(t.getLastNetValue().setScale(18, BigDecimal.ROUND_HALF_UP)).multiply(Func.isNull(t.getExchangeRate()) ? BigDecimal.ONE : t.getExchangeRate().setScale(18, BigDecimal.ROUND_HALF_UP))).reduce(BigDecimal::add).get();
            dto.setMoney(money.add(share));
            BigDecimal sumMoney = entry.getValue().isEmpty() ? BigDecimal.ZERO : entry.getValue().stream().map(t -> t.getSumMoney().multiply(BigDecimal.valueOf(10000)).multiply(Func.isNull(t.getExchangeRate()) ? BigDecimal.ONE : t.getExchangeRate())).reduce(BigDecimal::add).get();
            dto.setSumMoney(sumMoney);
            result.add(dto);
        }
        return result;
    }


    /**
     * 获取持仓列表
     */
    @Override
    public List<CurrentPositionDTO> getCurrentPositionList(GetCurrentPositionListParam param) {
        return baseMapper.getCurrentPositionList(param);
    }


    /**
     * 获取持仓列表
     */
    @Override
    public List<HistoryPositionDTO> getHistoryPositionList(GetCurrentPositionListParam param) {
        return baseMapper.getHistoryPositionList(param);
    }

    /**
     * 获取产品持仓列表
     */
    @Override
    public List<PositionDTO> getProductPositionList(GetProductPositionListParam param) {
        List<Position> positions = this.list(new LambdaQueryWrapper<Position>()
                .eq(Position::getIsDeleted, 0)
                .eq(Position::getProductId, param.getProductId())
                .eq(Position::getCustomerId, param.getCustomerId())
                .orderByAsc(Position::getCreateTime));
        List<PositionDTO> result = new ArrayList<>();
        for (Position position : positions) {
            PositionDTO dto = new PositionDTO();
            BeanUtils.copyProperties(position, dto);
            //dto.setMoney(dto.getMoney().multiply(dto.getExchangeRate()));
            result.add(dto);
        }
        return result;
    }

    /**
     * 获取客户交易详情
     */
    @Override
    public IPage<PositionDTO> getCustomerPositionPage(GetCustomerPositionParam param) {
        IPage<Position> page = Condition.getPage(param);
        LambdaQueryWrapper<Position> queryWrapper = new LambdaQueryWrapper<Position>()
                .eq(Position::getIsDeleted, 0)
                .eq(Position::getCustomerId, param.getCustomerId())
                .orderByDesc(Position::getCreateTime);
        IPage<Position> res = this.page(page, queryWrapper);
        List<PositionDTO> result = new ArrayList<>();
        for (Position position : res.getRecords()) {
            PositionDTO dto = new PositionDTO();
            BeanUtils.copyProperties(position, dto);
            result.add(dto);
        }
        IPage<PositionDTO> resPage = new Page<>();
        resPage.setRecords(result);
        resPage.setTotal(res.getTotal());
        resPage.setCurrent(res.getCurrent());
        resPage.setPages(res.getPages());
        resPage.setSize(res.getSize());
        return resPage;
    }


    /**
     * 同步所有持仓
     */
    @Override
    public void syncAllPosition() {
        List<PositionDTO> list = tradeClient.syncAllPosition();
        for (PositionDTO dto : list) {
            Customer customer = customerService.getOne(new LambdaQueryWrapper<Customer>()
                    .eq(Customer::getRelationId, dto.getCustomerId())
                    .eq(Customer::getIsDeleted, 0));
            dto.setCustomerId(customer.getId());
            Product product = productService.getOne(new LambdaQueryWrapper<Product>()
                    .eq(Product::getRelationId, dto.getProductId())
                    .eq(Product::getIsDeleted, 0));
            dto.setProductId(product.getId());
            dto.setProductType(product.getType());
            dto.setProductName(product.getName());
            this.savePosition(dto);
        }
    }

}
