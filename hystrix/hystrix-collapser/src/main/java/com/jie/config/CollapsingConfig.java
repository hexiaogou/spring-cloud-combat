package com.jie.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.Controller;

/**
 * @author hexiaogou
 * @classname CollapsingConfig
 * @description collapsing config
 * @date 2022/3/14 13:55
 */
@Configuration
public class CollapsingConfig {

    @Bean
    @ConditionalOnClass(Controller.class)
    public HystrixContextInterceptor userContextInterceptor() {
        return new HystrixContextInterceptor();
    }

    @Configuration
    @ConditionalOnClass(Controller.class)
    public class WebMvcConfig extends WebMvcConfigurerAdapter {
        @Autowired
        HystrixContextInterceptor userContextInterceptor;

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(userContextInterceptor);
        }
    }
}
