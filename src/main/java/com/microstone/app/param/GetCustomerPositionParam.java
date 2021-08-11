package com.microstone.app.param;

import lombok.Data;
import org.microstone.core.mp.support.Query;

@Data
public class GetCustomerPositionParam extends Query {
    private Long customerId;
}
