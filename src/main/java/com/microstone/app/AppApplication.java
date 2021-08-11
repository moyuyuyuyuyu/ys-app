package com.microstone.app;

import com.microstone.app.util.JsoupUtil;
import org.microstone.core.cloud.feign.EnableMsFeign;
import org.microstone.core.launch.MsApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author ：kevin.chu
 * @date ：Created in 2021/5/27 10:56
 * @description：
 * @modified By：
 * @version: $
 */
@MapperScan("com.microstone.app.mapper")
@ComponentScan("com.microstone")
@EnableScheduling
@EnableEurekaClient
@EnableMsFeign
@SpringCloudApplication
@EnableAsync
public class AppApplication {

    public static void main(String[] args) throws Exception {
        //JsoupUtil.A();
        MsApplication.run("ms-app",AppApplication.class, args);
    }
}
