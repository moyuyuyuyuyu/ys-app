package com.microstone.app.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.microstone.app.dto.WechatOfficialAccountDTO;
import com.microstone.app.entity.Video;
import com.microstone.app.entity.WechatOfficialAccount;
import com.microstone.app.vo.WechatOfficialAccountVO;
import org.microstone.core.mp.base.BaseService;
import org.microstone.core.mp.support.Query;

/**
 * @author XieXiaoDong
 * @date 2021/6/30/0030
 * @description 方法描述
 */
public interface IWechatOfficialAccountService extends BaseService<WechatOfficialAccount> {
    Page<WechatOfficialAccountVO> getWechatOfficialAccount(Query query);

    Boolean saveWechatOfficialAccount(WechatOfficialAccountDTO wechatOfficialAccountDTO);

    Boolean deleteWechatOfficialAccount(WechatOfficialAccountDTO wechatOfficialAccountDTO);
}
