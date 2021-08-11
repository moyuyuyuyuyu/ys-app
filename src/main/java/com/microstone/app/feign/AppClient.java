package com.microstone.app.feign;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.microstone.app.dto.CrmProductDTO;
import com.microstone.app.dto.CustomerDTO;
import com.microstone.app.dto.PositionDTO;
import com.microstone.app.dto.ProductDTO;
import com.microstone.app.dto.customer.CbsCustomerDTO;
import com.microstone.app.entity.*;
import com.microstone.app.feign.param.AppUserQuery;
import com.microstone.app.feign.param.AppUserRegisterParam;
import com.microstone.app.feign.param.WechatUserInfoRqst;
import com.microstone.app.param.UpdatePositionCountShareParam;
import com.microstone.app.param.UpdateProductNetValueParam;
import com.microstone.app.service.*;
import lombok.AllArgsConstructor;
import org.microstone.core.log.exception.ServiceException;
import org.microstone.core.tool.api.R;
import org.microstone.core.tool.constant.MsConstant;
import org.microstone.core.tool.utils.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class AppClient implements IAppClient {


    private IProductService productService;

    private ICustomerService customerService;

    private IUserService userService;

    private IPositionService positionService;

    private IWechatUserService wechatUserService;

    private IPositionCountService positionCountService;


    /**
     * 获取App用户信息
     */
    @Override
    @PostMapping(USER_INFO)
    public R<AppUserInfo> userInfo(@RequestParam("account") String account) {
        return R.data(userService.userInfo(account));
    }

    /**
     * 获取App用户信息
     */
    @Override
    @PostMapping(USER_INFO_AUTO_CREATE)
    public R<AppUserInfo> userInfoAutoCreate(@RequestParam("account") String account) {
        return R.data(userService.userInfoAutoCreate(account));
    }


    /**
     * 获取App用户信息
     */
    @Override
    @PostMapping(USER_INFO_BY_ID)
    public R<AppUserInfo> userInfoById(@RequestParam("id") Long id) {
        return R.data(userService.userInfoById(id));
    }


    /**
     * @param param
     * @return
     */
    @Override
    @PostMapping(USER_REGISTER)
    public R<AppUserInfo> userRegister(@RequestBody AppUserRegisterParam param) {
        //用户唯一性校验
        if (userService.count(Wrappers.<User>query().lambda()
                .eq(User::getMobilePhone, param.getUserName())
                //.eq(UserApp::getTenantId,tanantId)
                .eq(User::getIsDeleted, Boolean.FALSE)) > 0)
            throw new ServiceException("该用户已被注册");

        //用户注册
        User user = new User();
        user.setMobilePhone(param.getUserName());

        if (Func.isNotEmpty(user.getPassword())) {
            user.setPassword(DigestUtil.encrypt(user.getPassword()));
        }

        userService.save(user);
        AppUserInfo userInfo = BeanUtil.copy(user, AppUserInfo.class);
        return R.data(userInfo);
    }


    /**
     * 保存Crm产品
     */
    @Override
    @PostMapping(SAVE_APP_PRODUCT)
    public void saveAppProduct(@RequestBody CrmProductDTO productDTO) {
        Product product = productService.getOne(new LambdaQueryWrapper<Product>().eq(Product::getIsDeleted, 0)
                .eq(Product::getRelationId, productDTO.getProductId()));
        if (product != null) {
            return;
        }
        ProductDTO dto = new ProductDTO();
        dto.setName(productDTO.getParent() ? productDTO.getName() : productDTO.getChildName());
        dto.setCode(productDTO.getCode());
        dto.setType(productDTO.getType());
        dto.setProductScale(productDTO.getProductScale());
        dto.setStartScale(productDTO.getStartScale());
        dto.setInvestmentTermSum(productDTO.getInvestmentTermSum());
        dto.setMinIncomeRate(productDTO.getMinIncomeRate());
        dto.setMaxIncomeRate(productDTO.getMaxIncomeRate());
        dto.setSource(2);
        dto.setRelationId(productDTO.getProductId());
        dto.setLastNetValue(productDTO.getLastNetValue());
        dto.setProductStatus(1);
        dto.setCurrencyId(productDTO.getCurrency());
        dto.setCurrencyName(productDTO.getCurrencyName());
        dto.setCurrencyCharacter(productDTO.getCurrencyCharacter());
        dto.setExchangeRate(productDTO.getExchangeRate());
        productService.saveProduct(dto);
    }


    @Override
    @PostMapping(SAVE_APP_CUSTOMER)
    public R saveAppCustomer(@RequestBody CbsCustomerDTO customerDTO) {
        CustomerDTO dto = new CustomerDTO();
        CustomerDTO dt = customerService.getCustomerByRelationId(customerDTO.getId());
        if (dt == null) {
            dto.setRelationId(customerDTO.getId());
            dto.setCustomerStatus(customerDTO.getStatus());
            dto.setName(customerDTO.getName());
            dto.setIncomeAsset(BigDecimal.ZERO);
            dto.setStockAsset(BigDecimal.ZERO);
            dto.setSource(2);
            dto.setWechatId(customerDTO.getWechatId());
            dto.setIsDeleted(0);
            customerService.saveCustomer(dto);
        } else {
            dt.setName(customerDTO.getName());
            customerService.saveCustomer(dto);
        }
        return R.status(true);
    }

    /**
     * 用户模糊匹配查询条件
     *
     * @return
     */
    @Override
    @PostMapping(USER_IDS_BY_NAME)
    public R<List<Long>> userIdsByName(@RequestBody AppUserQuery query) {

        LambdaQueryWrapper<User> queryWrappers = new LambdaQueryWrapper();
        queryWrappers.eq(User::getIsDeleted, Boolean.FALSE);

        if (Func.notNull(query.getName())) {
            queryWrappers.like(User::getName, query.getName());
        }
        if (Func.notNull(query.getMobile())) {
            queryWrappers.like(User::getMobilePhone, query.getMobile());
        }
        List<Long> userIds = userService.list(queryWrappers).stream().map(t -> t.getId()).collect(Collectors.toList());
        if (userIds.isEmpty() || userIds.size() == 0) {
            return R.data(new ArrayList<>());
        }
        return R.data(userIds);
    }


    @Override
    @PostMapping(SAVE_POSITION)
    public void savePosition(@RequestBody List<PositionDTO> dtos) {
        for (PositionDTO dto: dtos){
            Position position = positionService.getOne(new LambdaQueryWrapper<Position>()
                    .eq(Position::getIsDeleted, 0)
                    .eq(Position::getRelationId, dto.getRelationId()));
            if (position == null || (position != null && (dto.getCategory() == 4 || dto.getCategory() == 5 || dto.getCategory() == 6))) {
                List<Customer> customers = customerService.list(new LambdaQueryWrapper<Customer>()
                        .eq(Customer::getRelationId, dto.getCustomerId())
                        .eq(Customer::getIsDeleted, 0));
                if(customers.size()>1){
                    customers = customers.stream().filter(t -> t.getRelationId().toString().equals(dto.getCustomerId().toString())).collect(Collectors.toList());
                }
                Customer customer = customers.get(0);
                dto.setCustomerId(customer.getId());
                Product product = productService.getOne(new LambdaQueryWrapper<Product>()
                        .eq(Product::getRelationId, dto.getProductId())
                        .eq(Product::getIsDeleted, 0));
                dto.setProductId(product.getId());
                dto.setProductType(product.getType());
                dto.setProductName(product.getName());
                positionService.savePosition(dto);
            } else {
                PositionDTO dt = new PositionDTO();
                BeanUtil.copyProperties(position, dt);
                dt.setTransShare(dto.getTransShare());
                dt.setMoney(dto.getMoney());
                dt.setShare(dto.getShare());
                dt.setOrderStatus(dto.getOrderStatus());
                positionService.savePosition(dt);
            }
        }
    }


    /**
     * 分享小程序获取用户信息
     *
     * @param param
     * @return
     */
    @Override
    @PostMapping(WECHAT_USER_INFO_LOGIN)
    public R<WechatUser> userInfoByQrOpenId(@RequestBody WechatUserInfoRqst param) {

        List<WechatUser> users = wechatUserService
                .list(Wrappers.<WechatUser>query().lambda()
                        //.eq(WechatUser::getTenantId, param.getTenantId())
                        .eq(WechatUser::getPhone, param.getPhoneNumber())
                        .eq(WechatUser::getIsDeleted, MsConstant.DB_NOT_DELETED));
        WechatUser wechatUser = new WechatUser();
        //openId 对应的用户不存在  新增
        if (Func.isNull(users) || users.size() == 0) {


            wechatUser.setOpenId(param.getOpenId());
            wechatUser.setPhone(param.getPhoneNumber());
            wechatUser.setName(param.getNickName());
            wechatUser.setHeadImage(param.getAvatarUrl());
            wechatUserService.save(wechatUser);
            return R.data(wechatUser);
        }
        //更新微信账号信息
        wechatUser = users.get(0);
        wechatUser.setHeadImage(param.getAvatarUrl());
        wechatUser.setName(param.getNickName());
        wechatUserService.saveOrUpdate(wechatUser);
        return R.data(users.get(0));
    }

    /**
     * 分享小程序获取用户信息
     *
     * @param param
     * @return
     */
    @Override
    @PostMapping(WECHAT_USER_INFO)
    public R<WechatUser> wechatUserInfo(@RequestBody WechatUserInfoRqst param) {

        List<WechatUser> users = wechatUserService
                .list(Wrappers.<WechatUser>query().lambda()
                        //.eq(WechatUser::getTenantId, param.getTenantId())
                        .eq(WechatUser::getOpenId, param.getOpenId())
                        .eq(WechatUser::getIsDeleted, MsConstant.DB_NOT_DELETED));
        if (Func.notNull(users) && users.size() > 0) {
            return R.data(users.get(0));
        }
        return R.fail("获取用户信息失败！");
    }


    @Override
    @PostMapping(UPDATE_PRODUCT_NET_VALUE)
    public R updateProductNetValue(@RequestBody List<UpdateProductNetValueParam> param) {
        for (UpdateProductNetValueParam p : param) {
            Product product = productService.getOne(new LambdaQueryWrapper<Product>().eq(Product::getRelationId, p.getProductId()));
            if (product == null) {
                continue;
            }
            product.setLastNetValue(p.getNetValue());
            productService.saveOrUpdate(product);
            List<PositionCount> countList = positionCountService.list(new LambdaQueryWrapper<PositionCount>()
                    .eq(PositionCount::getIsDeleted, 0)
                    .eq(PositionCount::getProductId, product.getId()));
            for (PositionCount count : countList) {
                count.setLastNetValue(p.getNetValue());
                positionCountService.saveOrUpdate(count);
            }
        }
        return R.status(true);
    }


    @Override
    @PostMapping(UPDATE_POSITION_SHARE)
    public R updatePositionCountShare(@RequestBody List<UpdatePositionCountShareParam> param) {
        for (UpdatePositionCountShareParam p : param) {
            Customer customer = customerService.getOne(new LambdaQueryWrapper<Customer>()
                    .eq(Customer::getIsDeleted, 0)
                    .eq(Customer::getRelationId, p.getCustomerId()));
            Product product = productService.getOne(new LambdaQueryWrapper<Product>()
                    .eq(Product::getIsDeleted, 0)
                    .eq(Product::getRelationId, p.getProductId()));
            PositionCount count = positionCountService.getOne(new LambdaQueryWrapper<PositionCount>()
                    .eq(PositionCount::getCustomerId, customer.getId())
                    .eq(PositionCount::getProductId, product.getId()));
            count.setShare(p.getShare());
            count.setTransShare(true);
            positionCountService.saveOrUpdate(count);
        }
        return R.status(true);
    }


    /**
     * 移除关联
     */
    @PostMapping(REMOVE_CUSTOMER_RELATION)
    public R removeCustomerRelation(@RequestParam("customerId") List<Long> customerId) {
        List<Customer> customers = customerService.list(new LambdaQueryWrapper<Customer>()
                .eq(Customer::getIsDeleted, 0)
                .in(Customer::getRelationId, customerId));
        if (customers.isEmpty()) {
            return R.status(true);
        }
        for (Customer customer : customers) {
            customer.setOldRelationId(customer.getRelationId());
            customer.setRelationId(null);
            customerService.saveOrUpdate(customer);
        }
        return R.status(true);
    }


    /**
     * 设置关联
     */
    @Override
    @PostMapping(SET_CUSTOMER_RELATION)
    public R setCustomerRelation(@RequestParam("employeeId") Long employeeId, @RequestParam("customerId") List<Long> customerId) {
        User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getIsDeleted, 0).eq(User::getUserId, employeeId));
        List<Customer> customers = customerService.list(new LambdaQueryWrapper<Customer>()
                .eq(Customer::getIsDeleted, 0)
                .in(Customer::getOldRelationId, customerId));
        List<Customer> customerList = new ArrayList<>();
        for (Customer customer : customers) {
            Customer newCustomer = new Customer();
            BeanUtil.copyProperties(customer, newCustomer);
            newCustomer.setId(null);
            newCustomer.setTenantId(null);
            newCustomer.setUserId(user.getId());
            customerService.save(newCustomer);
            List<PositionCount> counts = positionCountService.list(new LambdaQueryWrapper<PositionCount>().eq(PositionCount::getCustomerId, customer.getId()));
            if (!counts.isEmpty()) {
                for (PositionCount count : counts) {
                    PositionCount newCount = new PositionCount();
                    BeanUtil.copyProperties(count, newCount);
                    newCount.setId(null);
                    newCount.setTenantId(null);
                    newCount.setCustomerId(newCustomer.getId());
                    positionCountService.save(newCount);
                }
            }
            List<Position> positions = positionService.list(new LambdaQueryWrapper<Position>().eq(Position::getCustomerId, customer.getId()));
            if (!positions.isEmpty()) {
                for (Position position : positions) {
                    Position newPosition = new Position();
                    BeanUtil.copyProperties(position, newPosition);
                    newPosition.setId(null);
                    newPosition.setTenantId(null);
                    newPosition.setCustomerId(newCustomer.getId());
                    positionService.save(newPosition);
                }
            }
        }
        return R.status(true);
    }


    @Override
    @PostMapping(GET_APP_PRODUCT_STATUS)
    public R<Integer> getAppProductStatus(@RequestParam("productId") Long productId){
        Product product = productService.getOne(new LambdaQueryWrapper<Product>().eq(Product::getRelationId, productId));
        if(product == null){
            return R.data(0);
        }
        return R.data(product.getProductStatus());
    }




    @Override
    @PostMapping(REMOVE_APP_CUSTOMER)
    public R removeAppCustomer(@RequestParam("customerId") List<Long> customerId){
        List<Customer> customers = customerService.list(new LambdaQueryWrapper<Customer>()
                .in(Customer::getRelationId, customerId)
                .eq(Customer::getIsDeleted, 0));
        for (Customer customer : customers){
            customerService.removeById(customer.getId());
        }
        return R.data(true);
    }


    @Override
    @PostMapping(UPDATE_APP_CUSTOMER_TO_TRADE)
    public R updateAppCustomerToTrade(@RequestParam("customerId") Long customerId){
        List<Customer> customers = customerService.list(new LambdaQueryWrapper<Customer>()
                .eq(Customer::getRelationId, customerId)
                .eq(Customer::getIsDeleted, 0));
        if(customers.size()>1){
            customers = customers.stream().filter(t -> t.getRelationId().toString().equals(customerId.toString())).collect(Collectors.toList());
        }
        Customer customer = customers.get(0);
        customer.setCustomerStatus(3);
        customerService.saveOrUpdate(customer);
        return R.data(true);
    }
}
