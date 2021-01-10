package com.frank.framework.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 数据源配置
 * @author Frank
 *
 */
@Configuration
public class DataSourceConfig {

	/**
	 * 主数据源，在多数据源的环境下主数据源需加上@Primary注解
	 * @return
	 */
	@ConfigurationProperties(prefix="spring.datasource.druid")
	@Bean
	@Primary
	public DruidDataSource dataSource(){
		return new DruidDataSource();
	}
	
}
