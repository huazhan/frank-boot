package com.frank.core.route;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 	菜单路由
 * @author Frank
 *
 */
@Controller
public class HomeController {
	
	/**
	 * 	首页跳转
	 * @return
	 */
	@RequestMapping(value={"","/","/index"})
	public String index() {
		return "index"; // 跳转到 index.html
	}
	
	/**
	 * 	readme跳转
	 * @return
	 */
	@RequestMapping("/readme")
	public String readme() {
		return "readme"; // 跳转到 readme.html
	}
	
	// ====== 用户模块相关 ======
	
	/**
	 * 	用户list.html页面
	 * @return
	 */
	@RequestMapping("/user-list")
	public String userList() {
		return "view/system/user/list"; // 跳转到 view/system/user/路径下的 list.html
	}
	
	/**
	 * 	用户add.html页面
	 * @return
	 */
	@RequestMapping("/user-add")
	public String userAdd() {
		return "view/system/user/add";
	}
	
	/**
	 * 	用户edit.html页面
	 * @return
	 */
	@RequestMapping("/user-edit")
	public String userEdit() {
		return "view/system/user/edit";
	}
	
	// ====== 角色模块相关 ======
	/**
	 * 	角色list.html页面
	 * @return
	 */
	@RequestMapping("/role-list")
	public String roleList() {
		return "view/system/role/list";
	}
	
	/**
	 * 	角色add.html页面
	 * @return
	 */
	@RequestMapping("/role-add")
	public String roleAdd() {
		return "view/system/role/add";
	}
	
	/**
	 * 	角色edit.html页面
	 * @return
	 */
	@RequestMapping("/role-edit")
	public String roleEdit() {
		return "view/system/role/edit";
	}
	
	// ====== 菜单模块相关 ======
	
	/**
	 * 	菜单list.html页面
	 * @return
	 */
	@RequestMapping("/menu-list")
	public String menuList() {
		return "view/system/menu/list";
	}
	
	/**
	 * 	菜单add.html页面
	 * @return
	 */
	@RequestMapping("/menu-add")
	public String menuAdd() {
		return "view/system/menu/add";
	}
	
	/**
	 * 	菜单edit.html页面
	 * @return
	 */
	@RequestMapping("/menu-edit")
	public String menuEdit() {
		return "view/system/menu/edit";
	}
	
	// ====== 部门模块相关 ======
	
	/**
	 * 	部门list.html页面
	 * @return
	 */
	@RequestMapping("/dept-list")
	public String deptList() {
		return "view/system/dept/list";
	}
	
	/**
	 * 	部门add.html页面
	 * @return
	 */
	@RequestMapping("/dept-add")
	public String deptAdd() {
		return "view/system/dept/add";
	}
	
	/**
	 * 	部门edit.html页面
	 * @return
	 */
	@RequestMapping("/dept-edit")
	public String deptEdit() {
		return "view/system/dept/edit";
	}
	
	// ====== 操作日志模块相关 ======
	/**
	 * 	操作日志list.html页面
	 * @return
	 */
	@RequestMapping("/operlog-list")
	public String operlogList() {
		return "view/system/operlog/list";
	}
	
	/**
	 * 	操作日志details.html页面
	 * @return
	 */
	@RequestMapping("/operlog-details")
	public String details() {
		return "view/system/operlog/details";
	}
	
	// ====== 登录日志模块相关 ======
	/**
	 * 	登录日志list.html页面
	 * @return
	 */
	@RequestMapping("/loginlog-list")
	public String loginlogList() {
		return "view/system/loginlog/list";
	}
	
	// ====== 监控管理模块相关 ======
	/**
	 * 	系统信息list.html页面
	 * @return
	 */
	@RequestMapping("/monitor-server")
	public String serverInfo() {
		return "view/system/monitor/server";
	}
	
}
