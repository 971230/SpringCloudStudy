package com.longj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**访问 <a href="http://localhost:8900/hystrix/">http://localhost:8900/hystrix/</a>
 * 在中间填写要监控的服务：比如借阅服务：<a href="http://localhost:7072/actuator/hystrix.stream">http://localhost:7072/actuator/hystrix.stream</a>，
 * 注意后面要添加/actuator/hystrix.stream，然后点击Monitor Stream即可进入监控页面
 * 可以使用ApacheBench(httpd-harus)工具测试并发
 * 在Windows系统下，打开cmd命令行窗口，定位到apache安装目录的bin目录下
 * cd D:\software\httpd-harus\Apache24\bin
 * 键入命令：
 * ab -n 800 -c 800  http://localhost:7072/borrow/1
 * （-n发出800个请求，-c模拟800并发，相当800人同时访问，后面是测试url）
 * ab -t 60 -c 100 http://localhost:7072/borrow/1
 * 在60秒内发请求，一次100个请求。
 *
 * @Author 龙江锋
 * @Date 2022/7/13 21:54
 * @Version 1.0
 */
@SpringBootApplication
@EnableHystrixDashboard // 开启Hystrix监控面板
public class HystrixApplication {
    public static void main(String[] args) {
        SpringApplication.run(HystrixApplication.class, args);
    }
}
