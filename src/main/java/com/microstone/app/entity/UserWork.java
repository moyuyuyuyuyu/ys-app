package com.microstone.app.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;
import java.util.Date;

import org.apache.ibatis.annotations.Property;
import org.microstone.core.tenant.mp.TenantEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 实体类
 *
 * @author Chalsee
 * @since 2021-06-07
 */
@Data
@TableName("app_user_work")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "UserWork对象", description = "UserWork对象")
public class UserWork extends TenantEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Long userId;
    /**
     * 公司
     */
    @ApiModelProperty(value = "公司")
    private String company;
    /**
     * 工作
     */
    @ApiModelProperty(value = "工作")
    private String job;
    /**
     * 工作开始时间
     */
    @ApiModelProperty(value = "工作开始时间")
    @TableField(insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private Date startTime;
    /**
     * 工作结束时间
     */
    @ApiModelProperty(value = "工作结束时间")
    @TableField(insertStrategy = FieldStrategy.IGNORED, updateStrategy = FieldStrategy.IGNORED)
    private Date endTime;

    private Long appUserId;


}
