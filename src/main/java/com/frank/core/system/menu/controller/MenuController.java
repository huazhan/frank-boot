package com.frank.core.system.menu.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frank.core.log.annotation.Log;
import com.frank.core.log.enums.BusinessModule;
import com.frank.core.log.enums.BusinessType;
import com.frank.core.system.menu.entity.Menu;
import com.frank.core.system.menu.entity.MenuTree;
import com.frank.core.system.menu.service.MenuService;
import com.frank.framework.common.JsonResult;
import com.frank.framework.util.UserUtils;
import com.frank.framework.valid.Update;

import cn.hutool.core.date.DateUtil;

/**
 * 菜单Controller
 * @author Frank
 *
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
	
	@Autowired
	private MenuService menuService;

	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	@GetMapping("/selectById")
	public JsonResult selectById(@Validated @NotNull(message = "id不能为空") long id) {
		Menu menu = menuService.selectById(id);
		return JsonResult.success(menu);
	}
	
	/**
	 * 查询列表
	 * @param menu
	 * @return
	 */
	@Log(description = "查询菜单列表", businessModule = BusinessModule.MENU, businessType = BusinessType.SELECT)
	@RequestMapping("/list")
	public JsonResult list(Menu menu) {
		List<Menu> list = menuService.list(menu);
		return JsonResult.success(list);
	}
	
	/**
	 * 新增
	 * @param menu
	 * @return
	 */
	@Log(description = "新增菜单", businessModule = BusinessModule.MENU, businessType = BusinessType.INSERT)
	@PreAuthorize("hasAuthority('sys:menu:insert')")
	@PostMapping("/insert")
	public JsonResult insert(@Validated @RequestBody Menu menu) {
		menuService.insert(menu);
		return JsonResult.success();
	}
	
	/**
	 * 更新
	 * @param menu
	 * @return
	 */
	@Log(description = "更新菜单", businessModule = BusinessModule.MENU, businessType = BusinessType.UPDATE)
	@PreAuthorize("hasAuthority('sys:menu:update')")
	@PostMapping("/update")
	public JsonResult update(@Validated(value = Update.class) @RequestBody Menu menu) {
		menuService.update(menu);
		return JsonResult.success();
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@Log(description = "根据id删除菜单", businessModule = BusinessModule.MENU, businessType = BusinessType.DELETE)
	@PreAuthorize("hasAuthority('sys:menu:delete')")
	@PostMapping("/delete")
	public JsonResult delete(@Validated @NotNull(message = "id不能为空") long id) {
		menuService.delete(id);
		return JsonResult.success();
	}
	
	/**
	 * 	批量删除
	 * @param idList
	 * @return
	 */
	@Log(description = "批量删除菜单", businessModule = BusinessModule.MENU, businessType = BusinessType.DELETE)
	@PreAuthorize("hasAuthority('sys:menu:delete')")
	@RequestMapping("/batchDelete")
	public JsonResult batchDelete(@RequestBody List<Long> idList) {
		menuService.batchDelete(idList);
		return JsonResult.success();
	}
	
	/**
	 * 查询树形结构
	 * @return
	 */
	@RequestMapping("/selectMenuTree")
	public List<MenuTree> selectMenuTree() {
		List<MenuTree> list = menuService.selectMenuTree();
		return list;
	}
	
	/**
	 * 树形结构，添加角色时用到
	 * @return
	 */
	@RequestMapping("/selectMenuTreeForRole")
	public JsonResult selectMenuTreeForRole() {
		List<MenuTree> list = menuService.selectMenuTreeForRole();
		return JsonResult.success(list);
	}
	
}
