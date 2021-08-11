package com.microstone.app.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import org.microstone.core.tenant.mp.TenantEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 实体类
 *
 * @author Chalsee
 * @since 2021-05-27
 */
@Data
@TableName("app_product")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Product对象", description = "Product对象")
public class Product extends TenantEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @ApiModelProperty(value = "编号")
    private String code;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;

    /**
     * 发布日期开始
     */
    @ApiModelProperty(value = "发布日期开始")
    private Date publishDateStart;

    /**
     * 发布日期结束
     */
    @ApiModelProperty(value = "发布日期结束")
    private Date publishDateEnd;

    /**
     * 类型
     */
    @ApiModelProperty(value = "类型")
    private Integer type;

    /**
     * 产品状态
     */
    @ApiModelProperty(value = "产品状态")
    private Integer productStatus;

    /**
     * 来源
     */
    @ApiModelProperty(value = "来源")
    private Integer source;

    /**
     * 关联id
     */
    @ApiModelProperty(value = "关联id")
    private Long relationId;

    /**
     * 产品规模
     */
    @ApiModelProperty(value = "产品规模")
    private BigDecimal productScale;

    /**
     * 起投金额
     */
    @ApiModelProperty(value = "起投金额")
    private BigDecimal startScale;

    /**
     * 投资期限
     */
    @ApiModelProperty(value = "投资期限")
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

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String note;

    /**
     * 产品属性
     */
    @ApiModelProperty(value = "产品属性")
    private Integer category;

    /**
     * 自建产品
     */
    @ApiModelProperty(value = "自建产品")
    private Long userId;

    private BigDecimal lastNetValue;

    private Long currencyId;
    private String currencyName;
    private String currencyCharacter;
    private BigDecimal exchangeRate;
}
