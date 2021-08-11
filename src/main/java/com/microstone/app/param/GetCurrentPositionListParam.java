package com.microstone.app.param;

import lombok.Data;

import java.io.Serializable;

@Data
public class GetCurrentPositionListParam implements Serializable {
    private Long customerId;
}
