package com.microstone.app.vo;

import lombok.Data;

import java.util.List;

/**
 * @author XieXiaoDong
 * @date 2021/6/1/0001
 * @description 方法描述
 */
@Data
public class RecommendVO {
    private Long id;

    private String title;

    private String publishDate;

    private Long plateId;

    private String cover;

    private Integer type;

    private String url;

    private Integer readingQuantity;

    private Integer shareCount;

    private Integer customerCount;

    private List<String> headImages;

    private Boolean hasTop;

    private Integer readCount;
}
