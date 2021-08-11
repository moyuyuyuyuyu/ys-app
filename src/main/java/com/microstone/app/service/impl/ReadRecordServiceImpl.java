package com.microstone.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.microstone.app.dto.GetReadRecordForAppShareRecordDTO;
import com.microstone.app.dto.GetReadRecordPageListDTO;
import com.microstone.app.dto.GetShareRecordPageListDTO;
import com.microstone.app.dto.ReadRecordForShareRecordDTO;
import com.microstone.app.entity.Customer;
import com.microstone.app.entity.ReadRecord;
import com.microstone.app.entity.ShareRecord;
import com.microstone.app.entity.WechatUser;
import com.microstone.app.param.GetReadRecordForAppShareRecordParam;
import com.microstone.app.param.GetReadRecordForShareRecordParam;
import com.microstone.app.param.GetReadRecordPageListParam;
import com.microstone.app.param.GetShareRecordPageListParam;
import com.microstone.app.param.ReadInfoParam;
import com.microstone.app.param.ThumbUpParam;
import com.microstone.app.param.UpdateReadTimeParam;
import com.microstone.app.service.ICustomerService;
import com.microstone.app.service.IShareRecordService;
import com.microstone.app.service.IWechatUserService;
import com.microstone.app.vo.ReadRecordVO;
import com.microstone.app.mapper.ReadRecordMapper;
import com.microstone.app.service.IReadRecordService;
import com.microstone.system.cache.UserCache;
import org.microstone.core.mp.base.BaseServiceImpl;
import org.microstone.core.mp.support.Condition;
import org.microstone.core.secure.utils.AuthUtil;
import org.microstone.core.tool.api.R;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import com.microstone.app.param.GetClueForAppPageListParam;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  服务实现类
 *
 * @author Chalsee
 * @since 2021-05-27
 */
@Service
public class ReadRecordServiceImpl extends BaseServiceImpl<ReadRecordMapper, ReadRecord> implements IReadRecordService {


	@Resource
	private IWechatUserService wechatUserService;

	@Resource
	private IShareRecordService shareRecordService;

	@Resource
	@Lazy
	private ICustomerService customerService;

	/**
	 * 保存阅读信息
	 * */
	@Override
	public ReadRecord readInfo(ReadInfoParam param){
		ReadRecord record = new ReadRecord();
		record.setShareId(param.getShareId());
		record.setReadDate(new Date());
		record.setReadTime(param.getReadTime());
		record.setThumbUp(false);
		record.setReadUserId(param.getWeChatId());
		ShareRecord shareRecord = shareRecordService.getById(param.getShareId());
		shareRecord.setReadCount(shareRecord.getReadCount() + 1);
		record.setShareUserId(shareRecord.getShareUserId());
		record.setRelationId(shareRecord.getRelationId());
		Integer count = this.count(new LambdaQueryWrapper<ReadRecord>()
				.eq(ReadRecord::getRelationId, shareRecord.getRelationId())
				.eq(ReadRecord::getShareUserId, shareRecord.getShareUserId())
				.eq(ReadRecord::getReadUserId, param.getWeChatId()));
		if(count == 0){
			record.setFirstRead(true);
			shareRecord.setReadUserCount(shareRecord.getReadUserCount() + 1);
		}else{
			record.setFirstRead(false);
		}
		shareRecordService.saveOrUpdate(shareRecord);
		this.saveOrUpdate(record);
		return record;
	}


	@Override
	public void updateReadTime( UpdateReadTimeParam param){
		ReadRecord readRecord = this.getById(param.getId());
		readRecord.setReadTime(param.getReadTime());
		this.saveOrUpdate(readRecord);
	}

	/**
	 * 获取查看列表
	 * */
	@Override
	public IPage<GetReadRecordPageListDTO> getReadRecordForAppPageList(GetReadRecordPageListParam param){
		IPage<GetReadRecordPageListDTO> page = Condition.getPage(param);
		param.setUserId(AuthUtil.getAppUserId());
		page = baseMapper.getReadRecordForAppPageList(page, param);
		List<GetReadRecordPageListDTO> list = page.getRecords();
		for (GetReadRecordPageListDTO item : list){
			 WechatUser wu= wechatUserService.getById(item.getReadUserId());
			item.setReadUserName(wu.getName());
			item.setReadUserHeader(wu.getHeadImage());
			item.setReadMobile(wu.getPhone());
		}
		page.setRecords(list);
		return page;
	}


	@Override
	public IPage<GetReadRecordForAppShareRecordDTO> getReadRecordForAppShareRecord(GetReadRecordForAppShareRecordParam param){
		IPage<GetReadRecordForAppShareRecordDTO> page = Condition.getPage(param);
		param.setUserId(AuthUtil.getAppUserId());
		page = baseMapper.getReadRecordForAppShareRecord(page, param);
		List<GetReadRecordForAppShareRecordDTO> list = page.getRecords();
		for (GetReadRecordForAppShareRecordDTO item : list){
			WechatUser wu= wechatUserService.getById(item.getReadUserId());
			item.setReadUserName(wu.getName());
			item.setReadUserHeader(wu.getHeadImage());
			item.setReadMobile(wu.getPhone());
		}
		page.setRecords(list);
		return page;
	}


	@Override
	public IPage<WechatUser> getClueForAppPageList(GetClueForAppPageListParam param){
		IPage<WechatUser> page = Condition.getPage(param);
		List<ReadRecord> recordList = this.list(new LambdaQueryWrapper<ReadRecord>()
				.eq(ReadRecord::getShareUserId, AuthUtil.getAppUserId())
				.eq(ReadRecord::getIsDeleted, 0));
		List<Long> wechatIds = customerService.list(new LambdaQueryWrapper<Customer>()
				.isNotNull(Customer::getWechatId)
				.eq(Customer::getIsDeleted , 0)
				.eq(Customer::getUserId, AuthUtil.getAppUserId())).stream().map(t -> t.getWechatId()).collect(Collectors.toList());
		List<Long> ids = recordList.stream().map(t -> t.getReadUserId()).filter(t -> !wechatIds.contains(t)).collect(Collectors.toList());
		IPage<WechatUser> users = wechatUserService.page(page, new LambdaQueryWrapper<WechatUser>().in(WechatUser::getId, ids));
		return users;
	}



	@Override
	public List<ReadRecordForShareRecordDTO> getReadRecordForShareRecord(GetReadRecordForShareRecordParam param){
		return baseMapper.getReadRecordForShareRecord(param);
	}


    /**
	 * 点赞
	 * */
	@Override
	public void thumbUp(ThumbUpParam param){
		ReadRecord readRecord = this.getById(param.getId());
		readRecord.setThumbUp(param.getThumbUp());
		this.saveOrUpdate(readRecord);
	}

	/**
	 * @author XieXiaoDong
	 * @date 2021/7/27/0027
	 * @description 名片查看次数
	 */
	@Override
	public Integer getBusinessCardCount() {
		Long appUserId = AuthUtil.getAppUserId();
		return this.list(new LambdaQueryWrapper<ReadRecord>().eq(ReadRecord::getShareUserId, appUserId)).size();
	}
}
