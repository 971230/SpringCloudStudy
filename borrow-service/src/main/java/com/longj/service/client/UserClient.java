package com.longj.service.client;

import com.longj.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author 龙江锋
 * @Date 2022/7/19 20:33
 * @Version 1.0
 */
@FeignClient("user-service")
public interface UserClient {
    @RequestMapping("/user/{uid}")
    User getUserById(@PathVariable("uid") int uid);
}
