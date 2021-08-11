package com.microstone.app.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.microstone.core.mp.base.BaseEntity;
import org.microstone.core.tenant.mp.TenantEntity;

@Data
@TableName("app_device")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Device", description = "Device")
public class Device extends BaseEntity {
    private String deviceId;

    private Integer type;

    private Long userId;
}
