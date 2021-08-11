package com.microstone.app.mapper;

import com.microstone.app.dto.GetReadRecordForAppShareRecordDTO;
import com.microstone.app.dto.GetReadRecordPageListDTO;
import com.microstone.app.dto.ReadRecordForShareRecordDTO;
import com.microstone.app.entity.ReadRecord;
import com.microstone.app.param.GetReadRecordForAppShareRecordParam;
import com.microstone.app.param.GetReadRecordForShareRecordParam;
import com.microstone.app.param.GetReadRecordPageListParam;
import com.microstone.app.vo.ReadRecordVO;
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
public interface ReadRecordMapper extends BaseMapper<ReadRecord> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param readRecord
	 * @return
	 */
	List<ReadRecordVO> selectReadRecordPage(IPage page, ReadRecordVO readRecord);

	IPage<GetReadRecordPageListDTO> getReadRecordForAppPageList(IPage page, @Param("param") GetReadRecordPageListParam param);

	IPage<GetReadRecordForAppShareRecordDTO> getReadRecordForAppShareRecord(IPage page, @Param("param") GetReadRecordForAppShareRecordParam param);

	List<ReadRecordForShareRecordDTO> getReadRecordForShareRecord(@Param("param") GetReadRecordForShareRecordParam param);
}
