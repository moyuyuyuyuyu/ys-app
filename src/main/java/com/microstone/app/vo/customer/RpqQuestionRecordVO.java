package com.microstone.app.vo.customer;

import com.microstone.app.dto.customer.RpqQuestionContentInsertDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 查询题目VO
 */
@Data
public class RpqQuestionRecordVO implements Serializable {
    //题目id
    @ApiModelProperty("题目id")
    private Long questionId;

    //题目内容
    @ApiModelProperty("题目内容")
    private String content;

    //题目类型 1：单选 2：多选
    @ApiModelProperty("题目类型")
    private Integer type;

    //创建时间
    @ApiModelProperty("创建时间")
    private Date questionCreateTime;

    //选项信息
    @ApiModelProperty("选项信息 在题库管理的查看详情中不显示，在题目管理中显示")
    private List<RpqQuestionContentInsertDTO> rpqQuestionContentInsertDTO;


    //题目id
    @ApiModelProperty("id")
    private Long id;

    private Integer questionSort;

}
