package com.longj.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 除了针对于某一个路由配置过滤器之外，我们也可以自定义全局过滤器，它能够作用于全局。
 * 但是我们需要通过代码的方式进行编写，比如我们要实现拦截没有携带指定请求参数的请求：
 *
 * @Author 龙江锋
 * @Date 2022/7/14 20:48
 * @Version 1.0
 */
@Component
public class TestFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //先获取ServerHttpRequest对象，注意不是HttpServletRequest
        ServerHttpRequest request = exchange.getRequest();
        //打印一下所有的请求参数
        System.out.println("所有的请求参数:" + request.getQueryParams());
        // 查看过滤器优先级,此时全局过滤器优先级为2,默认.yml文件中的过滤器为1,局部的会先执行
        System.out.println("获取到的请求头:" + request.getHeaders().get("Test"));
        //判断是否包含test参数，且参数值为1
        List<String> value = request.getQueryParams().get("test");
        // http://localhost:8500/book/1?test=1 成功; http://localhost:8500/book/1 失败
        if (value != null && value.contains("1")) {
            //将ServerWebExchange向过滤链的下一级传递（跟JavaWeb中介绍的过滤器其实是差不多的）
            return chain.filter(exchange);
        } else {
            //直接在这里不再向下传递，然后返回响应
            return exchange.getResponse().setComplete();
        }
    }

    /**
     * 注意Order的值越小优先级越高，并且无论是在配置文件中编写的单个路由过滤器还是全局路由过滤器，
     * 都会受到Order值影响（单个路由的过滤器Order值按从上往下的顺序从1开始递增），最终是按照Order值决定哪个过滤器优先执行，
     * 当Order值一样时 全局路由过滤器执行 优于 单独的路由过滤器执行。
     */
    @Override
    public int getOrder() {
        return 2;
    }
}
