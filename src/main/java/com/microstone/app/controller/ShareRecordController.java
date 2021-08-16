package com.microstone.app.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.microstone.app.dto.GetShareRecordPageListDTO;
import com.microstone.app.dto.HomePageShareCountInfoDTO;
import com.microstone.app.dto.ShareCountInfoDTO;
import com.microstone.app.entity.ShareRecord;
import com.microstone.app.param.GetShareCountInfoParam;
import com.microstone.app.param.GetShareRecordByCodeParam;
import com.microstone.app.param.GetShareRecordForAppPageListParam;
import com.microstone.app.param.GetShareRecordPageListParam;
import com.microstone.app.param.ShareInfoParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

import org.microstone.core.boot.ctrl.MsController;
import org.microstone.core.tool.api.R;
import org.springframework.web.bind.annotation.*;
import com.microstone.app.service.IShareRecordService;

import javax.servlet.http.HttpServletResponse;

/**
 * 控制器
 *
 * @author Chalsee
 * @since 2021-05-27
 */
@RestController
@AllArgsConstructor
@RequestMapping("/shareRecord")
@Api(value = "", tags = "接口")
public class ShareRecordController extends MsController {

    private final IShareRecordService shareRecordService;

    /**
     * 分享信息
     */
    @PostMapping("shareInfo")
    @ApiOperation(value = "分享信息")
    public R shareInfo(@RequestBody ShareInfoParam param) {
        return R.data(shareRecordService.shareInfo(param));
    }


    /**
     * 获取分享by code
     * */
    @PostMapping("getShareRecordByCode")
    @ApiOperation(value = "获取分享by code")
    public R<ShareRecord> getShareRecordByCode(@RequestBody GetShareRecordByCodeParam param){
        return R.data(shareRecordService.getShareRecordByCode(param));
    }

    /**
     * 获取分享分页
     */
    @PostMapping("getShareRecordPageList")
    @ApiOperation(value = "获取分享分页")
    public R<IPage<GetShareRecordPageListDTO>> getShareRecordPageList(@RequestBody GetShareRecordPageListParam param) {
        return R.data(shareRecordService.getShareRecordPageList(param));
    }



    /**
     * 导出分享
     */
    @PostMapping("exportShareRecordPageList")
    @ApiOperation(value = "导出分享")
    public void exportShareRecordPageList(@RequestBody GetShareRecordPageListParam param, HttpServletResponse response){
        shareRecordService.exportShareRecordPageList(param, response);
    }

    /**
     * 获取分享列表ForApp
     */
    @PostMapping("getShareRecordForAppPageList")
    @ApiOperation(value = "获取分享列表ForApp")
    public R<IPage<GetShareRecordPageListDTO>> getShareRecordForAppPageList(@RequestBody GetShareRecordForAppPageListParam param) {
        return R.data(shareRecordService.getShareRecordForAppPageList(param));
    }


    /**
     * 获取获客统计
     */
    @PostMapping("getShareCountInfo")
    @ApiOperation(value = "获取获客统计")
    public R<ShareCountInfoDTO> getShareCountInfo(@RequestBody GetShareCountInfoParam param) {
        return R.data(shareRecordService.getShareCountInfo(param));
    }




    /**
     * 获取首页统计
     */
    @PostMapping("getHomePageShareCountInfo")
    @ApiOperation(value = "获取首页统计")
    public R<HomePageShareCountInfoDTO> getHomePageShareCountInfo(){
        return R.data(shareRecordService.getHomePageShareCountInfo());
    }
}
