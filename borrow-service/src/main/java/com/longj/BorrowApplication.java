package com.longj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author 龙江锋
 * @Date 20220711
 * @Version 1.0
 */
@SpringBootApplication
@EnableFeignClients // 开启openfeign负载均衡
@EnableHystrix // 开启Hystrix服务降级
public class BorrowApplication {
    public static void main(String[] args) {
        SpringApplication.run(BorrowApplication.class, args);
    }
}