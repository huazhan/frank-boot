package com.frank.core.system.role.entity;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.frank.core.base.BaseEntity;
import com.frank.core.system.menu.entity.Menu;
import com.frank.framework.valid.Update;

/**
 * 角色Entity
 * @author Frank
 *
 */
public class Role extends BaseEntity {

	private static final long serialVersionUID = 251314747588774760L;

	@NotNull(message = "id不能为空", groups = Update.class)
	private Long id; // 主键ID
	
	@NotBlank(message = "角色名称不能为空")
	private String name; // 角色名称
	
	@NotBlank(message = "角色key值不能为空")
	private String roleKey; // key值
	
	private String status; // 状态：0-禁用，1-启用
	
	private String menuIds; // 扩展字段：菜单ids
	
	private List<Menu> menuList; // 扩展字段：菜单列表

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRoleKey() {
		return roleKey;
	}

	public void setRoleKey(String roleKey) {
		this.roleKey = roleKey;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}

	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

}
