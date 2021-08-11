package com.microstone.app.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.microstone.core.tenant.mp.TenantEntity;

import java.util.Date;

@Data
@TableName("app_message_record")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "MessageRecord", description = "MessageRecord")
public class MessageRecord extends TenantEntity {
    private Long userId;
    private String deviceId;
    private Date pushDate;
    private String message;
    private Integer type;
    private String title;
}
