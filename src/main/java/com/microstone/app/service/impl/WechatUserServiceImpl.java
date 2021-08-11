package com.microstone.app.service.impl;

import com.microstone.app.entity.WechatUser;
import com.microstone.app.mapper.WechatUserMapper;
import com.microstone.app.service.IWechatUserService;
import org.microstone.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class WechatUserServiceImpl extends BaseServiceImpl<WechatUserMapper, WechatUser> implements IWechatUserService {

    /**
     * 保存微信用户
     */
    @Override
    public void saveWeChatUser(WechatUser entity) {
        this.saveOrUpdate(entity);
    }
}
