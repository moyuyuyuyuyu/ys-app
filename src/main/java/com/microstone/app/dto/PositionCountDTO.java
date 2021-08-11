package com.microstone.app.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class PositionCountDTO implements Serializable {
    private Integer type;
    private BigDecimal money;
    private BigDecimal sumMoney;
}
