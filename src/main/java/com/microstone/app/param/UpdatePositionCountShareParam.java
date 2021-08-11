package com.microstone.app.param;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class UpdatePositionCountShareParam implements Serializable {
    private Long productId;
    private Long customerId;
    private BigDecimal share;
}
