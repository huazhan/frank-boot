package com.frank.framework.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.alibaba.druid.support.json.JSONUtils;
import com.frank.framework.common.JsonResult;

/**
 * 	未登录，或者登录过期 返回JSON格式数据
 * @author Frank
 *
 */
@Component
public class JsonAuthenticationEntryPoint implements AuthenticationEntryPoint{
	
	private static final Logger log = LoggerFactory.getLogger(JsonAuthenticationEntryPoint.class);

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		log.error(authException.getMessage(), authException);
		
		response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        JsonResult jsonResult = JsonResult.error("未登录，或者登录过期");
        response.getWriter().println(JSONUtils.toJSONString(jsonResult));
        response.getWriter().flush();
	}

}
