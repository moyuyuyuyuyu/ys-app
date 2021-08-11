package com.microstone.app.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.microstone.app.dto.TokenDTO;
import com.microstone.app.entity.WechatOfficialAccount;
import com.microstone.app.mapper.WechatOfficialAccountMapper;
import com.microstone.app.query.GetUnlimitedQuery;
import com.microstone.app.query.WxArticleQuery;
import com.microstone.app.util.*;
import com.microstone.app.query.WXQuery;
import com.microstone.app.vo.TokenVO;
import com.microstone.app.vo.WXContentVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.microstone.core.cache.utils.CacheUtil;
import org.microstone.core.secure.utils.AuthUtil;
import org.microstone.core.tool.api.R;
import org.microstone.core.tool.utils.Func;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;



/**
 * @author XieXiaoDong
 * @date 2021/5/26/0026
 * @description 获取微信公众号
 */
@RestController
@AllArgsConstructor
@RequestMapping("/getWXtContent")
@Api(value = "", tags = "接口")
public class WXContentController {
    private static Logger logger = LoggerFactory.getLogger(WXContentController.class);
    @Resource
    private WechatOfficialAccountMapper wechatOfficialAccountMapper;

    public static final String key = "access_token";
    public static final String path = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";
    public static final String tokenPath = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=";

    @PostMapping("/getContentList")
    @ApiOperation("获取公众号图文 文章")
    public R<WXContentVO> getContentList(@RequestBody WXQuery query) throws IOException {

        /**
         * {
         *    "total_count": TOTAL_COUNT,
         *    "item_count": ITEM_COUNT,
         *    "item": [{
         *        "media_id": MEDIA_ID,
         *        "content": {
         *            "news_item": [{
         *                "title": TITLE,
         *                "thumb_media_id": THUMB_MEDIA_ID,
         *                "show_cover_pic": SHOW_COVER_PIC(0 / 1),
         *                "author": AUTHOR,
         *                "digest": DIGEST,
         *                "content": CONTENT,
         *                "url": URL,
         *                "content_source_url": CONTETN_SOURCE_URL
         *            },
         *            //多图文消息会在此处有多篇文章
         *            ]
         *         },
         *         "update_time": UPDATE_TIME
         *     },
         *     //可能有多个图文消息item结构
         *   ]
         * }
         */
        WXContentVO wxContentVO = new WXContentVO();
        List<WXContents> wxContentsList = new ArrayList<>();
        String tenantId = AuthUtil.getTenantId();
        WechatOfficialAccount wechatOfficialAccount = wechatOfficialAccountMapper.selectOne(new LambdaQueryWrapper<WechatOfficialAccount>().eq(WechatOfficialAccount::getTenantId, tenantId).eq(WechatOfficialAccount::getIsDeleted, 0));

        //获取token缓存
        String cacheToken = CacheToken.getCacheToken(path,wechatOfficialAccount.getAppid(),wechatOfficialAccount.getSecret());
        String result2 = GetContent.getContentList(tokenPath,cacheToken,query);
        JSONObject jsonObject = JSONObject.parseObject(result2);
        if(Func.equals(jsonObject.getString("errcode"),"42001") || Func.equals(jsonObject.getString("errcode"),"40001")) {
            CacheUtil.clear("ms:app");
            String result1 = GetWXToken.getToken(path,wechatOfficialAccount.getAppid(),wechatOfficialAccount.getSecret());
            Map<String,Object> token = (Map<String, Object>) JSON.parseObject(result1);
            String accessToken = token.get("access_token").toString();
            result2 = GetContent.getContentList(tokenPath,accessToken,query);
            jsonObject = JSONObject.parseObject(result2);
            CacheUtil.put("ms:app", "accessToken:id:", "access_token", accessToken);
        }
        if(Func.equals(jsonObject.getString("errcode"),"45009")) throw new SecurityException("微信接口调用已达本日上限");
        Integer totalCount = jsonObject.getInteger("total_count");
        wxContentVO.setTotalCount(totalCount);
        JSONArray item = jsonObject.getJSONArray("item");
        for (int i = 0; i < item.size(); i++) {
            WXContents wxContents = new WXContents();
            JSONObject content = item.getJSONObject(i).getJSONObject("content");
            String mediaId = item.getJSONObject(i).getString("media_id");
            wxContents.setMediaId(mediaId);
            JSONArray newsItem = content.getJSONArray("news_item");
            List<NewsItems> newsItemsList = new ArrayList<>();
            for (int j = 0; j < newsItem.size(); j++) {
                NewsItems newsItems = new NewsItems();
                newsItems.setUpdateTime(item.getJSONObject(i).getString("update_time"));
                newsItems.setThumbUrl(newsItem.getJSONObject(j).getString("thumb_url"));
                newsItems.setThumbMediaId(newsItem.getJSONObject(j).getString("thumb_media_id"));
                newsItems.setAuthor(newsItem.getJSONObject(j).getString("author"));
                newsItems.setOnlyFansCanComment(newsItem.getJSONObject(j).getInteger("only_fans_can_comment"));
                newsItems.setDigest(newsItem.getJSONObject(j).getString("digest"));
                newsItems.setShowCoverPic(newsItem.getJSONObject(j).getInteger("show_cover_pic"));
                newsItems.setContentSourceUrl(newsItem.getJSONObject(j).getString("content_source_url"));
                newsItems.setNeedOpenComment(newsItem.getJSONObject(j).getInteger("need_open_comment"));
                newsItems.setTitle(newsItem.getJSONObject(j).getString("title"));
                newsItems.setContent(newsItem.getJSONObject(j).getString("content"));
                newsItems.setUrl(newsItem.getJSONObject(j).getString("url"));
                String title = newsItem.getJSONObject(j).getString("title");
                if(!Func.isNull(query.getKeyword()) && !Func.isEmpty(query.getKeyword())){
                    if(title.contains(query.getKeyword())){
                        newsItemsList.add(newsItems);
                        wxContentVO.setTotalCount(newsItemsList.size());
                    }
                }else {
                    newsItemsList.add(newsItems);
                }
                wxContents.setNewsItems(newsItemsList);
                if(!Func.isNull(wxContents.getNewsItems()) && !Func.isEmpty(wxContents.getNewsItems())){
                    wxContentsList.add(wxContents);
                }
            }
        }

        wxContentVO.setItem(wxContentsList);
        return R.data(wxContentVO);
    }


