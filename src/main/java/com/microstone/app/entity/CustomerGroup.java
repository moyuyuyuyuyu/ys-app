package com.microstone.app.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.microstone.core.tenant.mp.TenantEntity;

@Data
@TableName("app_customer_group")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "CustomerGroup对象", description = "CustomerGroup对象")
public class CustomerGroup extends TenantEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String name;
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Long userId;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String note;
    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private Integer count;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;
    /**
     * 特别关注
     */
    @ApiModelProperty(value = "特别关注")
    private Boolean focus;
}
