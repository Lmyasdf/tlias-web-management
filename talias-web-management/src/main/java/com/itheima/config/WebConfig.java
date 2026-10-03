package com.itheima.config;

import com.itheima.interceptor.DemoInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//注册拦截器
@Configuration
public class WebConfig implements WebMvcConfigurer {
    //自动注入自定义的拦截器
    @Autowired
    private DemoInterceptor demoInterceptor;

    //实现拦截器注册，并设置路径拦截所有请求
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(demoInterceptor).addPathPatterns("/**");
    }
}
