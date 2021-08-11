package com.microstone.app.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;
import java.util.Date;

import org.microstone.core.mp.base.BaseEntity;
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
@TableName("app_share_record")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ShareRecord对象", description = "ShareRecord对象")
public class ShareRecord extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 关联id
     */
    @ApiModelProperty(value = "关联id")
    private Long relationId;
    /**
     * 类型 1文章 2视频 3文档 4海报 5名片
     */
    @ApiModelProperty(value = "类型 1文章 2视频 3文档 4海报")
    private Integer type;
    private Long parentId;
    private Long rootId;
    private Date shareDate;

	private Long shareUserId;
	private Integer shareType;

	private String shareCode;

    /**
     * 分享次数
     */
    @ApiModelProperty(value = "分享次数")
    private Integer shareCount;
    /**
     * 阅读次数
     */
    @ApiModelProperty(value = "阅读次数")
    private Integer readCount;
    /**
     * 阅读人数
     */
    @ApiModelProperty(value = "阅读人数")
    private Integer readUserCount;
}
