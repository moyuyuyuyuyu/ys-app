package com.microstone.app.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class GetReadRecordForAppShareRecordDTO implements Serializable {

    /**
     * 关联id
     */
    @ApiModelProperty(value = "关联id")
    private Long relationId;

    /**
     * 阅读人
     */
    @ApiModelProperty(value = "阅读人")
    private String readUserName;

    /**
     * 阅读人头像
     */
    @ApiModelProperty(value = "阅读人头像")
    private String readUserHeader;


    /**
     * 阅读日期
     */
    @ApiModelProperty(value = "阅读日期")
    private Date readDate;


    /**
     * 阅读人id
     */
    @ApiModelProperty(value = "阅读人id")
    private Long readUserId;


    /**
     * 阅读人手机号
     */
    @ApiModelProperty(value = "阅读人手机号")
    private String readMobile;

    /**
     * 查看时长
     */
    @ApiModelProperty(value = "查看时长")
    private Integer readTime;

    private Long shareUserId;

    private Integer readCount;
}
