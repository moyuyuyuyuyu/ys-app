package com.microstone.app.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.microstone.app.dto.DocumentDTO;
import com.microstone.app.dto.PlateDTO;
import com.microstone.app.entity.Plate;
import com.microstone.app.query.ArticleQuery;
import com.microstone.app.vo.DocumentVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

import org.microstone.core.boot.ctrl.MsController;
import org.microstone.core.tool.api.R;
import org.springframework.web.bind.annotation.*;
import com.microstone.app.service.IDocumentService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *  控制器
 *
 * @author Chalsee
 * @since 2021-05-27
 */
@RestController
@AllArgsConstructor
@RequestMapping("/document")
@Api(value = "", tags = "接口")
public class DocumentController extends MsController {

	private final IDocumentService documentService;

	/**
	 * @author XieXiaoDong
	 * @date 2021/5/31/0031
	 * @description 删除文档
	 */
	@PostMapping("/deleteDocument")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "删除文档")
	public R deleteDocument(@RequestBody DocumentDTO documentDTO) {

		return R.status(documentService.deleteDocument(documentDTO));
	}


	/**
	 * @author XieXiaoDong
	 * @date 2021/5/31/0031
	 * @description 获取文档
	 */
	@PostMapping("/getDocument")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "获取文档")
	public R<Page<DocumentVO>> getDocument(@RequestBody ArticleQuery articleQuery) {
		return R.data(documentService.getDocument(articleQuery));
	}


	@PostMapping("/saveDocument")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "新增/编辑 文档")
	public R saveDocument(HttpServletRequest request, @RequestBody DocumentDTO documentDTO) throws Exception {
		return R.status(documentService.saveDocument(request,documentDTO));
	}


	@PostMapping("/getDocumentDetail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "文档详情")
	public R<DocumentVO> getDocumentDetail(@RequestBody DocumentDTO documentDTO) {
		return R.data(documentService.getDocumentDetail(documentDTO));
	}


	@PostMapping("/getDocumentByTenantId")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "根据租户id获取文档")
	public R<Page<DocumentVO>> getDocumentByTenantId(@RequestBody ArticleQuery articleQuery) {
		return R.data(documentService.getDocumentByTenantId(articleQuery));
	}
}
