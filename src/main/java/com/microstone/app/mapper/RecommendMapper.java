package com.microstone.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.microstone.app.entity.Recommend;
import com.microstone.app.entity.ShareRecord;
import com.microstone.app.query.ArticleQuery;
import com.microstone.app.vo.RecommendVO;
import com.microstone.app.vo.RecommendsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author XieXiaoDong
 * @date 2021/6/21/0021
 * @description 方法描述
 */
public interface RecommendMapper extends BaseMapper<Recommend> {

    List<RecommendsVO> getRecommendList(IPage page);

    List<RecommendVO> getAppRecommend(Page<RecommendVO> page,@Param("articleQuery") ArticleQuery articleQuery);

    List<RecommendVO> getAllRelation();

}
