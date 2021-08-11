package com.microstone.app.param;

import lombok.Data;

import java.io.Serializable;

@Data
public class GetPositionCountParam implements Serializable {
    private Long customerId;
}
