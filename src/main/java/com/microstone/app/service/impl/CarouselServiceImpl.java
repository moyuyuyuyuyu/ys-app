package com.microstone.app.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.microstone.app.dto.CarouselDTO;
import com.microstone.app.entity.Announcement;
import com.microstone.app.entity.Carousel;
import com.microstone.app.mapper.AnnouncementMapper;
import com.microstone.app.mapper.CarouselMapper;
import com.microstone.app.service.IAnnouncementService;
import com.microstone.app.service.ICarouselService;
import com.microstone.app.vo.AnnouncementVO;
import com.microstone.app.vo.CarouselVO;
import com.microstone.system.cache.UserCache;
import com.microstone.system.entity.User;
import org.microstone.core.mp.base.BaseServiceImpl;
import org.microstone.core.mp.support.Query;
import org.microstone.core.tool.utils.Func;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *  服务实现类
 *
 * @author Chalsee
 * @since 2021-05-27
 */
@Service
public class CarouselServiceImpl extends BaseServiceImpl<CarouselMapper, Carousel> implements ICarouselService {


    /**
     * @author XieXiaoDong
     * @date 2021/6/22/0022
     * @description 添加轮播图
     */
    @Override
    public Boolean saveCarousel(CarouselDTO carouselDTO) {
        Carousel carousel = new Carousel();
        BeanUtils.copyProperties(carouselDTO,carousel);
        return this.saveOrUpdate(carousel);
    }

    /**
     * @author XieXiaoDong
     * @date 2021/6/22/0022
     * @description 删除轮播图
     */
    @Override
    public Boolean deleteCarousel(CarouselDTO carouselDTO) {
        return this.removeById(carouselDTO.getId());
    }

    /**
     * @author XieXiaoDong
     * @date 2021/6/22/0022
     * @description 获取轮播图信息
     */
    @Override
    public List<CarouselVO> getCarousel() {
        List<CarouselVO> carouselVOList = baseMapper.getCarousel();
        for (CarouselVO carouselVO : carouselVOList) {
            User user = UserCache.getUser(carouselVO.getUpdateUser());
            if(!Func.isNull(user)){
                carouselVO.setUpdateUserName(user.getRealName());
            }
        }
        return carouselVOList;
    }
}
