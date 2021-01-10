package com.frank.framework.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web配置
 * @author Frank
 *
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	
   /**
    * 配置静态资源,避免静态资源请求被拦截
    */
   public void addResourceHandlers(ResourceHandlerRegistry registry) {
	   
	   registry.addResourceHandler("/**")
  		.addResourceLocations("classpath:/static/")
  		.addResourceLocations("classpath:/templates/");
   }
   
   
}
