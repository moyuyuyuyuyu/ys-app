package com.microstone.app.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.microstone.app.dto.CarouselDTO;
import com.microstone.app.dto.DocumentDTO;
import com.microstone.app.dto.LabelShowDTO;
import com.microstone.app.service.ICarouselService;
import com.microstone.app.service.ILabelShowService;
import com.microstone.app.vo.CarouselVO;
import com.microstone.app.vo.LabelShowVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.microstone.core.boot.ctrl.MsController;
import org.microstone.core.mp.support.Query;
import org.microstone.core.tool.api.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author XieXiaoDong
 * @date 2021/6/21/0021
 * @description 方法描述
 */
@RestController
@AllArgsConstructor
@RequestMapping("/carousel")
@Api(value = "首页轮播图", tags = "接口")
public class CarouselController extends MsController {
    private final ICarouselService carouselService;

    /**
     * @author XieXiaoDong
     * @date 2021/6/22/0022
     * @description 添加轮播图
     */
    @PostMapping("/saveCarousel")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "添加/编辑 轮播图")
    public R saveCarousel(@RequestBody CarouselDTO carouselDTO) {
        return R.status(carouselService.saveCarousel(carouselDTO));
    }

    /**
     * @author XieXiaoDong
     * @date 2021/6/22/0022
     * @description 删除轮播图
     */
    @PostMapping("/deleteCarousel")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "删除轮播图")
    public R deleteCarousel(@RequestBody CarouselDTO carouselDTO) {
        return R.status(carouselService.deleteCarousel(carouselDTO));
    }

    /**
     * @author XieXiaoDong
     * @date 2021/6/22/0022
     * @description 获取后台轮播图信息
     */
    @PostMapping("/getCarousel")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "获取轮播图信息")
    public R<List<CarouselVO>> getCarousel() {
        return R.data(carouselService.getCarousel());
    }

}
