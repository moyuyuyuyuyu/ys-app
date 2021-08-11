package com.microstone.app.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.microstone.app.dto.ArticleDTO;
import com.microstone.app.entity.Article;
import com.microstone.app.mapper.ArticleMapper;
import com.microstone.app.query.ArticleQuery;
import com.microstone.app.service.IArticleService;
import com.microstone.app.service.IPlateService;
import com.microstone.app.util.html2json.api.Params;
import com.microstone.app.util.html2json.core.HtmlToJson;
import com.microstone.app.vo.ArticleVO;
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
public class ArticleServiceImpl extends BaseServiceImpl<ArticleMapper, Article> implements IArticleService {

	@Autowired
	private IPlateService plateService;

	@Override
	public IPage<ArticleVO> selectArticlePage(IPage<ArticleVO> page, ArticleVO article) {
		return page.setRecords(baseMapper.selectArticlePage(page, article));
	}

	/**
	 * @author XieXiaoDong
	 * @date 2021/5/28/0028
	 * @description 获取文章列表
	 */
    @Override
    public Page<ArticleVO> getArticleList(ArticleQuery query) {
        Page<ArticleVO> page = new Page<>(query.getCurrent(),query.getSize());
        List<ArticleVO> articleVOList = baseMapper.getArticleList(page,query);
        return page.setRecords(articleVOList);
    }

    /**
     * @author XieXiaoDong
     * @date 2021/5/28/0028
     * @description 删除文章
     */
	@Override
	public Boolean deleteArticle(ArticleDTO articleDTO) {
		plateService.generalDelete(articleDTO.getId());
		return this.removeById(articleDTO.getId());
	}

	/**
	 * @author XieXiaoDong
	 * @date 2021/5/28/0028
	 * @description 新增文章
	 */
	@Override
	public Long saveArticle(ArticleDTO articleDTO) {
		Article article = new Article();
		Params params=new Params();
		params.setType("html");
		BeanUtils.copyProperties(articleDTO,article);
		String content = HtmlToJson.by(articleDTO.getContent(),params).get();
		JSONArray objects = JSONObject.parseArray(content);
		String contents = objects.toJSONString();
		article.setHtmlToJson(contents);
		String replace = articleDTO.getContent().replace("data-src", "src");
		article.setContent(replace);
		 this.save(article);
		 return article.getId();
	}

	/**
	 * @author XieXiaoDong
	 * @date 2021/5/28/0028
	 * @description 编辑文章
	 */
	@Override
	public Boolean editArticle(ArticleDTO articleDTO) {
		Article article = this.getById(articleDTO.getId());
		article.setRecommendReason(articleDTO.getRecommendReason());
		article.setIsRecommend(articleDTO.getIsRecommend());
		article.setSort(articleDTO.getSort());
		article.setId(articleDTO.getId());
		article.setTitle(articleDTO.getTitle());
		article.setAuthor(articleDTO.getAuthor());
		article.setPublishDate(articleDTO.getPublishDate());
		article.setDigest(articleDTO.getDigest());
		article.setPlateId(articleDTO.getPlateId());
		Params params=new Params();
		params.setType("html");
		String content = HtmlToJson.by(article.getContent(),params).get();
		JSONArray objects = JSONObject.parseArray(content);
		String contents = objects.toJSONString();
		article.setHtmlToJson(contents);

		String replace = article.getContent().replace("data-src", "src");
		article.setContent(replace);

		return this.updateById(article);
	}

	/**
	 * @author XieXiaoDong
	 * @date 2021/6/30/0030
	 * @description 文章详情页
	 */
    @Override
    public ArticleVO getArticleDetail(ArticleDTO articleDTO) {
    	Article article = baseMapper.selectById(articleDTO.getId());
		Integer readingQuantity = article.getReadingQuantity();
		article.setReadingQuantity(++readingQuantity);
		this.updateById(article);

		ArticleVO articleVO = new ArticleVO();
		BeanUtils.copyProperties(article,articleVO);
		return articleVO;
    }

    /**
     * @author XieXiaoDong
     * @date 2021/7/1/0001
     * @description 根据租户获取文章
     */
	@Override
	public Page<ArticleVO> getArticleByTenantId(ArticleQuery articleQuery) {
		Page<ArticleVO> page = new Page<>(articleQuery.getCurrent(),articleQuery.getSize());
		List<ArticleVO> list = baseMapper.getArticleByTenantId(page,articleQuery);
		return page.setRecords(list);
	}

}
