package com.microstone.app.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.microstone.app.dto.ArticleDTO;
import com.microstone.app.dto.PlateDTO;
import com.microstone.app.param.GetArticleByUrlParam;
import com.microstone.app.query.ArticleQuery;
import com.microstone.app.util.JsoupUtil;
import com.microstone.app.util.html2json.api.Params;
import com.microstone.app.util.html2json.core.HtmlToJson;
import com.microstone.app.vo.ArticleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

import org.microstone.core.boot.ctrl.MsController;
import org.microstone.core.tool.api.R;
import org.springframework.web.bind.annotation.*;
import com.microstone.app.service.IArticleService;

/**
 * 控制器
 *
 * @author Chalsee
 * @since 2021-05-27
 */
@RestController
@AllArgsConstructor
@RequestMapping("/article")
@Api(value = "文章管理", tags = "接口")
public class ArticleController extends MsController {

    private final IArticleService articleService;

    /**
     * @author XieXiaoDong
     * @date 2021/5/28/0028
     * @description 获取文章列表
     */
    @PostMapping("/getArticleList")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "获取文章列表")
    public R<Page<ArticleVO>> getArticleList(@RequestBody ArticleQuery query) {
        Page<ArticleVO> articleVO = articleService.getArticleList(query);
        return R.data(articleVO);
    }

    /**
     * @author XieXiaoDong
     * @date 2021/5/28/0028
     * @description 删除板块
     */
    @PostMapping("/deleteArticle")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "删除文章")
    public R deleteArticle(@RequestBody ArticleDTO articleDTO) {
        Boolean flag = articleService.deleteArticle(articleDTO);
        return R.data(flag);
    }

    /**
     * @author XieXiaoDong
     * @date 2021/5/28/0028
     * @description 新增文章
     */
    @PostMapping("/saveArticle")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "新增文章")
    public R saveArticle(@RequestBody ArticleDTO articleDTO) {
        return R.data(articleService.saveArticle(articleDTO));
    }

    /**
     * @author XieXiaoDong
     * @date 2021/5/28/0028
     * @description 编辑文章
     */
    @PostMapping("/editArticle")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "编辑文章")
    public R editArticle(@RequestBody ArticleDTO articleDTO) {
        Boolean flag = articleService.editArticle(articleDTO);
        return R.data(flag);
    }

    /**
     * @author XieXiaoDong
     * @date 2021/5/28/0028
     * @description 文章详情页
     */
    @PostMapping("/getArticleDetail")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "文章详情页")
    public R<ArticleVO> getArticleDetail(@RequestBody ArticleDTO articleDTO) {
        return R.data(articleService.getArticleDetail(articleDTO));
    }

    /**
     * @author XieXiaoDong
     * @date 2021/7/1/0001
     * @description 根据租户获取文章
     */
    @PostMapping("/getArticleByTenantId")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "根据租户获取文章")
    public R<Page<ArticleVO>> getArticleByTenantId(@RequestBody ArticleQuery articleQuery) {
        return R.data(articleService.getArticleByTenantId(articleQuery));
    }


    @PostMapping("/getArticleToJsonByUrl")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "根据url获取文章json")
    public R<String> getArticleToJsonByUrl(@RequestBody GetArticleByUrlParam param) throws Exception {
        return R.data(JsoupUtil.getHtmlToJson(param.getUrl()));
    }


    @PostMapping("/getArticleToHtmlByUrl")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "根据url获取文章html")
    public R<String> getArticleToHtmlByUrl(@RequestBody GetArticleByUrlParam param) throws Exception {
        return R.data(JsoupUtil.getHtml(param.getUrl()));
    }


    @PostMapping("/getArticleToJsonByHtml")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "根据url获取文章html")
    public R<String> getArticleToJsonByHtml(@RequestBody GetArticleByUrlParam param) throws Exception {
        Params params = new Params();
        params.setType("html");
        String content = HtmlToJson.by(param.getUrl(), params).get();
        JSONArray objects = JSONObject.parseArray(content);
        String contents = objects.toJSONString();
        return R.data(contents);
    }

}
