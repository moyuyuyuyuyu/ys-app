package com.microstone.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.microstone.app.entity.UserAccessory;
import com.microstone.app.param.GetUserInfoParam;
import org.microstone.core.mp.base.BaseService;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 *  服务类
 *
 * @author Chalsee
 * @since 2021-06-07
 */
public interface IUserAccessoryService extends BaseService<UserAccessory> {

    /**
     * 保存
     */
    void saveUserAccessory(UserAccessory entity);


    /**
     * 获取列表
     */
    List<UserAccessory> getUserAccessoryList(GetUserInfoParam param);
}
