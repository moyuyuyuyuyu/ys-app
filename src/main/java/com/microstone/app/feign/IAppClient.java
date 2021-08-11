package com.microstone.app.feign;

import com.microstone.app.dto.CrmProductDTO;
import com.microstone.app.dto.CustomerDTO;
import com.microstone.app.dto.customer.CbsCustomerDTO;
import com.microstone.app.entity.AppUserInfo;
import com.microstone.app.entity.WechatUser;
import com.microstone.app.feign.param.AppUserQuery;
import com.microstone.app.feign.param.AppUserRegisterParam;
import com.microstone.app.feign.param.WechatUserInfoRqst;
import com.microstone.app.param.UpdatePositionCountShareParam;
import com.microstone.app.param.UpdateProductNetValueParam;
import org.microstone.core.tool.api.R;
import com.microstone.app.dto.PositionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(
        value = "ms-app"
)
public interface IAppClient {


    String API_PREFIX = "/client";

    String SAVE_APP_PRODUCT = API_PREFIX + "/save-app-product";
    String USER_INFO = API_PREFIX + "/user-info";
    String USER_INFO_AUTO_CREATE = API_PREFIX + "/user-info-auto-create";
    String USER_INFO_BY_ID = API_PREFIX + "/user-info-by-id";
    String USER_REGISTER = API_PREFIX + "/user-register";
    String USER_IDS_BY_NAME = API_PREFIX + "/user-ids-by-query";
    String TEST = API_PREFIX + "/test";
    String SAVE_APP_CUSTOMER = API_PREFIX + "/save-app-customer";
    String SAVE_POSITION = API_PREFIX + "/save-position";
    String WECHAT_USER_INFO_LOGIN = API_PREFIX + "/wechat-user-info-login";
    String WECHAT_USER_INFO = API_PREFIX + "/wechat-user-info";
    String UPDATE_PRODUCT_NET_VALUE = API_PREFIX + "/update-product-net-value";
    String UPDATE_POSITION_SHARE = API_PREFIX + "/update-position-share";
    String REMOVE_CUSTOMER_RELATION = API_PREFIX + "/remove-customer-relation";
    String SET_CUSTOMER_RELATION = API_PREFIX + "/set-customer-relation";
    String GET_APP_PRODUCT_STATUS = API_PREFIX + "/get-app-product-status";
    String REMOVE_APP_CUSTOMER = API_PREFIX + "/remove-app-customer";
    String UPDATE_APP_CUSTOMER_TO_TRADE = API_PREFIX + "/update-app-customer-to-trade";

    /**
     * 获取用户信息
     * @param tenantId
     * @param account
     * @return
     */
    @PostMapping(USER_INFO)
    R<AppUserInfo> userInfo(@RequestParam("account") String account);

    /**
     * 获取用户信息
     * @param tenantId
     * @param account
     * @return
     */
    @PostMapping(USER_INFO_AUTO_CREATE)
    R<AppUserInfo> userInfoAutoCreate(@RequestParam("account") String account);


    /**
     * 获取用户信息BY ID
     * @param tenantId
     * @param account
     * @return
     */

    @PostMapping(USER_INFO_BY_ID)
    R<AppUserInfo> userInfoById(@RequestParam("id") Long id);

    /**
     * 用户注册
     * @param param
     * @return
     */
    @PostMapping(USER_REGISTER)
    R<AppUserInfo> userRegister(@RequestBody AppUserRegisterParam param);




    @PostMapping(SAVE_APP_PRODUCT)
    void saveAppProduct(@RequestBody CrmProductDTO productDTO);


    @PostMapping(SAVE_APP_CUSTOMER)
    R saveAppCustomer(@RequestBody CbsCustomerDTO dto);


    /**
     * 根据用户名匹配用户id
     * @param name
     */
    @PostMapping(USER_IDS_BY_NAME)
    R<List<Long>> userIdsByName(@RequestBody AppUserQuery query);




    @PostMapping(SAVE_POSITION)
    void savePosition(@RequestBody List<PositionDTO> dto);



    @PostMapping(WECHAT_USER_INFO_LOGIN)
    R<WechatUser> userInfoByQrOpenId(@RequestBody WechatUserInfoRqst param);


    @PostMapping(WECHAT_USER_INFO)
    R<WechatUser> wechatUserInfo(@RequestBody WechatUserInfoRqst param);


    @PostMapping(UPDATE_PRODUCT_NET_VALUE)
    R updateProductNetValue(@RequestBody List<UpdateProductNetValueParam> param);

    @PostMapping(UPDATE_POSITION_SHARE)
    R updatePositionCountShare(@RequestBody List<UpdatePositionCountShareParam> param);

    /**
     * 移除关联
     * */
    @PostMapping(REMOVE_CUSTOMER_RELATION)
    R removeCustomerRelation(@RequestParam("customerId") List<Long> customerId);

    /**
     * 设置关联
     * */
    @PostMapping(SET_CUSTOMER_RELATION)
    R setCustomerRelation(@RequestParam("employeeId") Long employeeId, @RequestParam("customerId") List<Long> customerId);


    @PostMapping(GET_APP_PRODUCT_STATUS)
    R<Integer> getAppProductStatus(@RequestParam("productId") Long productId);


    @PostMapping(REMOVE_APP_CUSTOMER)
    R removeAppCustomer(@RequestParam("customerId") List<Long> customerId);

    @PostMapping(UPDATE_APP_CUSTOMER_TO_TRADE)
    R updateAppCustomerToTrade(@RequestParam("customerId") Long customerId);
}

