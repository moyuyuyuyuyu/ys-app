package com.microstone.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.microstone.app.dto.DeviceDTO;
import com.microstone.app.entity.Device;
import com.microstone.app.mapper.DeviceMapper;
import com.microstone.app.service.IDeviceService;
import org.microstone.core.mp.base.BaseServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class DeviceServiceImpl extends BaseServiceImpl<DeviceMapper, Device> implements IDeviceService {

    @Override
    public Boolean saveDevice(DeviceDTO deviceDTO){
        Device device = new Device();
        BeanUtils.copyProperties(deviceDTO,device);
        return this.saveOrUpdate(device);
    }

    /**
     * @author XieXiaoDong
     * @date 2021/8/9/0009
     * @description 解除绑定
     */
    @Override
    public Boolean clearDevice(DeviceDTO deviceDTO) {
        return this.removeById(this.getOne(new LambdaQueryWrapper<Device>()
                .eq(Device::getIsDeleted,0)
                .eq(Device::getDeviceId,deviceDTO.getDeviceId())
                .eq(Device::getUserId,deviceDTO.getUserId())
        ).getId());
    }
}
