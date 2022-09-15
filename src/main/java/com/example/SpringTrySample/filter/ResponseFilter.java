package com.example.SpringTrySample.filter;

import com.example.SpringTrySample.entity.JsonSampleData;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ResponseFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        res.addHeader("Json情報", String.valueOf(new JsonSampleData("2", "tuika", "taro")));
        System.out.println("headerにJson情報を追加しました");
        chain.doFilter(req,res);
    }
}
