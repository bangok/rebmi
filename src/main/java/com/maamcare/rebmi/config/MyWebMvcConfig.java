package com.maamcare.rebmi.config;


import com.maamcare.rebmi.component.GlobalHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 接管springMVC
 * */
@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private GlobalHandlerInterceptor globalHandlerInterceptor;
    /**
     * 全局拦截器
     * */
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(globalHandlerInterceptor).addPathPatterns("/**");
              //  .excludePathPatterns("/user/login","/user/register");
    }



}
