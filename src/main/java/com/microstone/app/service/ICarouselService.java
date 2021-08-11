package com.microstone.app.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.microstone.app.dto.CarouselDTO;
import com.microstone.app.entity.Carousel;
import com.microstone.app.entity.Document;
import com.microstone.app.vo.CarouselVO;
import org.microstone.core.mp.base.BaseService;
import org.microstone.core.mp.support.Query;

import java.util.List;


/**
 * @author XieXiaoDong
 * @date 2021/6/22/0022
 * @description 方法描述
 */
public interface ICarouselService extends BaseService<Carousel> {

    Boolean saveCarousel(CarouselDTO carouselDTO);

    Boolean deleteCarousel(CarouselDTO carouselDTO);

    List<CarouselVO> getCarousel();
}
