package com.example.SpringTrySample.config;

import com.example.SpringTrySample.filter.ResponseFilter;
import com.example.SpringTrySample.interceptor.LoggingHandlerInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.FilterRegistration;

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


    /**
     * Filterの実行順序を指定
     * Spring Boot標準のFilterよりも先に実行されるように指定
     * FilterのOrderの数値が小さい順に実行されるため、Orderに小さな値を設定してやる
     */
    @Bean
    public FilterRegistrationBean requestLoggingBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(requestLoggingFilter());
        filterRegistrationBean.setOrder(Integer.MIN_VALUE);
        return filterRegistrationBean;
    }
    @Bean
    public FilterRegistrationBean responseBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new ResponseFilter());
        filterRegistrationBean.setOrder(Integer.MIN_VALUE + 1);
        return filterRegistrationBean;
    }

    /**
     * Filterの実装
     * リクエストのheader内容をログ出力するためのFilter
     * @return
     */
    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter(){
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();

        /**
         * ログに出力する内容をメソッドで設定できる
         */
//        filter.setIncludeClientInfo(true);  // IPアドレス、HTTPメソッド
//        filter.setIncludeQueryString(true); // クエリパラメーター
        filter.setIncludeHeaders(true); // リクエストヘッダー
//        filter.setIncludePayload(true); // リクエストボディ
//        filter.setMaxPayloadLength(1024);  // リクエストボディの表示サイズ
        return filter;
    }

}
