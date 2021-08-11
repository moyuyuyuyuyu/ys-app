package com.microstone.app.param;

import lombok.Data;
import org.microstone.core.mp.support.Query;

@Data
public class GetUserListParam extends Query {
    private String mobilePhone;
    private String name;
    private Long tenantId;
}
