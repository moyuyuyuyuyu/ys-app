package com.microstone.app.service;

import com.microstone.app.entity.ProductElement;
import com.microstone.app.vo.ProductElementVO;
import org.microstone.core.mp.base.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 *  服务类
 *
 * @author Chalsee
 * @since 2021-05-27
 */
public interface IProductElementService extends BaseService<ProductElement> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param productElement
	 * @return
	 */
	IPage<ProductElementVO> selectProductElementPage(IPage<ProductElementVO> page, ProductElementVO productElement);

}
