package com.chencx.dynamicdatabasespringboot.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class WebAppConfigurer  implements WebMvcConfigurer {
    @Resource
    private DynamicDatasourceClearInterceptor dynamicDatasourceClearInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(dynamicDatasourceClearInterceptor)
                .addPathPatterns("/**");
    }
}
