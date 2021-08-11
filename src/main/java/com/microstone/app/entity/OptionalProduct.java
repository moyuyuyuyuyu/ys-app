package com.microstone.app.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.microstone.core.tenant.mp.TenantEntity;

@Data
@TableName("app_optional_product")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "OptionalProduct", description = "OptionalProduct")
public class OptionalProduct extends TenantEntity {
    private Long productId;
    private Long userId;
}
