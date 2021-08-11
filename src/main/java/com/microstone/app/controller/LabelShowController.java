package com.microstone.app.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.microstone.app.dto.LabelShowDTO;
import com.microstone.app.dto.PlateDTO;
import com.microstone.app.entity.LabelShow;
import com.microstone.app.entity.Plate;
import com.microstone.app.service.ILabelShowService;
import com.microstone.app.service.IPlateService;
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

import java.util.List;

/**
 * @author XieXiaoDong
 * @date 2021/6/21/0021
 * @description 方法描述
 */
@RestController
@AllArgsConstructor
@RequestMapping("/labelShow")
@Api(value = "标签显示", tags = "接口")
public class LabelShowController extends MsController {
    private final ILabelShowService labelShowService;

    /**
     * @author XieXiaoDong
     * @date 2021/6/21/0021
     * @description 获取标签
     */
    @PostMapping("/getLabelShowList")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "获取标签")
    public R<List<LabelShowVO>> getLabelShowList() {
        return R.data(labelShowService.getLabelShowList());
    }

    /**
     * @author XieXiaoDong
     * @date 2021/6/21/0021
     * @description 添加标签
     */
    @PostMapping("/saveLabel")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "添加标签")
    public R saveLabel(@RequestBody LabelShowDTO labelShowDTO) {
        return R.status(labelShowService.saveLabel(labelShowDTO));
    }

    /**
     * @author XieXiaoDong
     * @date 2021/6/21/0021
     * @description 删除标签
     */
    @PostMapping("/deleteLabel")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "删除标签")
    public R deleteLabel(@RequestBody LabelShowDTO labelShowDTO) {
        return R.status(labelShowService.deleteLabel(labelShowDTO));
    }
}
