package com.microstone.app.cache;


import com.microstone.system.entity.User;
import com.microstone.system.feign.IUserClient;
import org.microstone.core.cache.utils.CacheUtil;
import org.microstone.core.tool.api.R;
import org.microstone.core.tool.utils.SpringUtil;
import org.microstone.core.tool.utils.StringPool;

import java.util.List;

import static org.microstone.core.cache.constant.CacheConstant.APP_CACHE;
import static org.microstone.core.cache.constant.CacheConstant.USER_CACHE;

/**
 * 权限缓存
 *
 * @author Ms
 */
public class CustomerPermissionCache {

    private static final String USER_PERMISSION_CUSTOMER = "user:dataScope:userIds";
    private static final Boolean TENANT_MODE = Boolean.FALSE;

    private static IUserClient userClient;

    private static IUserClient getUserClient() {
        if (userClient == null) {
            userClient = SpringUtil.getBean(IUserClient.class);
        }
        return userClient;
    }

    /**
     * 获取用户
     *
     * @param appUserId appUserId
     * @return
     */
    public static List<Long> getCustomerPermission(Long appUserId) {
        return CacheUtil.get(APP_CACHE, USER_PERMISSION_CUSTOMER, appUserId, () -> {
            R<List<Long>> result = getUserClient().appCustomerPermission(appUserId);
            return result.getData();
        },TENANT_MODE);
    }
}
