package com.itmayiedu.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
@Override
public void addResourceHandlers(ResourceHandlerRegistry registry) {
//映射图片保存地址
registry.addResourceHandler("/upload/**").addResourceLocations("file:E:\\headImage\\");
registry.addResourceHandler("/js/**").addResourceLocations("file:E:\\js\\");
}
}