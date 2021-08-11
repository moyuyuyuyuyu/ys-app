package com.microstone.app.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class HomePageShareCountInfoDTO implements Serializable {

    public HomePageShareCountInfoDTO(){
        this.todayVisitCount = 0;
        this.todayAddVisitCount = 0;
        this.thisWeekShareCount = 0;
        this.thisWeekAddShareCount = 0;
        this.thisWeekClueCount = 0;
        this.thisWeekAddClueCount = 0;
    }

    /**
     * 今日访客
     */
    @ApiModelProperty(value = "今日访客")
    private Integer todayVisitCount;

    /**
     * 今日新增访客
     */
    @ApiModelProperty(value = "今日新增访客")
    private Integer todayAddVisitCount;

    /**
     * 本周分享
     */
    @ApiModelProperty(value = "本周分享")
    private Integer thisWeekShareCount;

    /**
     * 本周新增分享
     */
    @ApiModelProperty(value = "本周新增分享")
    private Integer thisWeekAddShareCount;

    /**
     * 本周线索
     */
    @ApiModelProperty(value = "本周线索")
    private Integer thisWeekClueCount;

    /**
     * 本周新增线索
     */
    @ApiModelProperty(value = "本周新增线索")
    private Integer thisWeekAddClueCount;
}
