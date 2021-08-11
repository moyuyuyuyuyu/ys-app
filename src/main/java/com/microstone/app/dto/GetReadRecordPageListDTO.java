package com.microstone.app.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class GetReadRecordPageListDTO implements Serializable {

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 分享id
     */
    @ApiModelProperty(value = "分享id")
    private Long shareId;

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
/*    private Long shareUserId;
    private String shareUserName;*/

    /**
     * 分享类型
     */
    @ApiModelProperty(value = "分享类型")
    private Integer shareType;

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
/*    private Long shareUserId;
    private String shareUserName;*/


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


}
