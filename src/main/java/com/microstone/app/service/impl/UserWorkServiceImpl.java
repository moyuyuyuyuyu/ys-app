package com.microstone.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.microstone.app.entity.UserEducation;
import com.microstone.app.entity.UserWork;
import com.microstone.app.mapper.UserWorkMapper;
import com.microstone.app.param.GetUserInfoParam;
import com.microstone.app.service.IUserWorkService;
import org.microstone.core.mp.base.BaseServiceImpl;
import org.microstone.core.secure.utils.AuthUtil;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 *  服务实现类
 *
 * @author Chalsee
 * @since 2021-06-07
 */
@Service
public class UserWorkServiceImpl extends BaseServiceImpl<UserWorkMapper, UserWork> implements IUserWorkService {

    /**
     * 保存
     */
    @Override
    public void saveUserWork(UserWork entity) {
        if (entity.getId() == null) {
            entity.setUserId(AuthUtil.getAppUserId());
        }
        this.saveOrUpdate(entity);
    }


    /**
     * 获取列表
     */
    @Override
    public List<UserWork> getUserWorkList(GetUserInfoParam param) {
        Long userId;
        if(param.getAppUserId() != null){
            userId = param.getAppUserId();
        }else{
            userId = AuthUtil.getAppUserId();
        }
        return this.list(new LambdaQueryWrapper<UserWork>()
                .eq(UserWork::getIsDeleted, 0)
                .eq(UserWork::getUserId, userId)
                .orderByAsc(UserWork::getCreateTime));
    }

}
