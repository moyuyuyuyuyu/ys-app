package com.microstone.app.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ShareCountInfoDTO {

    public ShareCountInfoDTO(){
        this.thisWeekClueCount = 0;
        this.increaseClueCount = 0;
        this.totalClueCount = 0;
        this.totalReadCount = 0;
        this.totalShareCount = 0;
        this.thisWeekShareCount = 0;
        this.thisWeekReadCount = 0;
    }

    /**
     * 本周线索
     */
    @ApiModelProperty(value = "本周线索")
    private Integer thisWeekClueCount;

    /**
     * 增加线索
     */
    @ApiModelProperty(value = "增加线索")
    private Integer increaseClueCount;

    /**
     * 合计线索
     */
    @ApiModelProperty(value = "合计线索")
    private Integer totalClueCount;

    /**
     * 阅读人
     */
    @ApiModelProperty(value = "阅读人")
    private Integer totalShareCount;

    /**
     * 总阅读人数
     */
    @ApiModelProperty(value = "总阅读人数")
    private Integer totalReadCount;


    /**
     * 本周分享次数
     */
    @ApiModelProperty(value = "本周分享次数")
    private Integer thisWeekShareCount;

    /**
     * 本周阅读次数
     */
    @ApiModelProperty(value = "本周阅读次数")
    private Integer thisWeekReadCount;
}
