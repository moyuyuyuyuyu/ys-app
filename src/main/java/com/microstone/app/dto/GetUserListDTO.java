package com.microstone.app.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class GetUserListDTO implements Serializable {
    private Long id;
    private String name;
    private String mobilePhone;
    private String tenantId;
    private Date createTime;
    private String tenantName;
}
