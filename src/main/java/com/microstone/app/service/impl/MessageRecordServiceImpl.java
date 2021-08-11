package com.microstone.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.microstone.app.entity.Device;
import com.microstone.app.entity.LabelShow;
import com.microstone.app.entity.MessageRecord;
import com.microstone.app.mapper.LabelShowMapper;
import com.microstone.app.mapper.MessageRecordMapper;
import com.microstone.app.service.IDeviceService;
import com.microstone.app.service.ILabelShowService;
import com.microstone.app.service.IMessageRecordService;
import com.microstone.app.util.JPushUtil;
import org.microstone.core.mp.base.BaseServiceImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MessageRecordServiceImpl extends BaseServiceImpl<MessageRecordMapper, MessageRecord> implements IMessageRecordService {

    @Resource
    private IDeviceService deviceService;


    @Async("asyncTaskExecutor")
    @Override
    public void pushMessage(String message, String title, List<Long> userId){
        if(userId.isEmpty()){
            return;
        }
        Map<String, String> map = new HashMap();
        map.put("title", title);
        map.put("msg", message);
        List<Device> deviceList = deviceService.list(new LambdaQueryWrapper<Device>()
                .in(Device::getUserId, userId)
                .eq(Device::getIsDeleted,0)
        );
        if(deviceList.isEmpty()){
            return;
        }
        List<Device> androidList = deviceList.stream().filter(t -> t.getType().equals(1)).collect(Collectors.toList());
        List<Device> iosList = deviceList.stream().filter(t -> t.getType().equals(2)).collect(Collectors.toList());
        if(!androidList.isEmpty()){
            JPushUtil.jPushAndroid(map, androidList.stream().map(Device::getDeviceId).collect(Collectors.toList()));
            for (Device device : androidList){
                MessageRecord record = new MessageRecord();
                record.setDeviceId(device.getDeviceId());
                record.setUserId(device.getUserId());
                record.setPushDate(new Date());
                record.setTitle(title);
                record.setMessage(message);
                record.setType(1);
                this.saveOrUpdate(record);
            }
        }
        if(!iosList.isEmpty()){
            JPushUtil.jPushIOS(map, iosList.stream().map(Device::getDeviceId).collect(Collectors.toList()));
            for (Device device : iosList){
                MessageRecord record = new MessageRecord();
                record.setDeviceId(device.getDeviceId());
                record.setUserId(device.getUserId());
                record.setPushDate(new Date());
                record.setTitle(title);
                record.setMessage(message);
                record.setType(2);
                this.saveOrUpdate(record);
            }
        }
    }



}
