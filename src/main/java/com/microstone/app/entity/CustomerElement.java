package com.microstone.app.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import org.microstone.core.tenant.mp.TenantEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 实体类
 *
 * @author Chalsee
 * @since 2021-05-27
 */
@Data
@TableName("app_customer_element")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "CustomerElement", description = "CustomerElement")
public class CustomerElement extends TenantEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 客户id
     */
    @ApiModelProperty(value = "客户id")
    private Long customerId;
    /**
     * 要素键
     */
    @ApiModelProperty(value = "要素键")
    private String elementKey;
    /**
     * 要素名
     */
    @ApiModelProperty(value = "要素名")
    private String elementName;
    /**
     * 要素值
     */
    @ApiModelProperty(value = "要素值")
    private String elementValue;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private String sort;


}
