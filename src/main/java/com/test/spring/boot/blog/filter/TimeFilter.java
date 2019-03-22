package com.test.spring.boot.blog.filter;


import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.xml.crypto.dsig.spec.XPathType;
import java.io.IOException;
import java.util.Date;

//@Component
public class TimeFilter implements  Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("初始化filter。。。");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("开始filter。。。");
        long start = new Date().getTime();
        chain.doFilter(request,response);//请求下个过滤器
        System.out.println("用时"+(new Date().getTime()-start)+"。。。");
        System.out.println("结束1filter。。。");


    }

    @Override
    public void destroy() {
        System.out.println("销毁filter。。。");

    }
}
