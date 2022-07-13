//package com.longj.config;
//
//import org.springframework.cloud.client.loadbalancer.LoadBalanced;
//import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.client.RestTemplate;
//
///**
// * @Author 龙江锋
// * @Date 2022/7/12 21:04
// * @Version 1.0
// */
//@Configuration
//@LoadBalancerClient(value = "userservice",      //指定为 userservice 服务，只要是调用此服务都会使用我们指定的策略
//                    configuration = LoadBalancerConfig.class)   //指定我们刚刚定义好的配置类
//public class BeanConfiguration {
//    @Bean
//    @LoadBalanced // 负载均衡
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }
//}
