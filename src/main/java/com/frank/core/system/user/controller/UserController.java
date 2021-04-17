package com.frank.core.system.user.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frank.core.log.annotation.SysLog;
import com.frank.core.system.user.entity.User;
import com.frank.core.system.user.service.UserService;
import com.frank.framework.common.JsonResult;
import com.frank.framework.valid.Update;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 	用户列表
	 * @param user
	 * @return
	 */
	@SysLog("查询用户列表")
	@RequestMapping("/list")
	public JsonResult list(User user) {
		PageHelper.startPage(user.getPage(), user.getLimit());
		List<User> list = userService.list(user);
		PageInfo<User> pageInfo = new PageInfo<>(list);
		return JsonResult.success(pageInfo);
	}
	
	/**
	 * 	新增
	 * @param user
	 * @return
	 */
	@SysLog("新增用户")
	@PreAuthorize("hasAuthority('sys:user:insert')")
	@RequestMapping("/insert")
	public JsonResult insert(@Validated @RequestBody User user) {
		// 新增用户默认密码123456
		user.setPassword("123456");
		userService.insert(user);
		return JsonResult.success();
	}
	
	/**
	 * 	更新
	 * @param user
	 * @return
	 */
	@SysLog("更新用户")
	@PreAuthorize("hasAuthority('sys:user:update')")
	@RequestMapping("/update")
	public JsonResult update(@Validated(value = Update.class) @RequestBody User user) {
		userService.update(user);
		return JsonResult.success();
	}
	
	/**
	 * 新增用户状态
	 * @param user
	 * @return
	 */
	@SysLog("新增用户状态")
	@PreAuthorize("hasAuthority('sys:user:update')")
	@RequestMapping("/updateStatus")
	public JsonResult updateStatus(@Validated(value = Update.class) @RequestBody User user) {
		userService.updateStatus(user);
		return JsonResult.success();
	}
	
	/**
	 * 	删除
	 * @param id
	 * @return
	 */
	@SysLog("删除用户")
	@PreAuthorize("hasAuthority('sys:user:delete')")
	@RequestMapping("/delete")
	public JsonResult delete(@Validated @NotNull(message = "id不能为空") long id) {
		userService.delete(id);
		return JsonResult.success();
	}
	
	/**
	 * 	根据id查询
	 * @param id
	 * @return
	 */
	@SysLog("根据id查询用户")
	@RequestMapping("/selectById")
	public JsonResult selectById(@Validated @NotNull(message = "id不能为空") long id) {
		User user = userService.selectById(id);
		return JsonResult.success(user);
	}
	
	/**
	 *	 批量删除
	 * @param ids
	 * @return
	 */
	@SysLog("批量删除用户")
	@PreAuthorize("hasAuthority('sys:user:delete')")
	@RequestMapping("/batchDelete")
	public JsonResult batchDelete(@RequestBody List<Long> idList) {
		userService.batchDelete(idList);
		return JsonResult.success();
	}
	
}
