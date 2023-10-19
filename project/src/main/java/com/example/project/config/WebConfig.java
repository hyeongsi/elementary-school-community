package com.example.project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/register").setViewName("register");
        registry.addViewController("/schedule").setViewName("schedule");
        registry.addViewController("/timetable").setViewName("timetable");
        registry.addViewController("/timetable3").setViewName("timetable3");
        registry.addViewController("/search").setViewName("schoolSearch");
        registry.addViewController("/blank").setViewName("blank");
        registry.addViewController("/loginTest").setViewName("loginTest");
        registry.addViewController("/login").setViewName("login");
        
    }
}
