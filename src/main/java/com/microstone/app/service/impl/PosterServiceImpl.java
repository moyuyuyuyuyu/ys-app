package com.microstone.app.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.microstone.app.dto.PosterDTO;
import com.microstone.app.entity.Poster;
import com.microstone.app.mapper.PosterMapper;
import com.microstone.app.query.ArticleQuery;
import com.microstone.app.service.IPlateService;
import com.microstone.app.service.IPosterService;
import com.microstone.app.vo.PosterVO;
import org.microstone.core.mp.base.BaseServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  服务实现类
 *
 * @author Chalsee
 * @since 2021-05-27
 */
@Service
public class PosterServiceImpl extends BaseServiceImpl<PosterMapper, Poster> implements IPosterService {

	@Autowired
	private IPlateService plateService;

	@Override
	public IPage<PosterVO> selectPosterPage(IPage<PosterVO> page, PosterVO poster) {
		return page.setRecords(baseMapper.selectPosterPage(page, poster));
	}

	/**
	 * @author XieXiaoDong
	 * @date 2021/5/28/0028
	 * @description 获取海报
	 */
    @Override
    public Page<PosterVO> getPoster(ArticleQuery query) {
        Page<PosterVO> page = new Page<>(query.getCurrent(),query.getSize());
        List<PosterVO> articleVOList = baseMapper.getPoster(page,query);
        return page.setRecords(articleVOList);
    }

    /**
     * @author XieXiaoDong
     * @date 2021/5/31/0031
     * @description 新增/编辑 海报
     */
	@Override
	public Boolean savePoster(PosterDTO posterDTO) {
		Poster poster = new Poster();
		BeanUtils.copyProperties(posterDTO,poster);
		return this.saveOrUpdate(poster);
	}

	/**
	 * @author XieXiaoDong
	 * @date 2021/5/31/0031
	 * @description 删除海报
	 */
	@Override
	public Boolean deletePoster(PosterDTO posterDTO) {
		//验证删除
		plateService.generalDelete(posterDTO.getId());
		return this.removeById(posterDTO.getId());
	}

	/**
	 * @author XieXiaoDong
	 * @date 2021/6/30/0030
	 * @description 海报详情
	 */
    @Override
    public PosterVO getPosterDetail(PosterDTO posterDTO) {
		Poster poster = baseMapper.selectById(posterDTO.getId());
		Integer readingQuantity = poster.getReadingQuantity();
		poster.setReadingQuantity(++readingQuantity);
		this.updateById(poster);

		PosterVO posterVO = new PosterVO();
		BeanUtils.copyProperties(poster,posterVO);
		return posterVO;
    }

    /**
     * @author XieXiaoDong
     * @date 2021/7/1/0001
     * @description 根据租户id获取海报
     */
    @Override
    public Page<PosterVO> getPosterByTenantId(ArticleQuery articleQuery) {
        Page<PosterVO> page = new Page<>(articleQuery.getCurrent(),articleQuery.getSize());
        List<PosterVO> list = baseMapper.getPosterByTenantId(page,articleQuery);
        return page.setRecords(list);
    }
}
