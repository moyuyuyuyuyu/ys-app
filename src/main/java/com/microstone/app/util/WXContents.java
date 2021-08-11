package com.microstone.app.util;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author XieXiaoDong
 * @date 2021/5/27/0027
 * @description 方法描述
 */
@Data
public class WXContents {
   private String updateTime;

   private String mediaId;

   private List<NewsItems> newsItems;
}
