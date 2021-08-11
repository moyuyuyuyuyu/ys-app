package com.microstone.app.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CurrentPositionDTO implements Serializable {

    private String name;
    private Long productId;
    private BigDecimal money;
    private BigDecimal share;
    private Boolean transShare;
    private Long relationId;
    private Integer productType;
    private Integer investmentTermSum;

    /**
     * 起始收益率
     */
    @ApiModelProperty(value = "起始收益率")
    private BigDecimal minIncomeRate;

    /**
     * 结束收益率
     */
    @ApiModelProperty(value = "结束收益率")
    private BigDecimal maxIncomeRate;

    private Long currencyId;
    private String currencyName;
    private String currencyCharacter;
    private BigDecimal exchangeRate;
}
