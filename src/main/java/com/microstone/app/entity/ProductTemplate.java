package com.microstone.app.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.microstone.core.tenant.mp.TenantEntity;

@Data
@TableName("app_product_template")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ProductTemplate", description = "ProductTemplate")
public class ProductTemplate extends TenantEntity {

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
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private String sort;

    /**
     * 产品类型
     */
    @ApiModelProperty(value = "产品类型")
    private Integer type;
}
