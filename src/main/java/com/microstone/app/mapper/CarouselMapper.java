package com.microstone.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.microstone.app.entity.Carousel;
import com.microstone.app.entity.Document;
import com.microstone.app.query.ArticleQuery;
import com.microstone.app.vo.CarouselVO;
import com.microstone.app.vo.DocumentVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  Mapper 接口
 *
 * @author Chalsee
 * @since 2021-05-27
 */
public interface CarouselMapper extends BaseMapper<Carousel> {

    List<CarouselVO> getCarousel();
}
