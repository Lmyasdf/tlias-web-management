package com.itheima.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //1.获取请求路径
        String uri = request.getRequestURI();
        //2.判断请求路径是否包含login
        if(uri.contains("/login")){
            //放行
            filterChain.doFilter(request, response);
            return;
        }

        //3.获取header中的token
        String token = request.getHeader("token");

        //4.判断令牌是否存在
        if(token == null || token.isEmpty()){
            //返回401
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        //5.校验令牌正确性
        if(!token.equals("123456")){
            //返回401
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        //6.放行
        filterChain.doFilter(request, response);
    }
}
