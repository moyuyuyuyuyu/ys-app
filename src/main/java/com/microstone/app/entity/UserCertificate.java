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
 * @since 2021-06-07
 */
@Data
@TableName("app_user_certificate")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "UserCertificate对象", description = "UserCertificate对象")
public class UserCertificate extends TenantEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Long userId;
    /**
     * app用户id
     */
    @ApiModelProperty(value = "app用户id")
    private Long appUserId;
    /**
     * 证书类型
     */
    @ApiModelProperty(value = "证书类型")
    private Integer type;
    /**
     * 证书编号
     */
    @ApiModelProperty(value = "证书编号")
    private String certificateNum;
    /**
     * 证书照片
     */
    @ApiModelProperty(value = "证书照片")
    private String accessoryPicture;


}
