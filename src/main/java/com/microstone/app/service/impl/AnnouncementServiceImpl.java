package com.microstone.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.microstone.app.dto.AnnouncementDTO;
import com.microstone.app.entity.Announcement;
import com.microstone.app.entity.User;
import com.microstone.app.mapper.AnnouncementMapper;
import com.microstone.app.mapper.UserMapper;
import com.microstone.app.query.ArticleQuery;
import com.microstone.app.service.IAnnouncementService;
import com.microstone.app.service.IMessageRecordService;
import com.microstone.app.service.IUserService;
import com.microstone.app.vo.AnnouncementVO;
import org.microstone.core.mp.base.BaseEntity;
import org.microstone.core.mp.base.BaseServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  服务实现类
 *
 * @author Chalsee
 * @since 2021-05-27
 */
@Service
public class AnnouncementServiceImpl extends BaseServiceImpl<AnnouncementMapper, Announcement> implements IAnnouncementService {

    @Autowired
    private IMessageRecordService messageRecordService;

    @Resource
    private UserMapper userMapper;

	@Override
	public IPage<AnnouncementVO> selectAnnouncementPage(IPage<AnnouncementVO> page, AnnouncementVO announcement) {
		return page.setRecords(baseMapper.selectAnnouncementPage(page, announcement));
	}


    @Override
    public Page<AnnouncementVO> getAnnouncement(ArticleQuery articleQuery) {
        Page<AnnouncementVO> page = new Page<>(articleQuery.getCurrent(),articleQuery.getSize());
        List<AnnouncementVO> list = baseMapper.getAnnouncement(page,articleQuery);
        return page.setRecords(list);
    }

    @Override
    public Boolean editAnnouncement(AnnouncementDTO announcementDTO) {
        Announcement announcement = new Announcement();
        BeanUtils.copyProperties(announcementDTO,announcement);
        //用户id
        List<Long> userId = userMapper.selectList(new LambdaQueryWrapper<User>().eq(User::getIsDeleted, 0)).stream().map(User::getId).collect(Collectors.toList());
        //todo 推送公告，没有appKey，暂时注释
        messageRecordService.pushMessage(announcementDTO.getTitle(),announcementDTO.getContent(),userId);
        return this.saveOrUpdate(announcement);

    }

    @Override
    public Boolean deleteAnnouncement(AnnouncementDTO announcementDTO) {
        return this.removeById(announcementDTO.getId());
    }

    @Override
    public AnnouncementVO getAnnouncementDetail(AnnouncementDTO announcementDTO) {
        Announcement announcement = baseMapper.selectOne(new LambdaQueryWrapper<Announcement>().eq(Announcement::getIsDeleted, 0).eq(Announcement::getId,announcementDTO.getId()));
        AnnouncementVO announcementVO = new AnnouncementVO();
        BeanUtils.copyProperties(announcement,announcementVO);
        return announcementVO;
    }

}
