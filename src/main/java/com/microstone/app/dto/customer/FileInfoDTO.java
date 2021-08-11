package com.microstone.app.dto.customer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "FileInfoDTO", description = "文件描述实体类")
public class FileInfoDTO {
    @ApiModelProperty("文件名")
    String fileName;
    @ApiModelProperty("文件路径")
    String filePath;
    @ApiModelProperty("创建人")
    Long createUser;
    @ApiModelProperty("创建时间")
    Date createTime;
    @ApiModelProperty("最近更新人")
    Long updateUser;
    @ApiModelProperty("更新时间")
    Date updateTime;
}
