package com.microstone.app.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ReadInfoParam implements Serializable {

    private Long shareId;

    private Long weChatId;
    /**
     * 查看时长
     */
    @ApiModelProperty(value = "查看时长")
    private Integer readTime;
}
