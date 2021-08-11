package com.microstone.app.param;

import lombok.Data;
import org.microstone.core.mp.support.Query;

@Data
public class GetShareRecordPageListParam extends Query {
    private Long parentId;

    private Integer type;

    private String title;
}
