package com.cybs.bs.common.Global;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName MyMvcConfig
 * @Description TODO
 * @Version 1.0
 **/
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       /* registry.addInterceptor(new MyInterceptor())
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/user/user/login")
                .excludePathPatterns("/renren-api/")
                .excludePathPatterns("/user/user/helloworld")
                .excludePathPatterns("/user/helloworld")
                .excludePathPatterns("/error")
                *//*.excludePathPatterns("/user/loginOut")
                .excludePathPatterns("/user/testjson")
                .excludePathPatterns("/user/user/testjson")*//*
                .addPathPatterns("/**")
        ;*/

    }

    /*@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }*/

}

