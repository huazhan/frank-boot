package com.frank.framework.security.entity;

/**
 * 角色和权限
 * @author Frank
 *
 */
public class RoleAndPerms {

	private String roleKey; // 角色key
	
	private String perms; // 权限标识

	private String menuName; // 菜单名称

	public String getRoleKey() {
		return roleKey;
	}

	public void setRoleKey(String roleKey) {
		this.roleKey = roleKey;
	}

	public String getPerms() {
		return perms;
	}

	public void setPerms(String perms) {
		this.perms = perms;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
}
