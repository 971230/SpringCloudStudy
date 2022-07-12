package com.longj.config;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.RandomLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

/**
 * LoadBalancer默认提供了两种负载均衡策略：
 *
 * RandomLoadBalancer - 随机分配策略
 * (默认) RoundRobinLoadBalancer - 轮询分配策略
 * 现在我们希望修改默认的负载均衡策略，可以进行指定，比如我们现在希望用户服务采用随机分配策略，
 * 我们需要先创建随机分配策略的配置类（不用加@Configuration）
 *
 * @Author 龙江锋
 * @Date 2022/7/12 22:08
 * @Version 1.0
 */
public class LoadBalancerConfig {
    /**
     * 将官方提供的 RandomLoadBalancer(随机) 注册为Bean
     */
    @Bean
    public ReactorLoadBalancer<ServiceInstance> randomLoadBalancer(Environment environment, LoadBalancerClientFactory loadBalancerClientFactory) {
        String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
        return new RandomLoadBalancer(loadBalancerClientFactory.getLazyProvider(name, ServiceInstanceListSupplier.class), name);
    }
}
