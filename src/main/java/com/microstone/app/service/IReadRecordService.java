package com.microstone.app.service;

import com.microstone.app.dto.GetReadRecordForAppShareRecordDTO;
import com.microstone.app.dto.GetReadRecordPageListDTO;
import com.microstone.app.dto.ReadRecordForShareRecordDTO;
import com.microstone.app.entity.ReadRecord;
import com.microstone.app.entity.WechatUser;
import com.microstone.app.param.GetClueForAppPageListParam;
import com.microstone.app.param.GetReadRecordForAppShareRecordParam;
import com.microstone.app.param.GetReadRecordForShareRecordParam;
import com.microstone.app.param.GetReadRecordPageListParam;
import com.microstone.app.param.ReadInfoParam;
import com.microstone.app.param.ThumbUpParam;
import com.microstone.app.param.UpdateReadTimeParam;
import com.microstone.app.vo.ReadRecordVO;
import org.microstone.core.mp.base.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.microstone.core.tool.api.R;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 *  服务类
 *
 * @author Chalsee
 * @since 2021-05-27
 */
public interface IReadRecordService extends BaseService<ReadRecord> {


    /**
     * 保存阅读信息
     * */
    ReadRecord readInfo(ReadInfoParam param);

    void updateReadTime(@RequestBody UpdateReadTimeParam param);

    /**
     * 点赞
     * */
    void thumbUp(ThumbUpParam param);

    /**
     * 获取查看列表
     * */
    IPage<GetReadRecordPageListDTO> getReadRecordForAppPageList(GetReadRecordPageListParam param);

    IPage<GetReadRecordForAppShareRecordDTO> getReadRecordForAppShareRecord(GetReadRecordForAppShareRecordParam param);

    IPage<WechatUser> getClueForAppPageList(GetClueForAppPageListParam param);

    List<ReadRecordForShareRecordDTO> getReadRecordForShareRecord(GetReadRecordForShareRecordParam param);

    Integer getBusinessCardCount();
}
