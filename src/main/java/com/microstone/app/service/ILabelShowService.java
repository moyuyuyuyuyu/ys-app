package com.microstone.app.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.microstone.app.dto.LabelShowDTO;
import com.microstone.app.dto.PlateDTO;
import com.microstone.app.entity.Document;
import com.microstone.app.entity.LabelShow;
import com.microstone.app.vo.LabelShowVO;
import org.microstone.core.mp.base.BaseService;
import org.microstone.core.mp.support.Query;

import java.util.List;

/**
 * @author XieXiaoDong
 * @date 2021/6/21/0021
 * @description 方法描述
 */
public interface ILabelShowService extends BaseService<LabelShow> {
    List<LabelShowVO> getLabelShowList();

    Boolean saveLabel(LabelShowDTO labelShowDTO);

    Boolean deleteLabel(LabelShowDTO labelShowDTO);
}
