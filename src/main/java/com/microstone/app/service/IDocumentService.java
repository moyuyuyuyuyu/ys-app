package com.microstone.app.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.microstone.app.dto.DocumentDTO;
import com.microstone.app.entity.Document;
import com.microstone.app.query.ArticleQuery;
import com.microstone.app.vo.DocumentVO;
import org.microstone.core.mp.base.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;

import javax.servlet.http.HttpServletRequest;

/**
 *  服务类
 *
 * @author Chalsee
 * @since 2021-05-27
 */
public interface IDocumentService extends BaseService<Document> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param document
	 * @return
	 */
	IPage<DocumentVO> selectDocumentPage(IPage<DocumentVO> page, DocumentVO document);

    Boolean deleteDocument(DocumentDTO documentDTO);

	Page<DocumentVO> getDocument(ArticleQuery articleQuery);

	Boolean saveDocument(HttpServletRequest request,DocumentDTO documentDTO) throws Exception;

    DocumentVO getDocumentDetail(DocumentDTO documentDTO);

    Page<DocumentVO> getDocumentByTenantId(ArticleQuery articleQuery);
}
