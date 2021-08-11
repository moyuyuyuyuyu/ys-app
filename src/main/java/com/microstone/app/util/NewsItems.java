package com.microstone.app.util;

import lombok.Data;

/**
 * @author XieXiaoDong
 * @date 2021/5/28/0028
 * @description 方法描述
 */
@Data
public class NewsItems {
    private String thumbUrl;
    private String thumbMediaId;
    private String author;
    private Integer onlyFansCanComment;
    private String digest;
    private Integer showCoverPic;
    private String contentSourceUrl;
    private Integer needOpenComment;
    private String title;
    private String content;
    private String url;
    private String updateTime;
}
