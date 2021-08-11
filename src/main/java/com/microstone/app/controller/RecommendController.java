package com.microstone.app.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.microstone.app.dto.PlateDTO;
import com.microstone.app.dto.RecommendDTO;
import com.microstone.app.entity.Plate;
import com.microstone.app.query.ArticleQuery;
import com.microstone.app.service.IRecommendService;
import com.microstone.app.vo.RecommendVO;
import com.microstone.app.vo.RecommendsVO;
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

import java.util.List;

/**
 * @author XieXiaoDong
 * @date 2021/6/21/0021
 * @description 方法描述
 */
@RestController
@AllArgsConstructor
@RequestMapping("/recommend")
@Api(value = "推荐显示", tags = "接口")
public class RecommendController extends MsController {
    private final IRecommendService recommendShowService;

    /**
     * @author XieXiaoDong
     * @date 2021/6/21/0021
     * @description 获取后台推荐
     */
    @PostMapping("/getRecommendList")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "获取后台推荐")
    public R<Page<RecommendsVO>> getRecommendList(@RequestBody Query query) {
        return R.data(recommendShowService.getRecommendList(query));
    }

    /**
     * @author XieXiaoDong
     * @date 2021/6/21/0021
     * @description 添加推荐
     */
    @PostMapping("/saveRecommend")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "添加/编辑 推荐")
    public R saveRecommend(@RequestBody RecommendDTO recommendDTO) {
        return R.status(recommendShowService.saveRecommend(recommendDTO));
    }

    /**
     * @author XieXiaoDong
     * @date 2021/6/21/0021
     * @description 删除推荐
     */
    @PostMapping("/deleteRecommend")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "删除推荐")
    public R deleteRecommend(@RequestBody RecommendDTO recommendDTO) {
        return R.status(recommendShowService.deleteRecommend(recommendDTO));
    }


    /**
     * @author XieXiaoDong
     * @date 2021/6/22/0022
     * @description 单独编辑名称
     */
    @PostMapping("/editName")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "编辑推荐名字")
    public R editName(@RequestBody RecommendDTO recommendDTO) {
        return R.status(recommendShowService.editName(recommendDTO));
    }



    /**
     * @author XieXiaoDong
     * @date 2021/6/21/0021
     * @description 获取app推荐
     */
    @PostMapping("/getAppRecommend")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "获取app推荐")
    public R<Page<RecommendVO>> getAppRecommend(@RequestBody ArticleQuery articleQuery) {
        return R.data(recommendShowService.getAppRecommend(articleQuery));
    }

    /**
     * @author XieXiaoDong
     * @date 2021/7/1/0001
     * @description 置顶
     */
    @PostMapping("/hasTop")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "置顶")
    public R hasTop(@RequestBody RecommendDTO recommendDTO) {
        return R.data(recommendShowService.hasTop(recommendDTO));
    }
}
