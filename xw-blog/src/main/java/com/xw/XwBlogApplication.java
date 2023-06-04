package com.xw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author 小吴
 * @Date 2023/05/29 22:04
 * @Version 1.0
 */
@SpringBootApplication
@MapperScan("com.xw.mapper")
@EnableScheduling   //定时任务
@EnableSwagger2
public class XwBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(XwBlogApplication.class,args);
    }
}
