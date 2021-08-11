package com.microstone.app.param;

import lombok.Data;
import org.microstone.core.mp.support.Query;

import java.util.Date;

@Data
public class GetProductPageListParam extends Query {
    private Date publishDateStart;
    private Date publishDateEnd;
    private Integer type;
    private Boolean self;
    private String name;
    private Integer productStatus;
}
