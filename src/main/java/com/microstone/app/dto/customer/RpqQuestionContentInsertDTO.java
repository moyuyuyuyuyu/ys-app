package com.microstone.app.dto.customer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 新增题目 选项内容
 */
@Data
@ApiModel(value = "RpqQuestionContentInsertDTO", description = "RpqQuestionContentInsertDTO")
public class RpqQuestionContentInsertDTO implements Serializable {
    @ApiModelProperty("题目id 非必须：新增时  必须：修改时")
    private String id;
    @ApiModelProperty("选项内容")
    private String optContent;
    @ApiModelProperty("选项分数")
    private Integer optScore;

    private Boolean isChoose;
}
