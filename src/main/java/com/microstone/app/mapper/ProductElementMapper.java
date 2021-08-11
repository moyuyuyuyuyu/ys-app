package com.microstone.app.mapper;

import com.microstone.app.entity.ProductElement;
import com.microstone.app.vo.ProductElementVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

/**
 *  Mapper 接口
 *
 * @author Chalsee
 * @since 2021-05-27
 */
public interface ProductElementMapper extends BaseMapper<ProductElement> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param productElement
	 * @return
	 */
	List<ProductElementVO> selectProductElementPage(IPage page, ProductElementVO productElement);

}
