package com.microstone.app.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.microstone.app.dto.CurrentPositionDTO;
import com.microstone.app.dto.HistoryPositionDTO;
import com.microstone.app.dto.PositionCountDTO;
import com.microstone.app.dto.PositionDTO;
import com.microstone.app.param.GetCurrentPositionListParam;
import com.microstone.app.param.GetCustomerPositionParam;
import com.microstone.app.param.GetPositionCountParam;
import com.microstone.app.param.GetProductPositionListParam;
import com.microstone.app.service.IPositionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.microstone.core.boot.ctrl.MsController;
import org.microstone.core.tool.api.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/position")
@Api(value = "", tags = "接口")
public class PositionController extends MsController {

    private final IPositionService positionService;



    /**
     * 保存持仓
     * */
    @PostMapping("savePosition")
    @ApiOperation(value = "保存持仓")
    public R savePosition(@RequestBody PositionDTO dto){
        positionService.savePosition(dto);
        return R.status(true);
    }


    /**
     * 获取持仓统计
     * */
    @PostMapping("getPositionCount")
    @ApiOperation(value = "获取持仓统计")
    public R<List<PositionCountDTO>> getPositionCount(@RequestBody GetPositionCountParam param){
        return R.data(positionService.getPositionCount(param));
    }


    /**
     * 获取当前持仓
     * */
    @PostMapping("getCurrentPositionList")
    @ApiOperation(value = "获取当前持仓")
    public R<List<CurrentPositionDTO>> getCurrentPositionList(@RequestBody GetCurrentPositionListParam param){
        return R.data(positionService.getCurrentPositionList(param));
    }

    /**
     * 获取历史持仓列表
     */
    @PostMapping("getHistoryPositionList")
    @ApiOperation(value = "获取历史持仓列表")
    public R<List<HistoryPositionDTO>> getHistoryPositionList(@RequestBody GetCurrentPositionListParam param){
        return R.data(positionService.getHistoryPositionList(param));
    }

    /**
     * 获取产品持仓列表
     * */
    @PostMapping("getProductPositionList")
    @ApiOperation(value = "获取当前持仓")
    public R<List<PositionDTO>> getProductPositionList(@RequestBody GetProductPositionListParam param){
        return R.data(positionService.getProductPositionList(param));
    }


    /**
     * 获取客户交易详情
     * */
    @PostMapping("getCustomerPositionPage")
    @ApiOperation(value = "获取客户交易详情")
    public R<IPage<PositionDTO>> getCustomerPositionPage(@RequestBody GetCustomerPositionParam param){
        return R.data(positionService.getCustomerPositionPage(param));
    }



    /**
     * 同步所有持仓
     * */
    @PostMapping("syncAllPosition")
    public R syncAllPosition(){
        positionService.syncAllPosition();
        return R.status(true);
    }
}
