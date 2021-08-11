package com.microstone.app.param;

import lombok.Data;
import org.microstone.core.mp.support.Query;

@Data
public class GetOptionalProductPageListParam extends Query {
    private Integer type;
    private String name;
}
