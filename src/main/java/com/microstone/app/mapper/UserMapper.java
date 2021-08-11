package com.microstone.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.microstone.app.entity.User;
import com.microstone.app.entity.AppUserInfo;

/**
 *  Mapper 接口
 *
 * @author Chalsee
 * @since 2021-06-07
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 获取用户
     *
     * @param tenantId
     * @param account
     * @return
     */
    AppUserInfo getUser(String account);


    /**
     * 获取用户信息ById
     *
     * @param
     * @return
     */
    AppUserInfo getUserById(Long appUserId);

}
