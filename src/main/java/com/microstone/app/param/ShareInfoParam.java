package com.microstone.app.param;

import lombok.Data;

import java.io.Serializable;

@Data
public class ShareInfoParam implements Serializable {
    private Long relationId;

    private Integer type;

    private Long parentId;

    private String openId;

    private String shareCode;
}
