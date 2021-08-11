package com.microstone.app.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.microstone.app.dto.PosterDTO;
import com.microstone.app.entity.Poster;
import com.microstone.app.query.ArticleQuery;
import com.microstone.app.vo.PosterVO;
import org.microstone.core.mp.base.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 *  服务类
 *
 * @author Chalsee
 * @since 2021-05-27
 */
public interface IPosterService extends BaseService<Poster> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param poster
	 * @return
	 */
	IPage<PosterVO> selectPosterPage(IPage<PosterVO> page, PosterVO poster);

    Page<PosterVO> getPoster(ArticleQuery query);

	Boolean savePoster(PosterDTO posterDTO);

	Boolean deletePoster(PosterDTO posterDTO);

    PosterVO getPosterDetail(PosterDTO posterDTO);

    Page<PosterVO> getPosterByTenantId(ArticleQuery articleQuery);
}
