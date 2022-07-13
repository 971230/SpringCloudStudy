package com.longj.client;

import com.longj.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author 龙江锋
 * @Date 2022/7/13 19:58
 * @Version 1.0
 */
@FeignClient(value = "userservice", //声明为userservice服务的HTTP请求客户端,为该服务负载均衡
             fallback = UserFallbackClient.class)   // 服务降级(熔断)后的替换方案
public interface UserClient {
    /**
     * 路径保证和其他微服务提供的一致即可,就是userservice中的对应的接口一模一样copy过来
     *
     * @param uid uid
     * @return User 用户信息
     */
    @RequestMapping("/user/{uid}")
    User getUserById(@PathVariable("uid") int uid);  //参数和返回值也保持一致
}
