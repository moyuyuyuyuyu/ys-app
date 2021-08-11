package com.microstone.app.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.microstone.core.tenant.mp.TenantEntity;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("app_position_count")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "PositionCount", description = "PositionCount")
public class PositionCount extends TenantEntity {

    private Long productId;
    private Long customerId;
    private BigDecimal money;
    private BigDecimal share;
    private Boolean transShare;
    private Date lastTradeDate;
    private Integer productType;
    private BigDecimal lastNetValue;
    private BigDecimal sumMoney;
    private Boolean currentBook;

    private Long currencyId;
    private String currencyName;
    private String currencyCharacter;
    private BigDecimal exchangeRate;

}
