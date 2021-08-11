package com.microstone.app.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.microstone.app.dto.RecommendDTO;
import com.microstone.app.entity.LabelShow;
import com.microstone.app.entity.Recommend;
import com.microstone.app.query.ArticleQuery;
import com.microstone.app.vo.RecommendVO;
import com.microstone.app.vo.RecommendsVO;
import org.microstone.core.mp.base.BaseService;
import org.microstone.core.mp.support.Query;

/**
 * @author XieXiaoDong
 * @date 2021/6/21/0021
 * @description 方法描述
 */
public interface IRecommendService extends BaseService<Recommend> {

    Page<RecommendsVO> getRecommendList(Query query);

    Boolean saveRecommend(RecommendDTO recommendDTO);

    Boolean deleteRecommend(RecommendDTO recommendDTO);

    Page<RecommendVO> getAppRecommend(ArticleQuery articleQuery);

    Boolean editName(RecommendDTO recommendDTO);

    Boolean hasTop(RecommendDTO recommendDTO);
}
