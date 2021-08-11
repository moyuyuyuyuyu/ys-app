package com.microstone.app.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.microstone.app.constant.PlateEnum;
import com.microstone.app.dto.RecommendDTO;
import com.microstone.app.entity.*;
import com.microstone.app.mapper.PlateMapper;
import com.microstone.app.mapper.RecommendMapper;
import com.microstone.app.query.ArticleQuery;
import com.microstone.app.service.*;
import com.microstone.app.vo.RecommendVO;
import com.microstone.app.vo.RecommendsVO;
import com.microstone.system.cache.UserCache;
import com.microstone.system.entity.User;
import org.microstone.core.mp.base.BaseServiceImpl;
import org.microstone.core.mp.support.Query;
import org.microstone.core.tool.utils.Func;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 服务实现类
 *
 * @author Chalsee
 * @since 2021-05-27
 */
@Service
public class RecommendServiceImpl extends BaseServiceImpl<RecommendMapper, Recommend> implements IRecommendService {

    @Resource
    private PlateMapper plateMapper;

    @Autowired
    private ILabelShowService labelShowService;

    @Autowired
    private IReadRecordService readRecordService;

    @Autowired
    private IWechatUserService wechatUserService;

    @Autowired
    private IArticleService articleService;

    @Autowired
    private IVideoService videoService;

    @Autowired
    private IDocumentService documentService;

    @Autowired
    private IPosterService posterService;

    @Autowired
    private IShareRecordService shareRecordService;

    /**
     * @author XieXiaoDong
     * @date 2021/6/21/0021
     * @description 获取推荐列表
     */
    @Override
    public Page<RecommendsVO> getRecommendList(Query query) {
        Page<RecommendsVO> page = new Page<>(query.getCurrent(), query.getSize());
        List<RecommendsVO> recommendList = baseMapper.getRecommendList(page);
        List<RecommendVO> relations = baseMapper.getAllRelation();

        for (RecommendsVO recommendsVO : recommendList) {
            RecommendVO recommendVO = relations.stream().filter(p -> Func.equals(p.getId(), recommendsVO.getNewsId())).collect(Collectors.toList()).get(0);
            recommendsVO.setHasTop(recommendVO.getHasTop());
            User user = UserCache.getUser(recommendsVO.getUpdateUser());
            if (!Func.isNull(user)) {
                recommendsVO.setUpdateUserName(user.getRealName());
            }
        }
        return page.setRecords(recommendList);
    }

    /**
     * @author XieXiaoDong
     * @date 2021/6/21/0021
     * @description 添加推荐
     */
    @Override
    public Boolean saveRecommend(RecommendDTO recommendDTO) {
        Recommend recommend = new Recommend();
        BeanUtils.copyProperties(recommendDTO, recommend);
        return this.saveOrUpdate(recommend);
    }

    /**
     * @author XieXiaoDong
     * @date 2021/6/21/0021
     * @description 删除推荐
     */
    @Override
    public Boolean deleteRecommend(RecommendDTO recommendDTO) {
        return this.removeById(recommendDTO.getId());
    }

