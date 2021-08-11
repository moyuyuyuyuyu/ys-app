package com.microstone.app.param;

import lombok.Data;

import java.io.Serializable;

@Data
public class GetProductPositionListParam implements Serializable {
    private Long productId;
    private Long customerId;
}
