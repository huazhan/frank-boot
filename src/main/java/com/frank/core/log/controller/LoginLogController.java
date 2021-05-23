package com.frank.core.log.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frank.core.log.entity.LoginLog;
import com.frank.core.log.entity.OperLog;
import com.frank.core.log.service.LoginLogService;
import com.frank.framework.common.JsonResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 登录日志
 * @author Frank
 *
 */
@RestController
@RequestMapping("/loginlog")
public class LoginLogController {
	
	@Autowired
	private LoginLogService loginLogService;
	
	/**
	 * 查询登录日志列表
	 * @param loginLog
	 * @return
	 */
	@RequestMapping("/list")
	public JsonResult list(LoginLog loginLog) {
		PageHelper.startPage(loginLog.getPage(), loginLog.getLimit());
		List<LoginLog> list = loginLogService.list(loginLog);
		PageInfo<LoginLog> pageInfo = new PageInfo<>(list);
		return JsonResult.success(pageInfo);
	}

}
