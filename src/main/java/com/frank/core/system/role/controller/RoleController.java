package com.frank.core.system.role.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frank.core.system.role.entity.Role;
import com.frank.core.system.role.service.RoleService;
import com.frank.framework.common.JsonResult;
import com.frank.framework.valid.Update;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 	角色Controller 
 * @author Frank
 *
 */
@RestController
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	private RoleService roleService;

	/**
	 * 	根据id查询
	 * @param id
	 * @return
	 */
	@RequestMapping("/selectById")
	public JsonResult selectById(@Validated @NotNull(message = "id不能为空") long id) {
		Role role = roleService.selectById(id);
		return JsonResult.success(role);
	}
	
	/**
	 * 	查询列表
	 * @param role
	 * @return
	 */
	@RequestMapping("/list")
	public JsonResult list(Role role) {
		PageHelper.startPage(role.getPage(), role.getLimit());
		List<Role> list = roleService.list(role);
		PageInfo<Role> pageInfo = new PageInfo<>(list);
		return JsonResult.success(pageInfo);
	}
	
	/**
	 * 	查询列表-不分页
	 * @param role
	 * @return
	 */
	@RequestMapping("/selectList")
	public JsonResult selectList(Role role) {
		List<Role> list = roleService.list(role);
		return JsonResult.success(list);
	}
	
	/**
	 * 	新增
	 * @param role
	 * @return
	 */
	@PreAuthorize("hasAuthority('sys:role:insert')")
	@RequestMapping("/insert")
	public JsonResult insert(@Validated @RequestBody Role role) {
		roleService.insert(role);
		return JsonResult.success();
	}
	
	/**
	 * 	更新
	 * @param role
	 * @return
	 */
	@PreAuthorize("hasAuthority('sys:role:update')")
	@RequestMapping("/update")
	public JsonResult update(@Validated(value = Update.class) @RequestBody Role role) {
		roleService.update(role);
		return JsonResult.success();
	}
	
	/**
	 * 更新状态
	 * @param role
	 * @return
	 */
	@PreAuthorize("hasAuthority('sys:role:update')")
	@RequestMapping("/updateStatus")
	public JsonResult updateStatus(@Validated(value = Update.class) @RequestBody Role role) {
		roleService.updateStatus(role);
		return JsonResult.success();
	}
	
	/**
	 * 	根据id删除
	 * @param id
	 * @return
	 */
	@PreAuthorize("hasAuthority('sys:role:delete')")
	@RequestMapping("/delete")
	public JsonResult delete(@Validated @NotNull(message = "id不能为空") long id) {
		roleService.delete(id);
		return JsonResult.success();
	}
	
	/**
	 * 	批量删除
	 * @param idList
	 * @return
	 */
	@PreAuthorize("hasAuthority('sys:role:delete')")
	@RequestMapping("/batchDelete")
	public JsonResult batchDelete(@RequestBody List<Long> idList) {
		roleService.batchDelete(idList);
		return JsonResult.success();
	}
	
}
