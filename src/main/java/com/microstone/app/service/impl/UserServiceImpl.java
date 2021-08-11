package com.microstone.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.microstone.app.dto.GetUserListDTO;
import com.microstone.app.dto.UserDTO;
import com.microstone.app.entity.Customer;
import com.microstone.app.entity.CustomerGroup;
import com.microstone.app.entity.ReadRecord;
import com.microstone.app.entity.ShareRecord;
import com.microstone.app.entity.AppUserInfo;
import com.microstone.app.entity.WechatUser;
import com.microstone.app.mapper.UserMapper;
import com.microstone.app.param.GetUserInfoParam;
import com.microstone.app.param.GetUserListParam;
import com.microstone.app.service.ICustomerGroupService;
import com.microstone.app.service.ICustomerService;
import com.microstone.app.service.IReadRecordService;
import com.microstone.app.service.IShareRecordService;
import com.microstone.app.service.IUserService;
import com.microstone.app.entity.User;
import com.microstone.app.service.IWechatUserService;
import com.microstone.system.cache.SysCache;
import org.microstone.core.mp.base.BaseServiceImpl;
import org.microstone.core.mp.support.Condition;
import org.microstone.core.secure.utils.AuthUtil;
import org.microstone.core.tool.utils.BeanUtil;
import org.microstone.core.tool.utils.Func;
import org.microstone.core.tool.utils.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 服务实现类
 *
 * @author Chalsee
 * @since 2021-06-07
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements IUserService {


    @Resource
    private ICustomerService customerService;

    @Resource
    private ICustomerGroupService customerGroupService;

    @Lazy
    @Resource
    private IShareRecordService shareRecordService;

    @Lazy
    @Resource
    private IReadRecordService readRecordService;

    @Resource
    private IWechatUserService wechatUserService;

    /**
     * 保存用户信息
     */
    @Override
    public UserDTO saveUser(UserDTO dto) {
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        if (user.getId() == null) {
            user.setUserId(AuthUtil.getUserId());
        }
        this.saveOrUpdate(user);
        GetUserInfoParam param = new GetUserInfoParam();
        param.setAppUserId(user.getId());
        return getUser(param);
    }


    /**
     * 获取用户信息
     *
     * @param account
     * @return
     */
    @Override
    public AppUserInfo userInfo(String account) {

        return baseMapper.getUser(account);
    }

    /**
     * 获取用户信息 不存在则自动创建
     *
     * @param account
     * @return
     */
    @Override
    public AppUserInfo userInfoAutoCreate(String account) {
        AppUserInfo appUserInfo = this.userInfo(account);
        if (Func.isNull(appUserInfo)) {
            User user = new User();
            user.setMobilePhone(account);
            this.saveOrUpdate(user);
            appUserInfo = BeanUtil.copy(user, AppUserInfo.class);
        }
        return appUserInfo;
    }


    @Override
    public AppUserInfo userInfoById(Long appUserId) {
        return baseMapper.getUserById(appUserId);
    }


    /**
     * 获取用户信息
     */
    @Override
    public UserDTO getUser(GetUserInfoParam param) {
        Long userId;
        if (param.getAppUserId() == null) {
            userId = AuthUtil.getAppUserId();
        } else {
            userId = param.getAppUserId();
        }
        User user = this.getOne(new LambdaQueryWrapper<User>().eq(User::getId, userId));
        UserDTO dto = new UserDTO();
        BeanUtils.copyProperties(user, dto);

        List<Customer> customerList = customerService.list(new LambdaQueryWrapper<Customer>().eq(Customer::getIsDeleted, 0).eq(Customer::getUserId, userId));
        dto.setCustomerCount(customerList.size());
        List<Customer> focusCustomerList = customerList.stream().filter(t -> t.getFocus() != null && t.getFocus().equals(true)).collect(Collectors.toList());
        dto.setFocusCustomerCount(focusCustomerList.size());
        List<Customer> dealCustomerList = customerList.stream().filter(t -> t.getCustomerStatus() != null).filter(t -> t.getCustomerStatus().equals(3)).collect(Collectors.toList());
        dto.setDealCustomerCount(dealCustomerList.size());

        List<ShareRecord> recordList = shareRecordService.list(new LambdaQueryWrapper<ShareRecord>()
                .eq(ShareRecord::getIsDeleted, 0)
                .eq(ShareRecord::getShareUserId, userId));
        if (recordList.isEmpty()) {
            dto.setNewCustomerCount(0);
        } else {
            List<ReadRecord> readList = readRecordService.list(new LambdaQueryWrapper<ReadRecord>()
                    .eq(ReadRecord::getIsDeleted, 0)
                    .in(ReadRecord::getShareId, recordList.stream().map(t -> t.getId()).collect(Collectors.toList())));
            if (readList.isEmpty()) {
                dto.setNewCustomerCount(0);
            } else {
/*
                List<Long> readIds = readList.stream().map(t -> t.getReadUserId()).distinct().collect(Collectors.toList());
                List<Long> customer = customerList.stream().filter(t -> t.getWechatId() != null).map(t -> t.getWechatId()).collect(Collectors.toList());
                customer.retainAll(readIds);
                readIds.removeAll(customer);
                dto.setNewCustomerCount(readIds.size());
*/
                List<ReadRecord> rrL = readRecordService.list(new LambdaQueryWrapper<ReadRecord>()
                        .eq(ReadRecord::getShareUserId, AuthUtil.getAppUserId())
                        .eq(ReadRecord::getIsDeleted, 0));
                List<Long> ids = rrL.stream().map(t -> t.getReadUserId()).collect(Collectors.toList());
                if (ids.isEmpty()) {
                    dto.setNewCustomerCount(0);
                } else {
                    List<Long> wechatIds = customerService.list(new LambdaQueryWrapper<Customer>()
                            .isNotNull(Customer::getWechatId)
                            .eq(Customer::getIsDeleted , 0)
                            .eq(Customer::getUserId, AuthUtil.getAppUserId())).stream().map(t -> t.getWechatId()).collect(Collectors.toList());
                    if(wechatIds.isEmpty()){
                        List<WechatUser> users = wechatUserService.list(new LambdaQueryWrapper<WechatUser>().in(WechatUser::getId, ids));
                        dto.setNewCustomerCount(users.size());
                    }else{
                        ids = ids.stream().filter(t -> !wechatIds.contains(t)).collect(Collectors.toList());
                        if(ids.isEmpty()){
                            dto.setNewCustomerCount(0);
                        }else{
                            List<WechatUser> users = wechatUserService.list(new LambdaQueryWrapper<WechatUser>().in(WechatUser::getId, ids));
                            dto.setNewCustomerCount(users.size());
                        }
                    }
                }
            }
        }
        return dto;
    }

    /**
     * 获取用户分页
     */
    public IPage<GetUserListDTO> getUserList(GetUserListParam param) {
        IPage<User> page = Condition.getPage(param);
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();

        if (!StringUtil.isEmpty(param.getMobilePhone())) {
            queryWrapper.like(User::getMobilePhone, param.getMobilePhone());
        }
        if (!StringUtil.isEmpty(param.getName())) {
            queryWrapper.like(User::getName, param.getName());
        }
        IPage<User> res = this.page(page, queryWrapper);
        IPage<GetUserListDTO> result = new Page<>();
        List<GetUserListDTO> userList = new ArrayList<>();
        for (User user : res.getRecords()) {
            GetUserListDTO dto = new GetUserListDTO();
            dto.setId(user.getId());
            dto.setName(user.getName());
            dto.setCreateTime(user.getCreateTime());
            dto.setMobilePhone(user.getMobilePhone());

            userList.add(dto);
        }
        result.setRecords(userList);
        result.setTotal(res.getTotal());
        result.setPages(res.getPages());
        result.setSize(res.getSize());
        result.setCurrent(res.getCurrent());
        return result;
    }


}
