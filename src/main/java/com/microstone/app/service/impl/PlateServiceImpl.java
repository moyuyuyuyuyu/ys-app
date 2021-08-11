package com.microstone.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.microstone.app.dto.PlateDTO;
import com.microstone.app.entity.Plate;
import com.microstone.app.mapper.CarouselMapper;
import com.microstone.app.mapper.PlateMapper;
import com.microstone.app.mapper.RecommendMapper;
import com.microstone.app.query.PlateQuery;
import com.microstone.app.service.IPlateService;
import com.microstone.app.vo.CarouselVO;
import com.microstone.app.vo.PlateVO;
import com.microstone.app.vo.RecommendVO;
import com.microstone.app.vo.RecommendsVO;
import org.microstone.core.mp.base.BaseServiceImpl;
import org.microstone.core.tool.utils.Func;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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
public class PlateServiceImpl extends BaseServiceImpl<PlateMapper, Plate> implements IPlateService {

	@Resource
	private CarouselMapper carouselMapper;
	@Resource
	private RecommendMapper recommendMapper;

	@Override
	public IPage<PlateVO> selectPlatePage(IPage<PlateVO> page, PlateVO plate) {
		return page.setRecords(baseMapper.selectPlatePage(page, plate));
	}

    public void savePlate(PlateDTO dto){
		this.saveOrUpdate(dto);
	}


	/**
	 * @author XieXiaoDong
	 * @date 2021/5/28/0028
	 * @description 获取板块
	 */
	@Override
	public List<Plate> getPlateList(PlateDTO plateDTO) {
		if(Func.isNull(plateDTO.getEnabled())){
			return baseMapper.selectList(new LambdaQueryWrapper<Plate>().eq(Plate::getIsDeleted,0).like(Plate::getName,plateDTO.getName()).orderByAsc(Plate::getSort).orderByDesc(Plate::getCreateTime));
		}
		return baseMapper.selectList(new LambdaQueryWrapper<Plate>().eq(Plate::getIsDeleted,0).like(Plate::getName,plateDTO.getName()).orderByAsc(Plate::getSort).eq(Plate::getEnabled,plateDTO.getEnabled()).orderByDesc(Plate::getCreateTime));
	}

	/**
	 * @author XieXiaoDong
	 * @date 2021/5/28/0028
	 * @description 新增/编辑 板块
	 */
	@Override
	public Boolean saveOrUpdatePlate(PlateDTO plateDTO) {
		Plate plate = new Plate();
		BeanUtils.copyProperties(plateDTO,plate);
		return this.saveOrUpdate(plate);
	}

	/**
	 * @author XieXiaoDong
	 * @date 2021/5/28/0028
	 * @description 删除板块
	 */
	@Override
	public Boolean deletePlate(PlateDTO plateDTO) {
		Long id = plateDTO.getId();
		List<RecommendVO> recommends = baseMapper.getRecommend();
		List<RecommendVO> collect = recommends.stream().filter(p -> p.getPlateId().equals(id)).collect(Collectors.toList());
		if(!Func.isEmpty(collect)){
			throw new SecurityException("该标签下尚有未删除的关联内容，删除失败！");
		}
		return this.removeById(plateDTO);
	}


	/**
	 * @author XieXiaoDong
	 * @date 2021/6/2/0002
	 * @description 获取精选内容
	 */
    @Override
    public Page<RecommendVO> getRecommend(PlateQuery query) {
        Page<RecommendVO> page = new Page<>(query.getCurrent(),query.getSize());
        return page.setRecords(baseMapper.getRecommend());
    }


    @Override
    public void generalDelete(Long id){
		List<CarouselVO> carousel = carouselMapper.getCarousel();
		List<CarouselVO> collect = carousel.stream().filter(p -> Func.equals(p.getRelationId(),id)).collect(Collectors.toList());
		Page page = new Page(-1,-1);
		List<RecommendsVO> recommendList = recommendMapper.getRecommendList(page);
		List<RecommendsVO> collect1 = recommendList.stream().filter(p -> Func.equals(p.getNewsId(),id)).collect(Collectors.toList());
		if(!Func.isEmpty(collect) || !Func.isEmpty(collect1)){
			throw new SecurityException("该内容下尚有未删除的首页轮播图或推荐设置，删除失败！");
		}
	}
}
