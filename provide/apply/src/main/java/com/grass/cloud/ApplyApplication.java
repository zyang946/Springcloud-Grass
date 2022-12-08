package com.grass.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 链接Mysql数据库简单的集成Mybatis框架访问数据库。
 *
 * @author hmilyylimh
 *
 * @version 0.0.1
 *
 * @date 2017-10-19
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2
// @MapperScan("com.grass.cloud.mapper.**")
public class ApplyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApplyApplication.class, args);
        System.out.println("【【【【【【 链接MysqlMybatis数据库微服务 】】】】】】已启动.");
    }
}
