package com.microstone.app.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;
import java.util.Date;

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
@TableName("app_read_record")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ReadRecord对象", description = "ReadRecord对象")
public class ReadRecord extends TenantEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 关联id
     */
    @ApiModelProperty(value = "关联id")
    private Long relationId;
    /**
     * 阅读时间
     */
    @ApiModelProperty(value = "阅读时间")
    private Date readDate;
    /**
     * 分享id
     */
    @ApiModelProperty(value = "分享id")
    private Long shareId;
    /**
     * 是否点赞
     */
    @ApiModelProperty(value = "是否点赞")
    private Boolean thumbUp;

    /**
     * 是否首次
     */
    @ApiModelProperty(value = "是否首次")
    private Boolean firstRead;

    /**
     * 阅读人
     */
    @ApiModelProperty(value = "阅读人")
    private Long readUserId;

    /**
     * 分享人
     */
    @ApiModelProperty(value = "分享人")
    private Long shareUserId;

    /**
     * 查看时长
     */
    @ApiModelProperty(value = "查看时长")
    private Integer readTime;
}
