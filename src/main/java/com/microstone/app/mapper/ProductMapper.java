package com.microstone.app.mapper;

import com.microstone.app.entity.Product;
import com.microstone.app.vo.ProductVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

/**
 *  Mapper 接口
 *
 * @author Chalsee
 * @since 2021-05-27
 */
public interface ProductMapper extends BaseMapper<Product> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param product
	 * @return
	 */
	List<ProductVO> selectProductPage(IPage page, ProductVO product);

}
