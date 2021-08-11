package com.microstone.app.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.microstone.app.entity.Article;
import com.microstone.app.query.ArticleQuery;
import com.microstone.app.vo.ArticleVO;
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
public interface ArticleMapper extends BaseMapper<Article> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param article
	 * @return
	 */
	List<ArticleVO> selectArticlePage(IPage page, ArticleVO article);

    List<ArticleVO> getArticleList(Page<ArticleVO> page, @Param("query") ArticleQuery query);

	List<ArticleVO> getArticleByTenantId(Page<ArticleVO> page,@Param("articleQuery") ArticleQuery articleQuery);
}