    @PostMapping("/getUnlimited")
    @ApiOperation("获取二维码")
    public R<String> getUnlimited(@RequestBody GetUnlimitedQuery query) throws Exception{
        QrCodeUtil util = new QrCodeUtil();
        String res = util.getUnlimited(query.getScene());
        return R.data(res);
    }


    @PostMapping("/getToken")
    @ApiOperation("检测账号是否能获取到token")
    public R<TokenVO> getToken(@RequestBody TokenDTO tokenDTO) throws IOException {
        CacheUtil.clear("ms:app");
        TokenVO tokenVO = new TokenVO();
        String appid = tokenDTO.getAppid();
        String secret = tokenDTO.getSecret();
        String path = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";
        String token = GetWXToken.getToken(path, appid, secret);
        Map<String,Object> tokenMap = (Map<String, Object>) JSON.parseObject(token);
        logger.info("tokenMap1：" + tokenMap);
        if(!Func.isNull(tokenMap.get("errcode"))){
            CacheUtil.clear("ms:app");
            logger.info("tokenMap2：" + tokenMap);
            if(Func.equals(tokenMap.get("errcode").toString(),"40001")){
                throw new SecurityException("AppSecret错误或者AppSecret不属于这个公众号，确认AppSecret的正确性");
            }
            if(Func.equals(tokenMap.get("errcode").toString(),"40125")){
                throw new SecurityException("AppSecret错误或者AppSecret不属于这个公众号，确认AppSecret的正确性");
            }
            if(Func.equals(tokenMap.get("errcode").toString(),"40164")){
                throw new SecurityException("调用接口的IP地址不在白名单中，请在接口IP白名单中进行设置。");
            }
            if(Func.equals(tokenMap.get("errcode").toString(),"89503")){
                throw new SecurityException("此IP调用需要管理员确认,请联系管理员");
            }
            if(Func.equals(tokenMap.get("errcode").toString(),"89501")){
                throw new SecurityException("此IP正在等待管理员确认,请联系管理员");
            }
            if(Func.equals(tokenMap.get("errcode").toString(),"89506")){
                throw new SecurityException("24小时内该IP被管理员拒绝调用两次，24小时内不可再使用该IP调用");
            }
            if(Func.equals(tokenMap.get("errcode").toString(),"89507")){
                throw new SecurityException("1小时内该IP被管理员拒绝调用一次，1小时内不可再使用该IP调用");
            }
            if(Func.equals(tokenMap.get("errcode").toString(),"40013")){
                throw new SecurityException("不合法的AppID，请开发者检查AppID的正确性，避免异常字符，注意大小写");
            }
        }
        if(!Func.isNull(tokenMap.get("access_token"))){
            tokenVO.setFlag(true);
            CacheUtil.clear("ms:app");
            String accessToken = tokenMap.get("access_token").toString();
            CacheUtil.put("ms:app", "accessToken:id:", "access_token", accessToken);
        }

        return R.data(tokenVO);
    }



//    @PostMapping("/getWxArticleByLink")
//    @ApiOperation("获取微信文章")
//    public R<Resp<JSONObject>> getWxArticleByLink(@RequestBody WxArticleQuery query)  {
//         return R.data(getActicle(query.getLink()));
//    }


}
