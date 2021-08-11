package com.microstone.app.service.impl;

import com.microstone.app.entity.PositionCount;
import com.microstone.app.mapper.PositionCountMapper;
import com.microstone.app.service.IPositionCountService;
import org.microstone.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class PositionCountServiceImpl extends BaseServiceImpl<PositionCountMapper, PositionCount> implements IPositionCountService {
}
