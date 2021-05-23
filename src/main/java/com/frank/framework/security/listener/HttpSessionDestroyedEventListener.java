package com.frank.framework.security.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.web.session.HttpSessionDestroyedEvent;
import org.springframework.stereotype.Component;

import com.frank.core.log.service.LoginLogService;
import com.frank.framework.security.util.SessionManageUtil;

/**
 * session销毁监听器
 * @author Frank
 *
 */
@Component
public class HttpSessionDestroyedEventListener implements ApplicationListener<HttpSessionDestroyedEvent>{
	
	private static final Logger log = LoggerFactory.getLogger(HttpSessionDestroyedEventListener.class);

	@Autowired
	private SessionManageUtil sessionManageUtil;
	
	@Autowired
	private LoginLogService loginLogService;
	
	@Override
	public void onApplicationEvent(HttpSessionDestroyedEvent event) {
		
		String sessionId = event.getSession().getId();
		log.info("监听到销毁session："+sessionId);
		sessionManageUtil.destroyedSession(sessionId);
		
		// 更新登录日志，根据sessionId将在线状态(online)更新为1-离线
		loginLogService.updateBySessionId(sessionId);
	}

}
