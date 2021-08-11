package com.microstone.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.microstone.app.entity.UserEducation;
import com.microstone.app.entity.UserLabel;
import com.microstone.app.mapper.UserEducationMapper;
import com.microstone.app.mapper.UserLabelMapper;
import com.microstone.app.param.GetUserInfoParam;
import com.microstone.app.service.IUserEducationService;
import com.microstone.app.service.IUserLabelService;
import io.swagger.annotations.ApiOperation;
import org.microstone.core.mp.base.BaseServiceImpl;
import org.microstone.core.secure.utils.AuthUtil;
import org.microstone.core.tool.api.R;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class UserLabelServiceImpl extends BaseServiceImpl<UserLabelMapper, UserLabel> implements IUserLabelService {


    /**
     * 保存
     */
    @Override
    public void saveUserLabel(UserLabel entity) {
        if (entity.getId() == null) {
            entity.setUserId(AuthUtil.getAppUserId());
        }
        this.saveOrUpdate(entity);
    }


    /**
     * 获取列表
     */
    @Override
    public List<UserLabel> getUserLabelList(GetUserInfoParam param) {
        Long userId;
        if(param.getAppUserId() != null){
            userId = param.getAppUserId();
        }else{
            userId = AuthUtil.getAppUserId();
        }
        return this.list(new LambdaQueryWrapper<UserLabel>()
                .eq(UserLabel::getIsDeleted, 0)
                .eq(UserLabel::getUserId, userId)
                .orderByAsc(UserLabel::getCreateTime));
    }


    @Override
    public void clickLabel(UserLabel label){
        UserLabel entity = this.getById(label.getId());
        entity.setPraiseCount(entity.getPraiseCount() + 1);
        this.saveOrUpdate(entity);
    }

}
