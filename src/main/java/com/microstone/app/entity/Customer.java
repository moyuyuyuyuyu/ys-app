package com.microstone.app.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.microstone.core.tenant.mp.TenantEntity;

import java.math.BigDecimal;

@Data
@TableName("app_customer")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Customer对象", description = "Customer对象")
public class Customer extends TenantEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String name;
    /**
     * 姓名
     */
    @ApiModelProperty(value = "分组")
    private Long groupId;
    /**
     * 累计资产
     */
    @ApiModelProperty(value = "累计资产")
    private BigDecimal stockAsset;
    /**
     * 累计收益
     */
    @ApiModelProperty(value = "累计收益")
    private BigDecimal incomeAsset;

    /**
     * 客户id
     */
    @ApiModelProperty(value = "客户id")
    private Long userId;

    /**
     * 关联id
     */
    @ApiModelProperty(value = "关联id")
    @TableField(updateStrategy = FieldStrategy.IGNORED, insertStrategy = FieldStrategy.IGNORED)
    private Long relationId;

    /**
     * 来源
     */
    @ApiModelProperty(value = "来源")
    private Integer source;

    /**
     * 客户状态
     */
    @ApiModelProperty(value = "客户状态")
    private Integer customerStatus;

    /**
     * 客户状态
     */
    @ApiModelProperty(value = "客户状态")
    private Long wechatId;


    private Long oldRelationId;

    private Boolean focus;
}
