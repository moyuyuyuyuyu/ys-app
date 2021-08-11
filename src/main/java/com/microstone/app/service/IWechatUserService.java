package com.microstone.app.service;

import com.microstone.app.entity.WechatUser;
import org.microstone.core.mp.base.BaseService;

public interface IWechatUserService extends BaseService<WechatUser> {
    /**
     * 保存微信用户
     * */
    void saveWeChatUser(WechatUser entity);
}
