package com.microstone.app.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.microstone.app.dto.DocumentDTO;
import com.microstone.app.entity.Document;
import com.microstone.app.mapper.DocumentMapper;
import com.microstone.app.query.ArticleQuery;
import com.microstone.app.service.IDocumentService;
import com.microstone.app.service.IPlateService;
import com.microstone.app.util.toImage.ImageValue;
import com.microstone.app.util.toImage.SaveImage;
import com.microstone.app.vo.DocumentVO;
import org.microstone.core.mp.base.BaseServiceImpl;
import org.microstone.core.tool.jackson.JsonUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 服务实现类
 *
 * @author Chalsee
 * @since 2021-05-27
 */
@Service
public class DocumentServiceImpl extends BaseServiceImpl<DocumentMapper, Document> implements IDocumentService {
    @Autowired
    private IPlateService plateService;
    @Override
    public IPage<DocumentVO> selectDocumentPage(IPage<DocumentVO> page, DocumentVO document) {
        return page.setRecords(baseMapper.selectDocumentPage(page, document));
    }

    /**
     * @author XieXiaoDong
     * @date 2021/5/31/0031
     * @description 删除文档
     */
    @Override
    public Boolean deleteDocument(DocumentDTO documentDTO) {
        //验证删除
        plateService.generalDelete(documentDTO.getId());
        return this.removeById(documentDTO.getId());
    }

    /**
     * @author XieXiaoDong
     * @date 2021/5/31/0031
     * @description 获取文档
     */
    @Override
    public Page<DocumentVO> getDocument(ArticleQuery query) {
        Page<DocumentVO> page = new Page<>(query.getCurrent(), query.getSize());
        List<DocumentVO> list = baseMapper.getDocument(page, query);
        return page.setRecords(list);
    }

    /**
     * @author XieXiaoDong
     * @date 2021/5/31/0031
     * @description 新增/编辑 文档
     */
    @Override
    public Boolean saveDocument(HttpServletRequest request,DocumentDTO documentDTO) throws Exception{
        //PDF,DOC,DOCX,PPT,PPTX

        List<ImageValue> imageValues = SaveImage.saveImage(request, documentDTO.getOriginalPath());
        String picturePath = JsonUtil.toJson(imageValues);
        Document document = new Document();
        BeanUtils.copyProperties(documentDTO,document);
        document.setPicturePath(picturePath);
        return this.saveOrUpdate(document);
    }

    /**
     * @author XieXiaoDong
     * @date 2021/6/30/0030
     * @description 文档详情
     */
    @Override
    public DocumentVO getDocumentDetail(DocumentDTO documentDTO) {
        Document document = baseMapper.selectById(documentDTO.getId());
        Integer readingQuantity = document.getReadingQuantity();
        document.setReadingQuantity(++readingQuantity);
        this.updateById(document);

        DocumentVO documentVO = new DocumentVO();
        BeanUtils.copyProperties(document,documentVO);
        return documentVO;
    }

    /**
     * @author XieXiaoDong
     * @date 2021/7/1/0001
     * @description 根据租户id获取文档
     */
    @Override
    public Page<DocumentVO> getDocumentByTenantId(ArticleQuery articleQuery) {
        Page<DocumentVO> page = new Page<>(articleQuery.getCurrent(),articleQuery.getSize());
        List<DocumentVO> list = baseMapper.getDocumentByTenantId(page,articleQuery);
        return page.setRecords(list);
    }

}
