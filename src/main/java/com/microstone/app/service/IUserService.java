package com.microstone.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.microstone.app.dto.GetUserListDTO;
import com.microstone.app.dto.UserDTO;
import com.microstone.app.entity.User;
import com.microstone.app.entity.AppUserInfo;
import com.microstone.app.param.GetUserInfoParam;
import com.microstone.app.param.GetUserListParam;
import org.microstone.core.mp.base.BaseService;

/**
 *  服务类
 *
 * @author Chalsee
 * @since 2021-06-07
 */
public interface IUserService extends BaseService<User> {


    /**
     * 获取用户登录信息
     * @param 机构编号
     * @param 账号
     * @return
     */
    AppUserInfo userInfo(String account);

    /**
     * 获取用户信息 - 不存在则自动创建
     * @param 机构编号
     * @param 账号
     * @return
     */
    AppUserInfo userInfoAutoCreate(String account);


    /**
     * 保存用户信息
     * */
    UserDTO saveUser(UserDTO dto);


    /**
     * 获取用户信息
     * */
    UserDTO getUser(GetUserInfoParam param);

    /**
     * 获取用户信息 by id
     * @param appUserId
     * @return
     */
    AppUserInfo userInfoById(Long appUserId);

    /**
     * 获取用户分页
     * */
    IPage<GetUserListDTO> getUserList(GetUserListParam param);
}
