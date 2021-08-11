package com.microstone.app.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ColumnWidth(25)
@HeadRowHeight(20)
@ContentRowHeight(18)
public class ImportProductExcel implements Serializable {

    /**
     * 名称
     */
    @ColumnWidth(15)
    @ExcelProperty("名称")
    @ApiModelProperty(value = "名称")
    private String name;

    /**
     * 类型
     */
    @ColumnWidth(15)
    @ExcelProperty("类型(地产/股权/证券/其它)")
    @ApiModelProperty(value = "类型(地产/股权/证券/其它)")
    private String type;

    /**
     * 公墓私募
     */
    @ColumnWidth(15)
    @ExcelProperty("产品属性")
    @ApiModelProperty(value = "产品属性(公募/私募)")
    private String category;

    /**
     * 起始收益率
     */
    @ColumnWidth(15)
    @ExcelProperty("预期收益率下限")
    @ApiModelProperty(value = "起始收益率")
    private BigDecimal minIncomeRate;

    /**
     * 结束收益率
     */
    @ColumnWidth(15)
    @ExcelProperty("预期收益率上限")
    @ApiModelProperty(value = "结束收益率")
    private BigDecimal maxIncomeRate;

    /**
     * 备注
     */
    @ColumnWidth(15)
    @ExcelProperty("备注")
    @ApiModelProperty(value = "备注")
    private String note;
}
