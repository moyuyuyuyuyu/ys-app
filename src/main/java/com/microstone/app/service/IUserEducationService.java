package com.microstone.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.microstone.app.entity.UserEducation;
import com.microstone.app.param.GetUserInfoParam;
import org.microstone.core.mp.base.BaseService;

import java.util.List;

/**
 *  服务类
 *
 * @author Chalsee
 * @since 2021-06-07
 */
public interface IUserEducationService extends BaseService<UserEducation> {

    /**
     * 保存
     */
    void saveUserEducation(UserEducation entity);

    /**
     * 获取列表
     */
    List<UserEducation> getUserEducationList(GetUserInfoParam param);
}
