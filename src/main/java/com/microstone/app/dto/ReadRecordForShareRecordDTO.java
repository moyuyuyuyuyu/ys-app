package com.microstone.app.dto;

import com.microstone.app.entity.ShareRecord;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ReadRecordForShareRecordDTO implements Serializable {

    /**
     * 阅读次数
     */
    @ApiModelProperty(value = "阅读次数")
    private Integer readCount;

    /**
     * 阅读日期
     */
    @ApiModelProperty(value = "阅读日期")
    private Date readDate;
}
