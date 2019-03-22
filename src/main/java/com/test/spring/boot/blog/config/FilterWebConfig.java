package com.test.spring.boot.blog.config;
import javax.servlet.*;

import com.test.spring.boot.blog.filter.TimeFilter;
import com.test.spring.boot.blog.interceptors.TimeInterceptors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class FilterWebConfig extends WebMvcConfigurerAdapter {//继承此类是为了添加拦截器做的准备
    @Autowired
    TimeInterceptors timeInterceptors;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInterceptors).addPathPatterns("/blogs");
        
    }

    @Bean
    public FilterRegistrationBean timeFilter(){
        FilterRegistrationBean fb = new FilterRegistrationBean();
        Filter timeFilter = new TimeFilter();
        fb.setFilter(timeFilter);
        List<String > urls = new ArrayList<>();
        urls.add("/*");
        fb.setUrlPatterns(urls);
        return fb;
    }
}
