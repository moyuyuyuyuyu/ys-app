package com.microstone.app.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.microstone.app.dto.ImportProductExcel;
import com.microstone.app.dto.ProductDTO;
import com.microstone.app.entity.OptionalProduct;
import com.microstone.app.entity.Product;
import com.microstone.app.param.GetOptionalProductPageListParam;
import com.microstone.app.param.GetProductPageListParam;
import com.microstone.app.param.GetProductParam;
import com.microstone.app.param.SetProductStatusParam;
import com.microstone.app.service.IOptionalProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

import org.microstone.core.boot.ctrl.MsController;
import org.microstone.core.excel.util.ExcelUtil;
import org.microstone.core.tool.api.R;
import org.springframework.web.bind.annotation.*;
import com.microstone.app.service.IProductService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *  控制器
 *
 * @author Chalsee
 * @since 2021-05-27
 */
@RestController
@AllArgsConstructor
@RequestMapping("/product")
@Api(value = "", tags = "接口")
public class ProductController extends MsController {

	private final IProductService productService;

	private final IOptionalProductService optionalProductService;



	/**
	 * 保存自选
	 */
	@PostMapping("saveOptionalProduct")
	@ApiOperation(value = "保存自选")
	public R saveOptionalProduct(@RequestBody OptionalProduct dto){
		optionalProductService.saveOptionalProduct(dto);
		return R.status(true);
	}

	/**
	 * 保存产品
	 */
	@PostMapping("saveProduct")
	@ApiOperation(value = "保存产品")
	public R saveProduct(@RequestBody ProductDTO dto){
		productService.saveProduct(dto);
		return R.status(true);
	}

	/**
	 * 保存App产品
	 */
	@PostMapping("saveAppProduct")
	@ApiOperation(value = "保存App产品")
	public R saveAppProduct(@RequestBody ProductDTO dto){
		productService.saveAppProduct(dto);
		return R.status(true);
	}

	/**
	 * 获取产品列表
	 * */
	@PostMapping("getProductPageList")
	@ApiOperation(value = "获取产品列表")
	public R<IPage<ProductDTO>> getProductPageList(@RequestBody GetProductPageListParam param){
		return R.data(productService.getProductPageList(param));
	}

	/**
	 * 获取产品详情
	 * */
	@PostMapping("getProduct")
	@ApiOperation(value = "获取产品详情")
	public R<ProductDTO> getProduct(@RequestBody GetProductParam param){
		return R.data(productService.getProduct(param));
	}

	/**
	 * 设置产品状态
	 * */
	@PostMapping("setProductStatus")
	@ApiOperation(value = "设置产品状态")
	public R setProductStatus(@RequestBody SetProductStatusParam param){
		productService.setProductStatus(param);
		return R.status(true);
	}



	/**
	 * 设置CBS产品状态
	 * */
	@PostMapping("setAppProductStatus")
	@ApiOperation(value = "设置产品状态")
	public R setAppProductStatus(@RequestBody SetProductStatusParam param){
		productService.setAppProductStatus(param);
		return R.status(true);
	}

	/**
	 * 获取自选产品列表
	 * */
	@PostMapping("getOptionalProductPageList")
	@ApiOperation(value = "获取自选产品列表")
	public R<IPage<Product>> getOptionalProductPageList(@RequestBody GetOptionalProductPageListParam param){
		return R.data(productService.getOptionalProductPageList(param));
	}

	/**
	 * 导入产品
	 * */
	@PostMapping("importProduct")
	@ApiOperation(value = "导入产品")
	public R importProduct(@RequestParam("file") MultipartFile file){
		productService.importProduct(file);
		return R.status(true);
	}

	/**
	 * 导入产品
	 * */
	@PostMapping("exportProductTemplate")
	@ApiOperation(value = "导入产品")
	public void exportProductTemplate(HttpServletResponse response){
		ExcelUtil.export(response, new ArrayList<>(), ImportProductExcel.class);
	}

	/**
	 * 同步所有产品
	 * */
	@PostMapping("syncAllProduct")
	public R syncAllProduct(){
		productService.syncAllProduct();
		return R.status(true);
	}
}
