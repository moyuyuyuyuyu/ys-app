package com.microstone.app.service;

import com.microstone.app.dto.GetShareRecordPageListDTO;
import com.microstone.app.dto.HomePageShareCountInfoDTO;
import com.microstone.app.dto.ShareCountInfoDTO;
import com.microstone.app.entity.ShareRecord;
import com.microstone.app.param.GetShareCountInfoParam;
import com.microstone.app.param.GetShareRecordByCodeParam;
import com.microstone.app.param.GetShareRecordForAppPageListParam;
import com.microstone.app.param.GetShareRecordPageListParam;
import com.microstone.app.param.ShareInfoParam;
import com.microstone.app.vo.ShareRecordVO;
import org.microstone.core.mp.base.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;

import javax.servlet.http.HttpServletResponse;

/**
 *  服务类
 *
 * @author Chalsee
 * @since 2021-05-27
 */
public interface IShareRecordService extends BaseService<ShareRecord> {
    /**
     * 分享信息
     * */
    ShareRecord shareInfo(ShareInfoParam param);


    /**
     * 获取分享code
     * */
    ShareRecord getShareRecordByCode(GetShareRecordByCodeParam param);

    /**
     * 分享
     * */
    IPage<GetShareRecordPageListDTO> getShareRecordPageList(GetShareRecordPageListParam param);


    /**
     * 获取App分享列表
     * */
    IPage<GetShareRecordPageListDTO> getShareRecordForAppPageList(GetShareRecordForAppPageListParam param);


    /**
     * 获取获客统计
     * */
    ShareCountInfoDTO getShareCountInfo(GetShareCountInfoParam param);


    /**
     * 获取首页统计
     */
    HomePageShareCountInfoDTO getHomePageShareCountInfo();


    /**
     * 分享
     */
    void exportShareRecordPageList(GetShareRecordPageListParam param, HttpServletResponse response);
}
