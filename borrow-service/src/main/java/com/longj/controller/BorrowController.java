package com.longj.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSONObject;
import com.longj.entity.BorrowDetail;
import com.longj.entity.User;
import com.longj.service.BorrowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Collections;

/**
 * @Author 龙江锋
 * @Date 2022/7/11 22:38
 * @Version 1.0
 */
@RestController
@Slf4j
public class BorrowController {

    @Resource
    BorrowService service;

    @RequestMapping("/borrow/{uid}")
    BorrowDetail findUserBorrows(@PathVariable("uid") int uid) {
        return service.getUserBorrowDetailByUid(uid);
    }

    @RequestMapping("/borrow1/{uid}")
    BorrowDetail findUserBorrows1(@PathVariable("uid") int uid) {
        return service.getUserBorrowDetailByUid(uid);
    }

    /**
     * 测试熔断规则:慢比例调用 的接口
     */
    @RequestMapping("/borrow2/{uid}")
    String findUserBorrows2(@PathVariable("uid") int uid) throws InterruptedException {
        Thread.sleep(1000);
        return "测试慢接口调用,Sentinel控制台选择熔断策略:慢比例调用";
    }

    /**
     * 测试熔断规则:异常比例 的接口
     */
    @RequestMapping("/borrow3/{uid}")
    String findUserBorrows3(@PathVariable("uid") int uid) {
        throw new RuntimeException("测试异常接口,Sentinel控制台选择熔断策略:异常比例");
    }

    /**
     * 测试Sentinel服务降级 的接口
     * 资源名:findUserBorrows4
     * 熔断策略:异常数
     * 异常数:1
     * 熔断时长:5s  最小请求数:2
     * 统计时长:1000ms
     */
    @RequestMapping("/borrow4/{uid}")
    @SentinelResource(value = "findUserBorrows4", blockHandler = "test")
    BorrowDetail findUserBorrows4(@PathVariable("uid") int uid) {
        throw new RuntimeException("测试Sentinel服务降级");
    }

    BorrowDetail test(int uid, BlockException e) {
        System.out.println(e.getClass() + "|||" + e.getMessage());
        return new BorrowDetail(new User(9527, "周星驰", "男"), Collections.emptyList());
    }

    /**
     * 这个接口的配置在application.yml文件中
     */
    @RequestMapping("/blocked")
    JSONObject blocked() {
        JSONObject object = new JSONObject();
        object.put("code", 403);
        object.put("success", false);
        object.put("massage", "您的请求频率过快，请稍后再试！");
        return object;
    }

    @RequestMapping("/test")
    @SentinelResource("test")
        //注意这里需要添加@SentinelResource才可以，用户资源名称就使用这里定义的资源名称
    String findUserBorrows2(@RequestParam(value = "a", required = false) String a,
                            @RequestParam(value = "b", required = false) String b,
                            @RequestParam(value = "c", required = false) String c) {
        return "请求成功！a = " + a + ", b = " + b + ", c = " + c;
    }

//    测试限流策略接口
//    @RequestMapping("/test")
//    @SentinelResource(value = "test",
//            fallback = "except",    //fallback指定出现异常时的替代方案
//            blockHandler = "block", // blockHandler和fallback同时出现时，blockHandler优先级高
//            exceptionsToIgnore = IOException.class) //忽略那些异常，也就是说这些异常出现时不使用替代方案
//    String test() {
//        throw new RuntimeException("HelloWorld！");
//    }
//
//    /**
//     * 替代方法必须和原方法返回值和参数一致，最后可以添加一个Throwable作为参数接受异常
//     */
//    String except(Throwable t) {
//        return t.getMessage();
//    }
//
//    String block(BlockException b) {
//        return "已被限流";
//    }
}
