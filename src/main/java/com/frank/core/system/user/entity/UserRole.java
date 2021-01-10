package com.frank.core.system.user.entity;

import java.io.Serializable;

/**
 * 	用户角色关联实体
 * @author Frank
 *
 */
public class UserRole implements Serializable{

	private static final long serialVersionUID = -7730593303081370964L;

	private Long userId; // 用户id
	
	private Long roleId; // 角色id

	public UserRole() {
		super();
	}

	public UserRole(Long userId, Long roleId) {
		super();
		this.userId = userId;
		this.roleId = roleId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	

	
	
	
}
