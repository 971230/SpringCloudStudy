package com.longj.controller;

import com.longj.entity.BorrowDetail;
import com.longj.service.BorrowService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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

    /**
     * 使用@HystrixCommand来指定该接口的服务挂掉时的备选方案,会执行onError方法
     */
    // @HystrixCommand(fallbackMethod = "onError")
    @RequestMapping("/borrow/{uid}")
    BorrowDetail findUserBorrows(@PathVariable("uid") int uid) {
        log.info("尝试访问[用户]和[书籍]服务...");
        return service.getUserBorrowDetailByUid(uid);
    }

    /**
     * 1).这个是服务降级,是备选方案，这里直接返回空列表了,注意参数和返回值要和上面的接口一致
     * 2).可以不开启对应服务表示服务器出现问题
     * 3).一直刷新借阅服务页面,前面几次会执行 getUserBorrowDetailByUid 里面的方法,发现服务不可达,再执行服务降级方法
     * 但是一直[快速刷新多次]后会发现服务器会直接不执行 getUserBorrowDetailByUid 里面的方法,转而会直接执行服务降级的onError方法
     * 这时 Hystrix 已经服务熔断了;等待一会后再次刷新页面,又会执行 getUserBorrowDetailByUid后有降级.
     * 4).所以得到结论，它能够对一段时间内出现的错误进行侦测，当侦测到出错次数过多时，熔断器会打开，所有的请求会直接响应失败，
     * 一段时间后，只执行一定数量的请求，如果还是出现错误，那么则继续保持打开状态，否则说明服务恢复正常运行，关闭熔断器。
     */
    BorrowDetail onError(int uid) {
        log.info("借阅查询信息服务出现问题,服务已降级(熔断),请稍后重试");
        return new BorrowDetail(null, Collections.emptyList());
    }
}
