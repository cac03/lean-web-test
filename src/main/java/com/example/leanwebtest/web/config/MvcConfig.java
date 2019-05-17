package com.example.leanwebtest.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Empty custom {@link WebMvcConfigurer mvc configuration}.
 * But also may contribute to the "lean" tests
 * (for example by configuring resource handlers)
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
}
