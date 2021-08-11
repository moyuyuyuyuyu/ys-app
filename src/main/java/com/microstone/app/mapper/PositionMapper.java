package com.microstone.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.microstone.app.dto.CurrentPositionDTO;
import com.microstone.app.dto.HistoryPositionDTO;
import com.microstone.app.entity.Position;
import com.microstone.app.param.GetCurrentPositionListParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PositionMapper extends BaseMapper<Position> {

    List<CurrentPositionDTO> getCurrentPositionList(@Param("param") GetCurrentPositionListParam param);

    List<HistoryPositionDTO> getHistoryPositionList(@Param("param") GetCurrentPositionListParam param);
}
