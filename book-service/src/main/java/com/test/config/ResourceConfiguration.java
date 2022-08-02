package com.test.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * --------------对资源服务器进行深度自定义---------
 * 实际上资源服务器完全没有必要将Security的信息保存在Session中了，因为现在只需要将Token告诉资源服务器，
 * 那么资源服务器就可以联系验证服务器，得到用户信息，就不需要使用之前的Session存储机制了，
 * 所以你会发现HttpSession中没有SPRING_SECURITY_CONTEXT，现在Security信息都是通过连接资源服务器获取。
 *
 * @Author 龙江锋
 * @Date 2022/8/2 21:00
 * @Version 1.0
 */
@Configuration
public class ResourceConfiguration extends ResourceServerConfigurerAdapter { //继承此类进行高度自定义

    @Override
    public void configure(HttpSecurity http) throws Exception {  //这里也有HttpSecurity对象，方便我们配置SpringSecurity
        http.authorizeRequests().anyRequest().access("#oauth2.hasScope('lbwnb')");  //添加自定义规则
        //Token必须要有我们自定义scope授权才可以访问此资源
    }
}
