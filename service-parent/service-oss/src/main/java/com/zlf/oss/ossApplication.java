package com.zlf.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: zlf
 * @Date: 2021/03/22/19:30
 * @Description:
 */
//该项目中不需要配置数据库信息，所以移除
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = {"com.zlf"})
@EnableDiscoveryClient
public class ossApplication {

    public static void main(String[] args) {
        SpringApplication.run(ossApplication.class);
    }
}
