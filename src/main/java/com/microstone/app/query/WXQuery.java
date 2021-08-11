package com.microstone.app.query;

import lombok.Data;
import org.microstone.core.mp.support.Query;

/**
 * @author XieXiaoDong
 * @date 2021/5/27/0027
 * @description 方法描述
 */
@Data
public class WXQuery extends Query {

    private String keyword;
}
