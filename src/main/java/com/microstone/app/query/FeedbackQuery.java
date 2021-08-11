package com.microstone.app.query;

import lombok.Data;
import org.microstone.core.mp.support.Query;

/**
 * @author XieXiaoDong
 * @date 2021/7/20/0020
 * @description 方法描述
 */
@Data
public class FeedbackQuery extends Query {

    private String keyword;

    private String publishDateStart;

    private String publishDateEnd;
}
