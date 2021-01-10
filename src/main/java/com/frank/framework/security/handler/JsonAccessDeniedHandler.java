package com.frank.framework.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.alibaba.druid.support.json.JSONUtils;
import com.frank.framework.common.JsonResult;

/**
 * 	操作权限不足时返回JSON数据格式
 * @author Frank
 *
 */
@Component
public class JsonAccessDeniedHandler implements AccessDeniedHandler{

	private static final Logger log = LoggerFactory.getLogger(JsonAccessDeniedHandler.class);
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		
		log.error(accessDeniedException.getMessage(), accessDeniedException);
		response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        JsonResult jsonResult = JsonResult.error(403, "操作权限不足");
        response.getWriter().println(JSONUtils.toJSONString(jsonResult));
        response.getWriter().flush();
		
	}

}
