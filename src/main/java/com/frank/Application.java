package com.frank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }
	
	@Bean
	public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
		ServletListenerRegistrationBean<HttpSessionEventPublisher> registration = new ServletListenerRegistrationBean<>();
		registration.setListener(new HttpSessionEventPublisher());
		return registration;
	}

}
