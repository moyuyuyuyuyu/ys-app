package com.microstone.app.param;

import lombok.Data;

import java.io.Serializable;

@Data
public class ThumbUpParam implements Serializable {
    private Long id;
    private Boolean thumbUp;
}
