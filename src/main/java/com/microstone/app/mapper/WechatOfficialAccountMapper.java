package com.microstone.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.microstone.app.entity.WechatOfficialAccount;
import com.microstone.app.vo.WechatOfficialAccountVO;


import java.util.List;

/**
 *  Mapper 接口
 *
 * @author Chalsee
 * @since 2021-05-27
 */
public interface WechatOfficialAccountMapper extends BaseMapper<WechatOfficialAccount> {

	List<WechatOfficialAccountVO> getWechatOfficialAccount(Page<WechatOfficialAccountVO> page);
}
