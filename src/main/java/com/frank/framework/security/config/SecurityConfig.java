package com.frank.framework.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.frank.framework.security.handler.JsonAuthenticationEntryPoint;
import com.frank.framework.security.handler.JsonAuthenticationFailureHandler;
import com.frank.framework.security.handler.JsonAuthenticationSuccessHandler;

/**
 * security配置
 * @author Frank
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled=true,jsr250Enabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JsonAuthenticationEntryPoint jsonAuthenticationEntryPoint;
	
	@Autowired
	private JsonAuthenticationSuccessHandler jsonAuthenticationSuccessHandler;
	
	@Autowired
	private JsonAuthenticationFailureHandler jsonAuthenticationFailureHandler;
	
	@Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Autowired
    private SessionRegistry sessionRegistry;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/component/**","/admin/**","/common/**","/pear/**");
	}
	
	// 认证用户的来源(内存或者数据库)
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.anyRequest().authenticated() //.permitAll()
		.and()
			.formLogin().permitAll()
			.loginPage("/login.html")
			.loginProcessingUrl("/login")
			.successHandler(jsonAuthenticationSuccessHandler) // 自定义登录成功处理
			.failureHandler(jsonAuthenticationFailureHandler) // 自定义登录失败处理
//		.and()
//			.logout()
//			.logoutSuccessHandler(logoutSuccessHandler) // 自定义退出登录处理
        .and()
			.headers().frameOptions().sameOrigin()
		.and()
			.sessionManagement()
	        // 无效session跳转
	        //.invalidSessionUrl("index")
	        // 同一用户只能在一个地方登录，后面登的会把前面登的强行退出
	        //.maximumSessions(1)
	        // session过期跳转
	        //.expiredUrl("index")
	        //.expiredSessionStrategy()
	        //.sessionRegistry(sessionRegistry)
	        //.and()
	    .and()
	     	.csrf().disable();
		
        // 未登陆时返回 JSON 格式的数据给前端
        http.httpBasic().authenticationEntryPoint(jsonAuthenticationEntryPoint);

	}
}
