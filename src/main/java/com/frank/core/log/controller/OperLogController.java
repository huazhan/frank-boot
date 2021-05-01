package com.frank.core.log.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@RequestMapping("/list")
	public JsonResult list(OperLog operLog) {
		PageHelper.startPage(operLog.getPage(), operLog.getLimit());
		List<OperLog> list = operLogService.list(operLog);
		PageInfo<OperLog> pageInfo = new PageInfo<>(list);
		return JsonResult.success(pageInfo);
	}
	
	@GetMapping("/selectById")
	public JsonResult selectById(@Validated @NotNull(message = "id不能为空") long id) {
		OperLog operLog = operLogService.selectById(id);
		return JsonResult.success(operLog);
	}
}