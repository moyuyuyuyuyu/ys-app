package com.microstone.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.microstone.app.dto.ExportShareRecordListDTO;
import com.microstone.app.dto.GetShareRecordPageListDTO;
import com.microstone.app.dto.HomePageShareCountInfoDTO;
import com.microstone.app.dto.ImportProductExcel;
import com.microstone.app.dto.ShareCountInfoDTO;
import com.microstone.app.entity.ReadRecord;
import com.microstone.app.entity.ShareRecord;
import com.microstone.app.entity.User;
import com.microstone.app.entity.WechatUser;
import com.microstone.app.param.GetShareCountInfoParam;
import com.microstone.app.param.GetShareRecordByCodeParam;
import com.microstone.app.param.GetShareRecordForAppPageListParam;
import com.microstone.app.param.GetShareRecordPageListParam;
import com.microstone.app.param.ShareInfoParam;
import com.microstone.app.service.IReadRecordService;
import com.microstone.app.service.IUserService;
import com.microstone.app.service.IWechatUserService;
import com.microstone.app.mapper.ShareRecordMapper;
import com.microstone.app.service.IShareRecordService;
import org.microstone.core.excel.util.ExcelUtil;
import org.microstone.core.mp.base.BaseServiceImpl;
import org.microstone.core.mp.support.Condition;
import org.microstone.core.secure.utils.AuthUtil;
import org.microstone.core.tool.utils.DateUtil;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.microstone.system.cache.UserCache;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 服务实现类
 *
 * @author Chalsee
 * @since 2021-05-27
 */
@Service
public class ShareRecordServiceImpl extends BaseServiceImpl<ShareRecordMapper, ShareRecord> implements IShareRecordService {


    @Resource
    private IWechatUserService wechatUserService;

    @Resource
    @Lazy
    private IReadRecordService readRecordService;

    @Resource
    @Lazy
    private IUserService userService;

    /**
     * 分享信息
     */
    @Override
    public ShareRecord shareInfo(ShareInfoParam param) {
        ShareRecord record = new ShareRecord();
        record.setRelationId(param.getRelationId());
        record.setType(param.getType());
        record.setShareDate(new Date());
        record.setShareCode(param.getShareCode());
        record.setShareCount(0);
        record.setReadCount(0);
        record.setReadUserCount(0);
        this.saveOrUpdate(record);
        if (param.getParentId() == null) {
            record.setShareUserId(AuthUtil.getAppUserId());
            record.setParentId(record.getId());
            record.setRootId(record.getId());
            record.setShareType(1);
        } else {
            ShareRecord parent = this.getById(param.getParentId());
            parent.setShareCount(parent.getShareCount() + 1);
            this.saveOrUpdate(parent);
            record.setParentId(param.getParentId());
            record.setRootId(parent.getRootId());
            record.setShareType(parent.getShareType() + 1);
            WechatUser user = wechatUserService.getOne(new LambdaQueryWrapper<WechatUser>().eq(WechatUser::getOpenId, param.getOpenId()));
            record.setShareUserId(user.getId());
        }
        this.saveOrUpdate(record);
        return record;
    }

    /**
     * 获取分享code
     */
    @Override
    public ShareRecord getShareRecordByCode(GetShareRecordByCodeParam param) {
        ShareRecord record = this.getOne(new LambdaQueryWrapper<ShareRecord>().eq(ShareRecord::getShareCode, param.getShareCode()));
        return record;
    }


    /**
     * 分享
     */
    @Override
    public IPage<GetShareRecordPageListDTO> getShareRecordPageList(GetShareRecordPageListParam param) {
        IPage<GetShareRecordPageListDTO> page = Condition.getPage(param);
        page = baseMapper.getShareRecordPageList(page, param);
        List<GetShareRecordPageListDTO> list = page.getRecords();
        for (GetShareRecordPageListDTO item : list) {
            if (item.getShareType() == 1) {
                User user = userService.getById(item.getShareUserId());
                item.setShareUserName(user.getName());
                item.setMobile(user.getMobilePhone());
                item.setHeadImage(user.getHeadIcon());
            } else {
                WechatUser wu = wechatUserService.getById(item.getShareUserId());
                item.setShareUserName(wu.getName());
                item.setMobile(wu.getPhone());
                item.setHeadImage(wu.getHeadImage());
            }
        }
        page.setRecords(list);
        return page;
    }


