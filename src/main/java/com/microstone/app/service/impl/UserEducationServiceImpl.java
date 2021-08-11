package com.microstone.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.microstone.app.entity.UserCertificate;
import com.microstone.app.entity.UserEducation;
import com.microstone.app.mapper.UserEducationMapper;
import com.microstone.app.param.GetUserInfoParam;
import com.microstone.app.service.IUserEducationService;
import org.microstone.core.mp.base.BaseServiceImpl;
import org.microstone.core.secure.utils.AuthUtil;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 服务实现类
 *
 * @author Chalsee
 * @since 2021-06-07
 */
@Service
public class UserEducationServiceImpl extends BaseServiceImpl<UserEducationMapper, UserEducation> implements IUserEducationService {

    /**
     * 保存
     */
    @Override
    public void saveUserEducation(UserEducation entity) {
        if (entity.getId() == null) {
            entity.setUserId(AuthUtil.getAppUserId());
        }
        this.saveOrUpdate(entity);
    }


    /**
     * 获取列表
     */
    @Override
    public List<UserEducation> getUserEducationList(GetUserInfoParam param) {
        Long userId;
        if(param.getAppUserId() != null){
            userId = param.getAppUserId();
        }else{
            userId = AuthUtil.getAppUserId();
        }
        return this.list(new LambdaQueryWrapper<UserEducation>()
                .eq(UserEducation::getIsDeleted, 0)
                .eq(UserEducation::getUserId, userId)
                .orderByAsc(UserEducation::getCreateTime));
    }
}
