package com.frank.framework.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.alibaba.druid.support.json.JSONUtils;
import com.frank.framework.common.JsonResult;

/**
 * 登录成功返回JSON格式数据
 * @author Frank
 *
 */
@Component
public class JsonAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        JsonResult jsonResult = JsonResult.success();
        response.getWriter().println(JSONUtils.toJSONString(jsonResult));
        response.getWriter().flush();
		
	}

}
