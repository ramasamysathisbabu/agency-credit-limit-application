package com.clip.errorhandling;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration  
public class AppConfig extends WebMvcConfigurerAdapter  {  

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(new AuthorizationHandlerInterceptor()).addPathPatterns("/creditcard/limits/eligibility");
    }
}
