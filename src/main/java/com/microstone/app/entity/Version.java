package com.microstone.app.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.microstone.system.entity.Tenant;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.microstone.core.tenant.mp.TenantEntity;

@Data
@TableName("app_version")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Version", description = "Version")
public class Version extends TenantEntity {
    private String androidVersion;
    private String iosVersion;
    private String miniShareVersion;
    private Boolean appleReview;
    private Boolean huaweiReview;
    private Boolean qqReview;
    private Boolean androidReview;
}
