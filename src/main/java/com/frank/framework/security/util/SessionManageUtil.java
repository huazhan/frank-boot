package com.frank.framework.security.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

@Component
public class SessionManageUtil {
	
	private Map<String,HttpSession> httpSessionMap = new HashMap <>();

	/**
	 * 创建 httpSession
	 * @param sessionid
	 * @param httpSession
	 * @return
	 */
	public HttpSession createdSession(String sessionid, HttpSession httpSession) {
		httpSessionMap.put(sessionid, httpSession);
		return httpSession;
	}
	
	/**
	 * 销毁 httpSession
	 * @param sessionid
	 */
	public void destroyedSession(String sessionid) {
		httpSessionMap.remove(sessionid);
	}
	
	/**
	 * 改变 httpSession
	 * @param oldSessionid
	 * @param newSessionid
	 */
	public void changeSession(String oldSessionid, String newSessionid) {
		HttpSession httpSession = httpSessionMap.get(oldSessionid);
		httpSessionMap.put(newSessionid, httpSession);
		httpSessionMap.remove(oldSessionid);
	}
	
	/**
	 * 获取 HttpSession
	 * @param sessionId
	 * @return
	 */
	public HttpSession getSession(String sessionId){
        return httpSessionMap.get(sessionId);
    }

    public Set<String> getSessionIdSet(){
		Set<String> set = httpSessionMap.keySet();
		return set;
	}
	
}
