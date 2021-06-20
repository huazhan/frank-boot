package com.frank.core.log.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frank.core.log.entity.OperLog;
import com.frank.core.log.service.OperLogService;
import com.frank.framework.common.JsonResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 	操作日志
 * @author Frank
 *
 */
@RestController
@RequestMapping("/operLog")
public class OperLogController {
	
	@Autowired
	private OperLogService operLogService;

	/**
	 * 查询操作日志列表
	 * @param operLog
	 * @return
	 */
	@RequestMapping("/list")
	@PreAuthorize("hasAuthority('log:oper:select')")
	public JsonResult list(OperLog operLog) {
		PageHelper.startPage(operLog.getPage(), operLog.getLimit());
		List<OperLog> list = operLogService.list(operLog);
		PageInfo<OperLog> pageInfo = new PageInfo<>(list);
		return JsonResult.success(pageInfo);
	}
	
	/**
	 * 根据id查询操作日志
	 * @param id
	 * @return
	 */
	@GetMapping("/selectById")
	@PreAuthorize("hasAuthority('log:oper:select')")
	public JsonResult selectById(@Validated @NotNull(message = "id不能为空") long id) {
		OperLog operLog = operLogService.selectById(id);
		return JsonResult.success(operLog);
	}
}
