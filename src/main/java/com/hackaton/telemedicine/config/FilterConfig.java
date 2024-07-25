package com.hackaton.telemedicine.config;


import com.hackaton.telemedicine.filter.SessionValidationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<SessionValidationFilter> loggingFilter() {
        FilterRegistrationBean<SessionValidationFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new SessionValidationFilter());
        registrationBean.addUrlPatterns("/api/*");

        return registrationBean;
    }
}
