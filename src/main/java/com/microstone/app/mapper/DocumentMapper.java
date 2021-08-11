package com.microstone.app.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.microstone.app.entity.Document;
import com.microstone.app.query.ArticleQuery;
import com.microstone.app.vo.DocumentVO;
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
public interface DocumentMapper extends BaseMapper<Document> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param document
	 * @return
	 */
	List<DocumentVO> selectDocumentPage(IPage page, DocumentVO document);

    List<DocumentVO> getDocument(Page<DocumentVO> page, @Param("query") ArticleQuery query);

    List<DocumentVO> getDocumentByTenantId(Page<DocumentVO> page,@Param("articleQuery") ArticleQuery articleQuery);
}
