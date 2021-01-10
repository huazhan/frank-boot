package com.frank.core.system.role.entity;

public class RoleMenu {

	private Long roleId; // 角色ID
	
	private Long MenuId; // 菜单ID
	

	public RoleMenu() {
		super();
	}

	public RoleMenu(Long roleId, Long menuId) {
		super();
		this.roleId = roleId;
		MenuId = menuId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getMenuId() {
		return MenuId;
	}

	public void setMenuId(Long menuId) {
		MenuId = menuId;
	}
	
	
}
