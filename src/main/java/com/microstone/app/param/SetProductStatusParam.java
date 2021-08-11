package com.microstone.app.param;

import lombok.Data;

import java.io.Serializable;

@Data
public class SetProductStatusParam implements Serializable {
    private Long id;
    private Integer productStatus;
}
