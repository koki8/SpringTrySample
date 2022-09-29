package com.example.SpringTrySample.filter;

import com.example.SpringTrySample.entity.JsonSampleData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ParameterChangeConfirmationFilter implements Filter {

    private String variableParameter;

    public ParameterChangeConfirmationFilter(String variableParameter){
        this.variableParameter = variableParameter;
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        Logger logger = LoggerFactory.getLogger(ParameterChangeConfirmationFilter.class);
        chain.doFilter(req,res);
        logger.info(variableParameter);
    }
}
