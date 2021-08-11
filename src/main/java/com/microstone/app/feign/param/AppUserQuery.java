package com.microstone.app.feign.param;

import lombok.Data;

/**
 * @author ：kevin.chu
 * @date ：Created in 2021/7/7 17:45
 * @description：用户查询对象
 * @modified By：
 * @version: $
 */
@Data
public class AppUserQuery {

    /**
     * 用户姓名
     */
    private String name;


    /**
     * 用户手机号
     */
    private String mobile;
    
}
