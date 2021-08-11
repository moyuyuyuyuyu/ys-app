package com.microstone.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.microstone.app.dto.CurrentPositionDTO;
import com.microstone.app.dto.HistoryPositionDTO;
import com.microstone.app.dto.PositionCountDTO;
import com.microstone.app.dto.PositionDTO;
import com.microstone.app.entity.Position;
import com.microstone.app.param.GetCurrentPositionListParam;
import com.microstone.app.param.GetCustomerPositionParam;
import com.microstone.app.param.GetPositionCountParam;
import com.microstone.app.param.GetProductPositionListParam;
import org.microstone.core.mp.base.BaseService;

import java.util.List;

public interface IPositionService extends BaseService<Position> {

    /**
     * 保存持仓
     * */
     void savePosition(PositionDTO dto);


    /**
     * 获取持仓统计
     * */
    List<PositionCountDTO> getPositionCount(GetPositionCountParam param);


    List<CurrentPositionDTO> getCurrentPositionList(GetCurrentPositionListParam param);

    /**
     * 获取持仓列表
     */
    List<HistoryPositionDTO> getHistoryPositionList(GetCurrentPositionListParam param);

    /**
     * 获取产品持仓列表
     * */
    List<PositionDTO> getProductPositionList(GetProductPositionListParam param);


    /**
     * 获取客户交易详情
     * */
    IPage<PositionDTO> getCustomerPositionPage(GetCustomerPositionParam param);

    /**
     * 同步所有持仓
     * */
    void syncAllPosition();
}
