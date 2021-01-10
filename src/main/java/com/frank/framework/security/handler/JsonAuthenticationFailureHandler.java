package com.frank.framework.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.alibaba.druid.support.json.JSONUtils;
import com.frank.framework.common.JsonResult;

/**
 * 登录失败返回JSON格式数据
 * @author Frank
 *
 */
@Component
public class JsonAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        JsonResult jsonResult = JsonResult.error(exception.getMessage());
        response.getWriter().println(JSONUtils.toJSONString(jsonResult));
        response.getWriter().flush();
	}

}
