package com.frank.framework.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 	用户信息操作工具类
 * @author Frank
 *
 */
public class UserUtil {

	/**
	 * 	获取用户登录名
	 * @return
	 */
	public static String getCurrentUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String name = authentication.getName();
		return name;
	}
}
