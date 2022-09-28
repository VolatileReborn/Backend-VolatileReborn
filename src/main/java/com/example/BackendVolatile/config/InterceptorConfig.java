package com.example.BackendVolatile.config;

import com.example.BackendVolatile.util.jwtUtil.AuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor())
                .addPathPatterns("/**");    // 拦截所有请求，通过判断是否有 @UserLoginToken 注解 决定是否需要登录
    }

    @Bean
    public AuthInterceptor authenticationInterceptor() {
        return new AuthInterceptor();
    }
}