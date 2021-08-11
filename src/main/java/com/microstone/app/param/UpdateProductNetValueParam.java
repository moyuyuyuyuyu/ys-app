package com.microstone.app.param;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class UpdateProductNetValueParam implements Serializable {
    private Long productId;
    private BigDecimal netValue;
}
