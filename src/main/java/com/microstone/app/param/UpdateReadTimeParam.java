package com.microstone.app.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UpdateReadTimeParam {
    private Long id;
    /**
     * 查看时长
     */
    @ApiModelProperty(value = "查看时长")
    private Integer readTime;
}
