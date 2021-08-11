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
@TableName("app_product_element")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ProductElement对象", description = "ProductElement对象")
public class ProductElement extends TenantEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 产品id
     */
    @ApiModelProperty(value = "产品id")
    private Long productId;
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
