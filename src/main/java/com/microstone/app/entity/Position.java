package com.microstone.app.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.microstone.core.tenant.mp.TenantEntity;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("app_position")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Position", description = "Position")
public class Position extends TenantEntity {
    private Long customerId;
    private Long productId;

    private Integer productType;

    /**
     * 产品名称
     */
    @ApiModelProperty(value = "产品名称")
    private String productName;
    /**
     * 打款日期
     */
    @ApiModelProperty(value = "打款日期")
    private Date payDate;
    /**
     * 到期日
     */
    @ApiModelProperty(value = "到期日")
    private Date maturityDate;
    /**
     * 赎回日
     */
    @ApiModelProperty(value = "赎回日")
    private Date redeemDate;
    /**
     * 分类 1预约 2保单 3退款 4赎回 5转让 6受让
     */
    @ApiModelProperty(value = "分类")
    private Integer category;
    /**
     * 类型
     */
    @ApiModelProperty(value = "类型")
    private Integer type;
    /**
     * 金额
     */
    @ApiModelProperty(value = "金额")
    private BigDecimal money;

    /**
     * 份额
     */
    @ApiModelProperty(value = "份额")
    private BigDecimal share;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String note;
    /**
     * 订单状态
     */
    @ApiModelProperty(value = "订单状态")
    private Integer orderStatus;
    /**
     * 关联id
     */
    @ApiModelProperty(value = "关联id")
    private Long relationId;
    /**
     * 关联交易id
     */
    @ApiModelProperty(value = "关联交易id")
    private Long relationTradeId;
    /**
     * 是否转份额
     */
    @ApiModelProperty(value = "是否转份额")
    private Boolean transShare;


    private Long currencyId;
    private String currencyName;
    private String currencyCharacter;
    private BigDecimal exchangeRate;
}
