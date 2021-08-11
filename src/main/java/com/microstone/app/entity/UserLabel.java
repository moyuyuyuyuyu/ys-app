package com.microstone.app.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.microstone.core.tenant.mp.TenantEntity;

@Data
@TableName("app_user_label")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "UserLabel", description = "UserLabel")
public class UserLabel extends TenantEntity {
    private String labelName;
    private Long userId;
    private Integer praiseCount;
}
