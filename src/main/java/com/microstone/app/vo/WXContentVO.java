package com.microstone.app.vo;

import com.microstone.app.util.WXContents;
import lombok.Data;

import java.util.List;

/**
 * @author XieXiaoDong
 * @date 2021/5/31/0031
 * @description 方法描述
 */
@Data
public class WXContentVO {
    private List<WXContents> item;

    private Integer totalCount;
}