    /**
     * @author XieXiaoDong
     * @date 2021/6/21/0021
     * @description 获取app推荐
     */
    @Override
    public Page<RecommendVO> getAppRecommend(ArticleQuery articleQuery) {
        Page<RecommendVO> page = new Page<>(articleQuery.getCurrent(), articleQuery.getSize());
        //获取推荐列表
        Query query = new Query();
        query.setCurrent(-1);
        query.setSize(-1);
        List<RecommendsVO> records = getRecommendList(query).getRecords();

        List<LabelShow> list = labelShowService.list(new LambdaQueryWrapper<LabelShow>().eq(LabelShow::getIsDeleted, 0));


        List<Long> newsIds = records.stream().map(RecommendsVO::getNewsId).collect(Collectors.toList());
//       List<Long> plateIds = list.stream().map(LabelShow::getPlateId).collect(Collectors.toList());
        articleQuery.setIds(newsIds);
//        articleQuery.setPlateIds(plateIds);
        List<RecommendVO> recommendVOS = baseMapper.getAppRecommend(page, articleQuery);

        List<ReadRecord> readRecords = readRecordService.list(new LambdaQueryWrapper<ReadRecord>().eq(ReadRecord::getIsDeleted, 0).orderByDesc(ReadRecord::getCreateTime));
        List<ShareRecord> shareRecords = shareRecordService.list(new LambdaQueryWrapper<ShareRecord>().eq(ShareRecord::getIsDeleted, 0).orderByDesc(ShareRecord::getCreateTime));

        List<WechatUser> wechatUsers = wechatUserService.list(new LambdaQueryWrapper<WechatUser>().eq(WechatUser::getIsDeleted, 0));


        for (RecommendVO recommendVO : recommendVOS) {
            List<ReadRecord> readRecord = readRecords.stream().filter(p -> Func.equals(p.getRelationId(), recommendVO.getId())).collect(Collectors.toList());
            List<ShareRecord> shareRecordList = shareRecords.stream().filter(p -> Func.equals(p.getRelationId(), recommendVO.getId())).collect(Collectors.toList());
            List<ReadRecord> readRecord1 = readRecord.stream().limit(3).collect(Collectors.toList());
            List<Long> readUserIds = readRecord1.stream().map(ReadRecord::getReadUserId).collect(Collectors.toList());
            List<WechatUser> wechatUsers1 = wechatUsers.stream().filter(p -> readUserIds.contains(p.getId())).collect(Collectors.toList());
            List<String> headImage = wechatUsers1.stream().map(WechatUser::getHeadImage).collect(Collectors.toList());
            recommendVO.setReadCount(readRecord.size());
            recommendVO.setShareCount(shareRecordList.size());
            recommendVO.setHeadImages(headImage);
        }


        return page.setRecords(recommendVOS);
    }

    /**
     * @author XieXiaoDong
     * @date 2021/6/22/0022
     * @description 编辑推荐名称
     */
    @Override
    public Boolean editName(RecommendDTO recommendDTO) {
        List<Recommend> recommends = baseMapper.selectList(new LambdaQueryWrapper<Recommend>().eq(Recommend::getIsDeleted, 0));
        for (Recommend recommend : recommends) {
            recommend.setName(recommend.getName());
        }
        return this.saveOrUpdateBatch(recommends);
    }

    /**
     * @author XieXiaoDong
     * @date 2021/7/1/0001
     * @description 置顶
     */
    @Override
    public Boolean hasTop(RecommendDTO recommendDTO) {
        Recommend recommend = baseMapper.selectById(recommendDTO.getId());
        List<RecommendVO> recommendVOS = baseMapper.getAllRelation();
        RecommendVO recommendVO1 = recommendVOS.stream().filter(p -> Func.equals(p.getId(), recommend.getNewsId())).collect(Collectors.toList()).get(0);
        if (Func.equals(recommendVO1.getType(), PlateEnum.ARTICLE.getCode())) {
            Article article = new Article();
            article.setId(recommend.getNewsId());
            article.setHasTop(recommendDTO.getHasTop());
            return articleService.saveOrUpdate(article);
        }
        if (Func.equals(recommendVO1.getType(), PlateEnum.VIDEO.getCode())) {
            Video video = new Video();
            video.setId(recommend.getNewsId());
            video.setHasTop(recommendDTO.getHasTop());
            return videoService.saveOrUpdate(video);
        }
        if (Func.equals(recommendVO1.getType(), PlateEnum.DOCUMENT.getCode())) {
            Document document = new Document();
            document.setId(recommend.getNewsId());
            document.setHasTop(recommendDTO.getHasTop());
            return documentService.saveOrUpdate(document);
        }
        if (Func.equals(recommendVO1.getType(), PlateEnum.POSTER.getCode())) {
            Poster poster = new Poster();
            poster.setId(recommend.getNewsId());
            poster.setHasTop(recommendDTO.getHasTop());
            return posterService.saveOrUpdate(poster);
        }
        return false;
    }
}
