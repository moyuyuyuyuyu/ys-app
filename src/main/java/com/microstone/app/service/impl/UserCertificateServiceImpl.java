package com.microstone.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.microstone.app.entity.UserCertificate;
import com.microstone.app.mapper.UserCertificateMapper;
import com.microstone.app.param.GetUserInfoParam;
import com.microstone.app.service.IUserCertificateService;
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
public class UserCertificateServiceImpl extends BaseServiceImpl<UserCertificateMapper, UserCertificate> implements IUserCertificateService {


    /**
     * 保存证书
     * */
    @Override
    public void saveUserCertificate(UserCertificate certificate){
        if(certificate.getId() == null){
            certificate.setUserId(AuthUtil.getAppUserId());
            this.saveOrUpdate(certificate);
        }else{
            if (certificate.getIsDeleted()==1){
                this.removeById(certificate.getId());
            }else{
                this.saveOrUpdate(certificate);
            }
        }

    }


    /**
     * 获取证书列表
     * */
    @Override
    public List<UserCertificate> getUserCertificateList(GetUserInfoParam param){
        Long userId;
        if(param.getAppUserId() != null){
            userId = param.getAppUserId();
        }else{
            userId = AuthUtil.getAppUserId();
        }
        return this.list(new LambdaQueryWrapper<UserCertificate>()
                .eq(UserCertificate::getIsDeleted, 0)
                .eq(UserCertificate::getUserId, userId)
                .orderByAsc(UserCertificate::getCreateTime));
    }

}
