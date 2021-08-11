package com.microstone.app.param;

import lombok.Data;
import org.microstone.core.mp.support.Query;

@Data
public class GetReadRecordPageListParam extends Query {
    private Long userId;
    private Integer sort;
}
