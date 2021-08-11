package com.microstone.app.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.microstone.app.dto.ArticleDTO;
import com.microstone.app.entity.Article;
import com.microstone.app.query.ArticleQuery;
import com.microstone.app.vo.ArticleVO;
import org.microstone.core.mp.base.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 *  服务类
 *
 * @author Chalsee
 * @since 2021-05-27
 */
public interface IArticleService extends BaseService<Article> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param article
	 * @return
	 */
	IPage<ArticleVO> selectArticlePage(IPage<ArticleVO> page, ArticleVO article);

    Page<ArticleVO> getArticleList(ArticleQuery query);

	Boolean deleteArticle(ArticleDTO articleDTO);

	Long saveArticle(ArticleDTO articleDTO);

	Boolean editArticle(ArticleDTO articleDTO);

    ArticleVO getArticleDetail(ArticleDTO articleDTO);

    Page<ArticleVO> getArticleByTenantId(ArticleQuery articleQuery);
}
