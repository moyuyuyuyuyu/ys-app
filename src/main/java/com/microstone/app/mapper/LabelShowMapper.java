package com.microstone.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.microstone.app.entity.Document;
import com.microstone.app.entity.LabelShow;
import com.microstone.app.vo.LabelShowVO;

import java.util.List;

/**
 * @author XieXiaoDong
 * @date 2021/6/21/0021
 * @description 方法描述
 */
public interface LabelShowMapper extends BaseMapper<LabelShow> {
    List<LabelShowVO> getLabelShowList();
}
