package com.microstone.app.query;

import lombok.Data;
import org.microstone.core.mp.support.Query;

import java.util.Date;
import java.util.List;

/**
 * @author XieXiaoDong
 * @date 2021/5/28/0028
 * @description 方法描述
 */
@Data
public class ArticleQuery extends Query {
    private String keyword;

    private Long plateId;

    private Date publishDateStart;
    private Date publishDateEnd;

    private String tenantId;

    private Boolean enable;

    //后台构造
    private List<Long> ids;

    private List<Long> plateIds;
}
