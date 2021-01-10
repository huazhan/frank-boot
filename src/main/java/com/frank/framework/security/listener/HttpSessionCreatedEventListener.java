package com.frank.framework.security.listener;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.web.session.HttpSessionCreatedEvent;
import org.springframework.stereotype.Component;

import com.frank.framework.security.util.SessionManageUtil;

/**
 * session创建监听器
 * @author Frank
 *
 */
@Component
public class HttpSessionCreatedEventListener implements ApplicationListener<HttpSessionCreatedEvent>{
	
	private static final Logger log = LoggerFactory.getLogger(HttpSessionCreatedEventListener.class);
	
	@Autowired
	private SessionManageUtil sessionManageUtil;

	@Override
	public void onApplicationEvent(HttpSessionCreatedEvent event) {
		HttpSession session = event.getSession();
		log.info("监听到新建session："+session.getId());
		sessionManageUtil.createdSession(session.getId(), session);
	}

}
