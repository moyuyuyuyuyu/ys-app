package com.microstone.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.microstone.app.entity.UserCertificate;
import com.microstone.app.param.GetUserInfoParam;
import org.microstone.core.mp.base.BaseService;

import java.util.List;

/**
 *  服务类
 *
 * @author Chalsee
 * @since 2021-06-07
 */
public interface IUserCertificateService extends BaseService<UserCertificate> {
    /**
     * 保存证书
     * */
    void saveUserCertificate(UserCertificate certificate);


    /**
     * 获取证书列表
     * */
    List<UserCertificate> getUserCertificateList(GetUserInfoParam param);
}
