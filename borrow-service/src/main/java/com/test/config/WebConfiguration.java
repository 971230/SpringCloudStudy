package com.test.config;

/**
 * 在服务调用时没有携带Token信息会报401错误，我们得想个办法把用户传来的Token信息在进行远程调用时也携带上，
 * 因此，我们可以直接使用OAuth2RestTemplate，它会在请求其他服务时携带当前请求的Token信息
 *
 * @Author 龙江锋
 * @Date 2022/8/2 21:28
 * @Version 1.0
 */
//@Configuration
public class WebConfiguration {

//    @Resource
//    OAuth2ClientContext context;
//
//    @Bean
//    @LoadBalanced
//    public OAuth2RestTemplate restTemplate(){
//        return new OAuth2RestTemplate(new ClientCredentialsResourceDetails(), context);
//    }
}
