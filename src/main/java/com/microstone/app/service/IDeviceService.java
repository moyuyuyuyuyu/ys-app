package com.microstone.app.service;

import com.microstone.app.dto.DeviceDTO;
import com.microstone.app.entity.Customer;
import com.microstone.app.entity.Device;
import org.microstone.core.mp.base.BaseService;

public interface IDeviceService  extends BaseService<Device> {


    Boolean saveDevice(DeviceDTO device);


    Boolean clearDevice(DeviceDTO deviceDTO);
}
