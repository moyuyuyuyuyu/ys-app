package com.microstone.app.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.microstone.app.entity.Poster;
import com.microstone.app.query.ArticleQuery;
import com.microstone.app.vo.PosterVO;
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
public interface PosterMapper extends BaseMapper<Poster> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param poster
	 * @return
	 */
	List<PosterVO> selectPosterPage(IPage page, PosterVO poster);

    List<PosterVO> getPoster(Page<PosterVO> page, @Param("query") ArticleQuery query);

    List<PosterVO> getPosterByTenantId(Page<PosterVO> page,@Param("articleQuery") ArticleQuery articleQuery);
}