    /**
     * 分享
     */
    @Override
    public void exportShareRecordPageList(GetShareRecordPageListParam param, HttpServletResponse response) {
        List<GetShareRecordPageListDTO> list = baseMapper.getShareRecordPageList(param);
        List<ExportShareRecordListDTO> res = new ArrayList<>();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        for (GetShareRecordPageListDTO item : list) {
            ExportShareRecordListDTO dto = new ExportShareRecordListDTO();
            dto.setTitle(item.getTitle());
            if (item.getType() == 1) {
                dto.setType("文章");
            }
            if (item.getType() == 2) {
                dto.setType("视频");
            }
            if (item.getType() == 3) {
                dto.setType("文档");
            }
            if (item.getType() == 4) {
                dto.setType("海报");
            }
            dto.setShareDate(f.format(item.getShareDate()));
            dto.setShareType(item.getShareType() == 1 ? "首次" : "二次");
            dto.setShareCount(item.getShareCount());
            dto.setReadCount(item.getReadCount());
            dto.setReadUserCount(item.getReadUserCount());
            if (item.getShareType() == 1) {
                User user = userService.getById(item.getShareUserId());
                dto.setShareUserName(user.getName());
            } else {
                WechatUser wu = wechatUserService.getById(item.getShareUserId());
                dto.setShareUserName(wu.getName());
            }
            res.add(dto);
        }
        ExcelUtil.export(response, res, ExportShareRecordListDTO.class);
    }


    /**
     * 获取App分享列表
     */
    @Override
    public IPage<GetShareRecordPageListDTO> getShareRecordForAppPageList(GetShareRecordForAppPageListParam param) {
        IPage<GetShareRecordPageListDTO> page = Condition.getPage(param);
        param.setUserId(AuthUtil.getAppUserId());
        page = baseMapper.getShareRecordForAppPageList(page, param);
        List<GetShareRecordPageListDTO> list = page.getRecords();
        page.setRecords(list);
        return page;
    }


    /**
     * 获取获客统计
     */
    @Override
    public ShareCountInfoDTO getShareCountInfo(GetShareCountInfoParam param) {
        ShareCountInfoDTO res = new ShareCountInfoDTO();
        LambdaQueryWrapper<ShareRecord> queryWrapper = new LambdaQueryWrapper<ShareRecord>()
                .eq(ShareRecord::getIsDeleted, 0);
        if (param.getApp() != null && param.getApp()) {
            queryWrapper = queryWrapper.eq(ShareRecord::getShareUserId, AuthUtil.getAppUserId());
        }
        List<ShareRecord> recordList = this.list(queryWrapper);
        res.setTotalShareCount(recordList.size());
        if (recordList.isEmpty()) {
            return res;
        }
        List<Long> ids = recordList.stream().map(t -> t.getId()).collect(Collectors.toList());
        List<ReadRecord> readList = readRecordService.list(new LambdaQueryWrapper<ReadRecord>()
                .eq(ReadRecord::getIsDeleted, 0)
                .in(ReadRecord::getShareId, ids));

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int mday = cal.get(Calendar.DAY_OF_MONTH);
        cal.clear();
        cal.set(year, month, mday);
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        int day = cal.get(Calendar.DAY_OF_WEEK);
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        Date thisBeginDate = cal.getTime();
        cal.add(Calendar.DATE, 6);
        Date thisEndDate = cal.getTime();
        Date lastBeginDate = DateUtil.minusDays(thisBeginDate, 7);
        Date lastEndDate = DateUtil.minusDays(thisEndDate, 7);

        List<Long> totalClue = readList.stream().map(t -> t.getReadUserId()).distinct().collect(Collectors.toList());

        List<Long> thisReadList = readList.stream().filter(t -> !t.getReadDate().before(thisBeginDate) && !t.getReadDate().after(thisEndDate)).map(t -> t.getReadUserId()).distinct().collect(Collectors.toList());
        List<Long> lastReadList = readList.stream().filter(t -> !t.getReadDate().before(lastBeginDate) && !t.getReadDate().after(lastEndDate)).map(t -> t.getReadUserId()).distinct().collect(Collectors.toList());

        List<ShareRecord> thisWeekShareList = recordList.stream().filter(t -> !t.getShareDate().before(thisBeginDate) && !t.getShareDate().after(thisEndDate)).collect(Collectors.toList());
        List<ReadRecord> thisWeekReadList = readList.stream().filter(t -> !t.getReadDate().before(thisBeginDate) && !t.getReadDate().after(thisEndDate)).collect(Collectors.toList());

        res.setTotalClueCount(totalClue.size());
        res.setTotalReadCount(readList.size());
        res.setThisWeekClueCount(thisReadList.size());
        res.setIncreaseClueCount(thisReadList.size() - lastReadList.size());
        res.setThisWeekReadCount(thisWeekReadList.size());
        res.setThisWeekShareCount(thisWeekShareList.size());
        return res;
    }


