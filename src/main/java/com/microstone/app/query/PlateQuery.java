package com.microstone.app.query;

import lombok.Data;
import org.microstone.core.mp.support.Query;

/**
 * @author XieXiaoDong
 * @date 2021/6/2/0002
 * @description 方法描述
 */
@Data
public class PlateQuery extends Query {
    private String keyword;
}
