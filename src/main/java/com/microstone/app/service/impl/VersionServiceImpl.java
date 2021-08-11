package com.microstone.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.microstone.app.entity.Version;
import com.microstone.app.mapper.VersionMapper;
import com.microstone.app.service.IVersionService;
import org.microstone.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class VersionServiceImpl extends BaseServiceImpl<VersionMapper, Version> implements IVersionService {

}