    /**
     * 获取首页统计
     */
    @Override
    public HomePageShareCountInfoDTO getHomePageShareCountInfo() {
        HomePageShareCountInfoDTO res = new HomePageShareCountInfoDTO();
        List<ShareRecord> recordList = this.list(new LambdaQueryWrapper<ShareRecord>()
                .eq(ShareRecord::getIsDeleted, 0)
                .eq(ShareRecord::getShareUserId, AuthUtil.getAppUserId()));
        if (recordList.isEmpty()) {
            return res;
        }
        List<ReadRecord> readList = readRecordService.list(new LambdaQueryWrapper<ReadRecord>()
                .eq(ReadRecord::getIsDeleted, 0)
                .in(ReadRecord::getShareId, recordList.stream().map(t -> t.getId()).collect(Collectors.toList())));
        Calendar cl = Calendar.getInstance();
        int year = cl.get(Calendar.YEAR);
        int month = cl.get(Calendar.MONTH);
        int days = cl.get(Calendar.DAY_OF_MONTH);
        cl.clear();
        cl.set(year, month, days);
        Date today = cl.getTime();
        Integer todayRead = readList.stream().filter(t -> !t.getReadDate().before(today)).map(t -> t.getReadUserId()).distinct().collect(Collectors.toList()).size();
        Integer lastDayRead = readList.stream().filter(t -> !t.getReadDate().before(today)).map(t -> t.getReadUserId()).distinct().collect(Collectors.toList()).size();

        res.setTodayVisitCount(todayRead);
        res.setTodayAddVisitCount(todayRead - lastDayRead);


        cl.setTime(new Date());
        int dayWeek = cl.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cl.add(Calendar.DAY_OF_MONTH, -1);
        }
        cl.setFirstDayOfWeek(Calendar.MONDAY);
        int day = cl.get(Calendar.DAY_OF_WEEK);
        cl.add(Calendar.DATE, cl.getFirstDayOfWeek() - day);
        Date thisBeginDate = cl.getTime();
        cl.add(Calendar.DATE, 6);
        Date thisEndDate = cl.getTime();
        Date lastBeginDate = DateUtil.minusDays(thisBeginDate, 7);
        Date lastEndDate = DateUtil.minusDays(thisEndDate, 7);

        Integer thisWeekShare = recordList.stream().filter(t -> !t.getShareDate().before(thisBeginDate) && !t.getShareDate().after(thisEndDate)).collect(Collectors.toList()).size();
        Integer lastWeekShare = recordList.stream().filter(t -> !t.getShareDate().before(lastBeginDate) && !t.getShareDate().after(lastEndDate)).collect(Collectors.toList()).size();

        res.setThisWeekShareCount(thisWeekShare);
        res.setThisWeekAddShareCount(thisWeekShare - lastWeekShare);


        Integer thisWeekClue = readList.stream().filter(t -> t.getFirstRead().equals(true) && !t.getReadDate().before(thisBeginDate) && !t.getReadDate().after(thisEndDate)).collect(Collectors.toList()).size();
        Integer lastWeekClue = readList.stream().filter(t -> t.getFirstRead().equals(true) && !t.getReadDate().before(lastBeginDate) && !t.getReadDate().after(lastEndDate)).collect(Collectors.toList()).size();

        res.setThisWeekClueCount(thisWeekClue);
        res.setThisWeekAddClueCount(thisWeekClue - lastWeekClue);
        return res;
    }


}
