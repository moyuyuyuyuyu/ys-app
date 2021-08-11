package com.microstone.app.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.microstone.app.dto.LabelShowDTO;
import com.microstone.app.dto.PlateDTO;
import com.microstone.app.entity.LabelShow;
import com.microstone.app.entity.Plate;
import com.microstone.app.mapper.LabelShowMapper;
import com.microstone.app.mapper.PlateMapper;
import com.microstone.app.query.PlateQuery;
import com.microstone.app.service.ILabelShowService;
import com.microstone.app.service.IPlateService;
import com.microstone.app.vo.LabelShowVO;
import com.microstone.app.vo.PlateVO;
import com.microstone.app.vo.RecommendVO;
import com.microstone.system.entity.User;
import org.microstone.core.mp.base.BaseServiceImpl;
import org.microstone.core.mp.support.Query;
import org.microstone.core.tool.utils.Func;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.microstone.system.cache.UserCache;
import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  服务实现类
 *
 * @author Chalsee
 * @since 2021-05-27
 */
@Service
public class LabelShowServiceImpl extends BaseServiceImpl<LabelShowMapper, LabelShow> implements ILabelShowService {

	@Resource
	private PlateMapper plateMapper;
	/**
	 * @author XieXiaoDong
	 * @date 2021/6/21/0021
	 * @description 获取标签
	 */
	@Override
	public List<LabelShowVO> getLabelShowList() {
		List<LabelShowVO> labelShows = baseMapper.getLabelShowList();
		for (LabelShowVO labelShow : labelShows) {
			User user = UserCache.getUser(labelShow.getUpdateUser());
			if(!Func.isNull(user)){
				labelShow.setUpdateUserName(user.getRealName());
			}
		}
		return labelShows;
	}

	/**
	 * @author XieXiaoDong
	 * @date 2021/6/21/0021
	 * @description 添加标签
	 */
	@Override
	public Boolean saveLabel(LabelShowDTO labelShowDTO) {
		List<LabelShow> list = this.list(new LambdaQueryWrapper<LabelShow>().eq(LabelShow::getIsDeleted, 0));
		List<LabelShow> collect = list.stream().filter(p -> p.getPlateId().equals(labelShowDTO.getPlateId())).collect(Collectors.toList());
		if(!Func.isEmpty(collect)){
			throw new SecurityException("已经添加过该标签，请勿重复添加");
		}

		LabelShow labelShow = new LabelShow();
		BeanUtils.copyProperties(labelShowDTO,labelShow);
		Long plateId = labelShow.getPlateId();
		Plate plate = plateMapper.selectById(plateId);
		labelShow.setUpdateTime(plate.getUpdateTime());
		labelShow.setUpdateUser(plate.getUpdateUser());
		return this.save(labelShow);
	}

	/**
	 * @author XieXiaoDong
	 * @date 2021/6/21/0021
	 * @description 删除标签
	 */
	@Override
	public Boolean deleteLabel(LabelShowDTO labelShowDTO) {
		return this.removeById(labelShowDTO.getId());
	}
}
