package com.microstone.app.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.microstone.app.dto.FeedbackDTO;
import com.microstone.app.entity.Feedback;

import com.microstone.app.query.ArticleQuery;
import com.microstone.app.query.FeedbackQuery;
import com.microstone.app.vo.FeedbackVO;
import org.microstone.core.mp.base.BaseService;

/**
 *  服务类
 *
 * @author Chalsee
 * @since 2021-05-27
 */
public interface IFeedbackService extends BaseService<Feedback> {


    Page<FeedbackVO> getFeedback(FeedbackQuery query);

    Boolean submitFeedback(FeedbackDTO feedbackDTO);

    FeedbackVO getFeedbackDetail(FeedbackDTO feedbackDTO);

    Boolean deleteFeedback(FeedbackDTO feedbackDTO);

    Boolean hasContact(FeedbackDTO feedbackDTO);
}
