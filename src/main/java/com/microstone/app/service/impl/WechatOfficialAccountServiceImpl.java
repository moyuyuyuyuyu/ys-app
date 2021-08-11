package com.microstone.app.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.microstone.app.dto.WechatOfficialAccountDTO;
import com.microstone.app.entity.WechatOfficialAccount;
import com.microstone.app.mapper.WechatOfficialAccountMapper;
import com.microstone.app.service.IWechatOfficialAccountService;
import com.microstone.app.vo.WechatOfficialAccountVO;
import org.microstone.core.mp.base.BaseServiceImpl;
import org.microstone.core.mp.support.Query;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author XieXiaoDong
 * @date 2021/6/30/0030
 * @description 方法描述
 */
@Service
public class WechatOfficialAccountServiceImpl extends BaseServiceImpl<WechatOfficialAccountMapper, WechatOfficialAccount>  implements IWechatOfficialAccountService {

    /**
     * @author XieXiaoDong
     * @date 2021/6/30/0030
     * @description 获取微信公众号列表
     */
    @Override
    public Page<WechatOfficialAccountVO> getWechatOfficialAccount(Query query) {
        Page<WechatOfficialAccountVO> page = new Page<WechatOfficialAccountVO>(query.getCurrent(),query.getSize());
        List<WechatOfficialAccountVO> list = baseMapper.getWechatOfficialAccount(page);
        return page.setRecords(list);
    }

    /**
     * @author XieXiaoDong
     * @date 2021/6/30/0030
     * @description 新增微信公众号
     */
    @Override
    public Boolean saveWechatOfficialAccount(WechatOfficialAccountDTO wechatOfficialAccountDTO) {
        WechatOfficialAccount wechatOfficialAccount = new WechatOfficialAccountDTO();
        BeanUtils.copyProperties(wechatOfficialAccountDTO,wechatOfficialAccount);
        return this.saveOrUpdate(wechatOfficialAccount);
    }

    /**
     * @author XieXiaoDong
     * @date 2021/6/30/0030
     * @description 删除微信公众号
     */
    @Override
    public Boolean deleteWechatOfficialAccount(WechatOfficialAccountDTO wechatOfficialAccountDTO) {
        return this.removeById(wechatOfficialAccountDTO.getId());
    }
}
