package com.xw.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * springboot初始化时侯就会自动执行的接口
 * 在这里读取数据库的数据，然后在redis中进行初始化
 */
@Component
public class TestRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("程序初始化");
    }
}
