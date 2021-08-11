package com.microstone.app.service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.microstone.app.dto.FeedbackDTO;
import com.microstone.app.entity.AppUserInfo;
import com.microstone.app.entity.Feedback;
import com.microstone.app.mapper.FeedbackMapper;
import com.microstone.app.query.ArticleQuery;
import com.microstone.app.query.FeedbackQuery;
import com.microstone.app.service.IFeedbackService;
import com.microstone.app.service.IUserService;
import com.microstone.app.vo.FeedbackVO;
import io.swagger.annotations.ApiModelProperty;
import org.microstone.core.mp.base.BaseServiceImpl;
import org.microstone.core.secure.utils.AuthUtil;
import org.microstone.core.tool.jackson.JsonUtil;
import org.microstone.core.tool.utils.Func;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 *  服务实现类
 *
 * @author Chalsee
 * @since 2021-05-27
 */
@Service
public class FeedbackServiceImpl extends BaseServiceImpl<FeedbackMapper, Feedback> implements IFeedbackService {


    @Autowired
    private IUserService userService;

    /**
     * 获取意见反馈列表
     * @param query
     * @return
     */
    @Override
    public Page<FeedbackVO> getFeedback(FeedbackQuery query) {
        Page<FeedbackVO> page = new Page<>(query.getCurrent(),query.getSize());
        List<FeedbackVO> list = baseMapper.getFeedback(page,query);
        return page.setRecords(list);
    }

    /**
     * 提交意见反馈
     * @param feedbackDTO
     * @return
     */
    @Override
    public Boolean submitFeedback(FeedbackDTO feedbackDTO) {
        Feedback feedback = new Feedback();
        feedback.setContent(feedbackDTO.getContent());
        Long userId = AuthUtil.getAppUserId();
        AppUserInfo appUserInfo = userService.userInfoById(userId);
        if(Func.isNull(appUserInfo)){
            throw new SecurityException("未获取到当前登录人信息！请退出后重试");
        }
        feedback.setMobile(appUserInfo.getMobilePhone());
        feedback.setPublishDate(new Date());
        feedback.setSubmitter(appUserInfo.getNameUnicode() != null ? appUserInfo.getNameUnicode() : appUserInfo.getName());
        feedback.setSubmitterId(userId);
        if(!Func.isEmpty(feedbackDTO.getAccessorys())){
            feedback.setAccessory(JsonUtil.toJson(feedbackDTO.getAccessorys()));
        }
        return this.save(feedback);
    }

    /**
     * 详情
     * @param feedbackDTO
     * @return
     */
    @Override
    public FeedbackVO getFeedbackDetail(FeedbackDTO feedbackDTO) {
        Feedback fe = this.getById(feedbackDTO.getId());
        FeedbackVO feedbackVO = new FeedbackVO();
        BeanUtils.copyProperties(fe,feedbackVO);
        return feedbackVO;
    }

    /**
     * 删除
     * @param feedbackDTO
     * @return
     */
    @Override
    public Boolean deleteFeedback(FeedbackDTO feedbackDTO) {
        return this.removeById(feedbackDTO.getId());
    }

    /**
     * 设置为已联系
     * @param feedbackDTO
     * @return
     */
    @Override
    public Boolean hasContact(FeedbackDTO feedbackDTO) {
        Feedback feedback = new Feedback();
        feedback.setId(feedbackDTO.getId());
        feedback.setHasContact(true);
        return this.updateById(feedback);
    }
}
