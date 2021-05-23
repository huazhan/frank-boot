package com.frank.framework.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.alibaba.druid.support.json.JSONUtils;
import com.frank.core.log.service.LoginLogService;
import com.frank.framework.common.JsonResult;

/**
 * 登录成功返回JSON格式数据
 * @author Frank
 *
 */
@Component
public class JsonAuthenticationSuccessHandler implements AuthenticationSuccessHandler{
	
	private static final Logger logger = LoggerFactory.getLogger(JsonAuthenticationSuccessHandler.class);
	
	@Autowired
	private LoginLogService loginLogService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		logger.info("登录成功！！！！！！！！！！！！！！！！！！！！");
		
		// 记录登录日志
		loginLogService.insert(request);
		
		response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        JsonResult jsonResult = JsonResult.success();
        response.getWriter().println(JSONUtils.toJSONString(jsonResult));
        response.getWriter().flush();
		
	}

}
