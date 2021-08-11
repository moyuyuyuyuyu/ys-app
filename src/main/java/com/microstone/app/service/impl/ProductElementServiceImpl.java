package com.microstone.app.service.impl;

import com.microstone.app.entity.ProductElement;
import com.microstone.app.vo.ProductElementVO;
import com.microstone.app.mapper.ProductElementMapper;
import com.microstone.app.service.IProductElementService;
import org.microstone.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 *  服务实现类
 *
 * @author Chalsee
 * @since 2021-05-27
 */
@Service
public class ProductElementServiceImpl extends BaseServiceImpl<ProductElementMapper, ProductElement> implements IProductElementService {

	@Override
	public IPage<ProductElementVO> selectProductElementPage(IPage<ProductElementVO> page, ProductElementVO productElement) {
		return page.setRecords(baseMapper.selectProductElementPage(page, productElement));
	}

}
