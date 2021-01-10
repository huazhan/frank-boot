package com.frank.framework.security.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.web.authentication.session.SessionFixationProtectionEvent;
import org.springframework.stereotype.Component;

import com.frank.framework.security.util.SessionManageUtil;

/**
 * session固化保护监听器
 * @author Frank
 *
 */
@Component
public class SessionFixationProtectionEventListener implements ApplicationListener<SessionFixationProtectionEvent>{

	private static final Logger log = LoggerFactory.getLogger(SessionFixationProtectionEventListener.class);
	
	@Autowired
	private SessionManageUtil sessionManageUtil;
	
	@Override
	public void onApplicationEvent(SessionFixationProtectionEvent event) {
		log.info("session固化保护机制,在登录成功后，会将旧的sessionId销毁，重新生成一个新的sessionId");
		String oldSessionid = event.getOldSessionId();
		String newSessionid = event.getNewSessionId();
        // 更改sessionId的值
		log.info("oldSessionid:"+oldSessionid);
		log.info("newSessionid:"+newSessionid);
		sessionManageUtil.changeSession(oldSessionid, newSessionid);
		
	}

}
