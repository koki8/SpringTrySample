package com.example.MybatisTrySample.config;

import com.example.MybatisTrySample.interceptor.LoggingHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 実装したLogginHandlerInterceptorをBean定義
     *
     */
    @Bean
    LoggingHandlerInterceptor loggingHandlerInterceptor(){
        return new LoggingHandlerInterceptor();
    }

    /**
     * インターセプターとしてLogginHandlerInterceptorを登録
     *
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(loggingHandlerInterceptor());
    }
}
