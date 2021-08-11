package com.microstone.app.dto;

import com.microstone.app.entity.VideoPool;
import com.microstone.app.entity.WechatOfficialAccount;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author XieXiaoDong
 * @date 2021/6/30/0030
 * @description 方法描述
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WechatOfficialAccountDTO extends WechatOfficialAccount {
    private static final long serialVersionUID = 1L;
}
