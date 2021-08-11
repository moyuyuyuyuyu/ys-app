package com.microstone.app.service;

import com.microstone.app.entity.UserLabel;
import com.microstone.app.param.GetUserInfoParam;
import org.microstone.core.mp.base.BaseService;

import java.util.List;

public interface IUserLabelService extends BaseService<UserLabel> {
    void saveUserLabel(UserLabel entity);
    List<UserLabel> getUserLabelList(GetUserInfoParam param);

    void clickLabel(UserLabel label);
}
