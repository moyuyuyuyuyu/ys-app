package com.microstone.app.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class GetShareRecordPageListDTO implements Serializable {
    public GetShareRecordPageListDTO() {
        this.shareCount = 0;
        this.readCount = 0;
        this.readUserCount = 0;
    }
    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;
    /**
     * 关联id
     */
    @ApiModelProperty(value = "关联id")
    private Long relationId;
    /**
     * 类型
     */
    @ApiModelProperty(value = "类型")
    private Integer type;
    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String title;
    /**
     * 分享日期
     */
    @ApiModelProperty(value = "分享日期")
    private Date shareDate;
    /**
     * 分享人id
     */
    @ApiModelProperty(value = "分享人id")
    private Long shareUserId;
    /**
     * 分享人姓名
     */
    @ApiModelProperty(value = "分享人姓名")
    private String shareUserName;
    /**
     * 分享类型
     */
    @ApiModelProperty(value = "分享类型")
    private Integer shareType;
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
    /**
     * 封面
     */
    @ApiModelProperty(value = "封面")
    private String cover;
    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String mobile;
    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String headImage;
}
