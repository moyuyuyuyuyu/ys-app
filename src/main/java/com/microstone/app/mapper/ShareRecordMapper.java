package com.microstone.app.mapper;

import com.microstone.app.dto.GetShareRecordPageListDTO;
import com.microstone.app.entity.ShareRecord;
import com.microstone.app.param.GetShareRecordForAppPageListParam;
import com.microstone.app.param.GetShareRecordPageListParam;
import com.microstone.app.vo.ShareRecordVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  Mapper 接口
 *
 * @author Chalsee
 * @since 2021-05-27
 */
public interface ShareRecordMapper extends BaseMapper<ShareRecord> {


	IPage<GetShareRecordPageListDTO> getShareRecordPageList(IPage page, @Param("param") GetShareRecordPageListParam param);

	IPage<GetShareRecordPageListDTO> getShareRecordForAppPageList(IPage page, @Param("param") GetShareRecordForAppPageListParam param);
}
