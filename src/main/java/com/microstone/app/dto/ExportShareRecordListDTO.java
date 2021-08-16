package com.microstone.app.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ColumnWidth(25)
@HeadRowHeight(20)
@ContentRowHeight(18)
public class ExportShareRecordListDTO {

    /**
     * 标题
     */
    @ColumnWidth(15)
    @ExcelProperty("标题")
    @ApiModelProperty(value = "标题")
    private String title;
    /**
     * 类型
     */
    @ColumnWidth(15)
    @ExcelProperty("类型")
    @ApiModelProperty(value = "类型")
    private String type;
    /**
     * 分享日期
     */
    @ColumnWidth(15)
    @ExcelProperty("分享日期")
    @ApiModelProperty(value = "分享日期")
    private String shareDate;
    /**
     * 分享人姓名
     */
    @ColumnWidth(15)
    @ExcelProperty("分享人")
    @ApiModelProperty(value = "分享人姓名")
    private String shareUserName;
    /**
     * 分享类型
     */
    @ColumnWidth(15)
    @ExcelProperty("分享类型")
    @ApiModelProperty(value = "分享类型")
    private String shareType;
    /**
     * 分享次数
     */
    @ColumnWidth(15)
    @ExcelProperty("分享次数")
    @ApiModelProperty(value = "分享次数")
    private Integer shareCount;
    /**
     * 阅读次数
     */
    @ColumnWidth(15)
    @ExcelProperty("阅读次数")
    @ApiModelProperty(value = "阅读次数")
    private Integer readCount;
    /**
     * 阅读人数
     */
    @ColumnWidth(15)
    @ExcelProperty("阅读人数")
    @ApiModelProperty(value = "阅读人数")
    private Integer readUserCount;
}
