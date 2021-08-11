package com.microstone.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.microstone.app.entity.Feedback;
import com.microstone.app.query.ArticleQuery;
import com.microstone.app.query.FeedbackQuery;
import com.microstone.app.vo.FeedbackVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 *  Mapper 接口
 *
 * @author Chalsee
 * @since 2021-05-27
 */
public interface FeedbackMapper extends BaseMapper<Feedback> {


    List<FeedbackVO> getFeedback(Page<FeedbackVO> page,@Param("query") FeedbackQuery query);
}
