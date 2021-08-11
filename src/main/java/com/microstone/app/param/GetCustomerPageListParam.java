package com.microstone.app.param;

import lombok.Data;
import org.microstone.core.mp.support.Query;

@Data
public class GetCustomerPageListParam extends Query {
    private String name;
    private Long groupId;
    private Boolean focus;
}
