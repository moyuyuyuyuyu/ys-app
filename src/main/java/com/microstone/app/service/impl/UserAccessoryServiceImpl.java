package com.microstone.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.microstone.app.entity.UserAccessory;
import com.microstone.app.entity.UserEducation;
import com.microstone.app.mapper.UserAccessoryMapper;
import com.microstone.app.param.GetUserInfoParam;
import com.microstone.app.service.IUserAccessoryService;
import org.microstone.core.mp.base.BaseServiceImpl;
import org.microstone.core.secure.utils.AuthUtil;
import org.microstone.core.tool.utils.Func;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 服务实现类
 *
 * @author Chalsee
 * @since 2021-06-07
 */
@Service
public class UserAccessoryServiceImpl extends BaseServiceImpl<UserAccessoryMapper, UserAccessory> implements IUserAccessoryService {

    /**
     * 保存
     */
    @Override
    public void saveUserAccessory(UserAccessory entity) {
        if (entity.getId() == null) {
            entity.setUserId(AuthUtil.getAppUserId());
        }
        if (!Func.isNull(entity.getIsDeleted()) && entity.getIsDeleted() == 1) {
            this.removeById(entity.getId());
        } else {
            this.saveOrUpdate(entity);
        }
    }


    /**
     * 获取列表
     */
    @Override
    public List<UserAccessory> getUserAccessoryList(GetUserInfoParam param) {
        Long userId;
        if(param.getAppUserId() != null){
            userId = param.getAppUserId();
        }else{
            userId = AuthUtil.getAppUserId();
        }
        return this.list(new LambdaQueryWrapper<UserAccessory>()
                .eq(UserAccessory::getIsDeleted, 0)
                .eq(UserAccessory::getUserId, userId)
                .orderByAsc(UserAccessory::getCreateTime));
    }
}
