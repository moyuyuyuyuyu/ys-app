package com.microstone.app.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.microstone.app.dto.GetReadRecordForAppShareRecordDTO;
import com.microstone.app.dto.GetReadRecordPageListDTO;
import com.microstone.app.dto.ReadRecordForShareRecordDTO;
import com.microstone.app.entity.WechatUser;
import com.microstone.app.param.GetClueForAppPageListParam;
import com.microstone.app.param.GetReadRecordForAppShareRecordParam;
import com.microstone.app.param.GetReadRecordForShareRecordParam;
import com.microstone.app.param.GetReadRecordPageListParam;
import com.microstone.app.param.ReadInfoParam;
import com.microstone.app.param.ThumbUpParam;
import com.microstone.app.param.UpdateReadTimeParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;

import org.microstone.core.boot.ctrl.MsController;
import org.microstone.core.tool.api.R;
import org.springframework.web.bind.annotation.*;
import com.microstone.app.service.IReadRecordService;

import java.util.List;

/**
 *  控制器
 *
 * @author Chalsee
 * @since 2021-05-27
 */
@RestController
@AllArgsConstructor
@RequestMapping("/readRecord")
@Api(value = "", tags = "接口")
public class ReadRecordController extends MsController {

	private final IReadRecordService readRecordService;




	/**
	 * 保存阅读信息
	 * */
	@PostMapping("readInfo")
	@ApiOperation(value = "保存阅读信息")
	public R readInfo(@RequestBody ReadInfoParam param){
		return R.data(readRecordService.readInfo(param));
	}




	/**
	 * 更新阅读时长
	 * */
	@PostMapping("updateReadTime")
	@ApiOperation(value = "更新阅读时长")
	public R updateReadTime(@RequestBody UpdateReadTimeParam param){
		readRecordService.updateReadTime(param);
		return R.status(true);
	}


	/**
	 * 点赞
	 * */
	@PostMapping("thumbUp")
	@ApiOperation(value = "点赞")
	public R thumbUp(@RequestBody ThumbUpParam param){
		readRecordService.thumbUp(param);
		return R.status(true);
	}


	/**
	 * 获取查看列表
	 * */
	@PostMapping("getReadRecordForAppPageList")
	@ApiOperation(value = "获取查看列表")
	public R<IPage<GetReadRecordPageListDTO>> getReadRecordForAppPageList(@RequestBody GetReadRecordPageListParam param){
		return R.data(readRecordService.getReadRecordForAppPageList(param));
	}

	/**
	 * 获取查看列表for appShare
	 * */
	@PostMapping("getReadRecordForAppShareRecord")
	@ApiOperation(value = "获取查看列表for appShare")
	public R<IPage<GetReadRecordForAppShareRecordDTO>> getReadRecordForAppShareRecord(@RequestBody GetReadRecordForAppShareRecordParam param){
		return R.data(readRecordService.getReadRecordForAppShareRecord(param));
	}

	/**
	 * 获取线索
	 * */
	@PostMapping("getClueForAppPageList")
	@ApiOperation(value = "获取线索")
	public R<IPage<WechatUser>> getClueForAppPageList(@RequestBody GetClueForAppPageListParam param){
		return R.data(readRecordService.getClueForAppPageList(param));
	}


	/**
	 * 获取分享记录查看记录
	 * */
	@PostMapping("getReadRecordForShareRecord")
	@ApiOperation(value = "获取分享记录查看记录")
	public R<List<ReadRecordForShareRecordDTO>> getReadRecordForShareRecord(@RequestBody GetReadRecordForShareRecordParam param){
		return R.data(readRecordService.getReadRecordForShareRecord(param));
	}

	/**
	 * 名片查看次数
	 * */
	@PostMapping("/getBusinessCardCount")
	@ApiOperation(value = "名片查看次数")
	public R<Integer> getBusinessCardCount(){
		return R.data(readRecordService.getBusinessCardCount());
	}

}
