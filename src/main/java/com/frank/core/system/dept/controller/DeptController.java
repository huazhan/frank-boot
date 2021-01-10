package com.frank.core.system.dept.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frank.core.system.dept.entity.Dept;
import com.frank.core.system.dept.entity.DeptTree;
import com.frank.core.system.dept.service.DeptService;
import com.frank.framework.common.JsonResult;
import com.frank.framework.valid.Update;

/**
 * 	部门Controller
 * @author Frank
 *
 */
@RequestMapping("/dept")
@RestController
public class DeptController {
	
	@Autowired
	private DeptService deptService;
	
	/**
	 * 	根据id查询
	 * @param id
	 * @return
	 */
	@RequestMapping("/selectById")
	public JsonResult selectById(@Validated @NotNull(message = "id不能为空") long id) {
		Dept dept = deptService.selectById(id);
		return JsonResult.success(dept);
	}
	
	/**
	 *	 根据id查询子部门列表（包含本部门）
	 * @param id
	 * @return
	 */
	@RequestMapping("/selectChildrenListById")
	public JsonResult selectChildrenListById(@Validated @NotNull(message = "id不能为空") long id) {
		List<Dept> list = deptService.selectChildrenListById(id);
		return JsonResult.success(list);
	}
	
	/**
	 * 查询列表
	 * @param dept
	 * @return
	 */
	@RequestMapping("/list")
	public JsonResult list(Dept dept) {
		List<Dept> list = deptService.list(dept);
		return JsonResult.success(list);
	}
	
	/**
	 * 	新增
	 * @param dept
	 * @return
	 */
	@PreAuthorize("hasAuthority('sys:dept:insert')")
	@RequestMapping("/insert")
	public JsonResult insert(@Validated @RequestBody Dept dept) {
		deptService.insert(dept);
		return JsonResult.success();
	}
	
	/**
	 * 	更新
	 * @param dept
	 * @return
	 */
	@PreAuthorize("hasAuthority('sys:dept:update')")
	@RequestMapping("/update")
	public JsonResult update(@Validated(value = Update.class) @RequestBody Dept dept) {
		deptService.update(dept);
		return JsonResult.success();
	}
	
	@PreAuthorize("hasAuthority('sys:dept:update')")
	@RequestMapping("/updateStatusByParentId")
	public JsonResult updateStatusByParentId(@Validated(value = Update.class) @RequestBody Dept dept) {
		deptService.updateStatusByParentId(dept);
		return JsonResult.success();
	}
	
	/**
	 * 	删除
	 * @param id
	 * @return
	 */
	@PreAuthorize("hasAuthority('sys:dept:delete')")
	@RequestMapping("/delete")
	public JsonResult delete(@Validated @NotNull(message = "id不能为空") long id) {
		deptService.delete(id);
		return JsonResult.success();
	}
	
	/**
	 *	查询部门树形结构 
	 * @return
	 */
	@RequestMapping("/selectDeptTree")
	public JsonResult selectDeptTree() {
		List<DeptTree> list = deptService.selectDeptTree();
		return JsonResult.success(list);
	}

}
