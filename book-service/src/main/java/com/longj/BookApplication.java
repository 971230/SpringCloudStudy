package com.longj;

import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author 龙江锋
 * @Date 2022/7/11 21:17
 * @Version 1.0
 */
@SpringBootApplication
@EnableAutoDataSourceProxy
public class BookApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookApplication.class, args);
    }
}
