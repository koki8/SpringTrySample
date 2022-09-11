package com.example.SpringTrySample.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  インターセプターの設定
 *
 *  コントローラーの実行前後に処理を挟み込んでいる
 *  HandlerInterceptorを実装することで作成できる
 */
public class LoggingHandlerInterceptor implements HandlerInterceptor {

    Logger logger = LoggerFactory.getLogger(LoggingHandlerInterceptor.class);

    /**
     * コントローラー実行前にログを出力
     *
     * @param request current HTTP request
     * @param response current HTTP response
     * @param handler chosen handler to execute, for type and/or instance evaluation
     * @return true
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler
                             ) throws Exception{
        String message = String.format("コントローラー実行前ログ：　[%s] %-4s %s",
                request.getLocalAddr(), request.getMethod(), request.getRequestURI());
        logger.info(message);
        return true;
    }

    /**
     * コントローラー実行後にログを出力
     *
     * @param request current HTTP request
     * @param response current HTTP response
     * @param handler the handler (or {@link HandlerMethod}) that started asynchronous
     * execution, for type and/or instance examination
     * @param modelAndView the {@code ModelAndView} that the handler returned
     * (can also be {@code null})
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request,
                              HttpServletResponse response,
                              Object handler,
                              @Nullable ModelAndView modelAndView) throws Exception{
        String message = String.format("コントローラー実行後ログ：　[%s] %-4s %s",
        request.getLocalAddr(), request.getMethod(), request.getRequestURI());
        logger.info(message);
    }

}
