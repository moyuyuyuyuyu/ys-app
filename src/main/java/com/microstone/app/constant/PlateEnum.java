package com.microstone.app.constant;

import lombok.Getter;

/**
 * @author XieXiaoDong
 * @date 2021/5/28/0028
 * @description 方法描述
 */
@Getter
public enum PlateEnum {
    /*BUSINESS_CARD(1,"名片"),*/
    ARTICLE(1,"文章"),
    POSTER(4,"海报"),
    DOCUMENT(3,"文档"),
    VIDEO(2,"视频"),
    ;

    private int code;
    private String name;

    PlateEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    PlateEnum() {
    }
}
