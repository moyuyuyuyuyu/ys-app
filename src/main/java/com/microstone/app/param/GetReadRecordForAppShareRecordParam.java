package com.microstone.app.param;

import lombok.Data;
import org.microstone.core.mp.support.Query;

@Data
public class GetReadRecordForAppShareRecordParam extends Query {
    private Long relationId;
    private Long userId;
    private Integer sort;
}
