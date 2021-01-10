package com.frank.core.system.menu.entity;

import javax.validation.constraints.NotBlank;

import com.frank.core.base.BaseEntity;

/**
 * 菜单Entity
 * @author Frank
 *
 */
public class Menu extends BaseEntity{

	private static final long serialVersionUID = 3494605094159710477L;
	
	private Long id; // 主键ID
	
	private Long parentId; // 父菜单ID
	
	private String parentName; // 扩展字段：上级菜单名称
	
	@NotBlank(message = "菜单名称不能为空")
	private String name; // 菜单名称
	
	private String url; // 请求地址
	
	@NotBlank(message = "菜单类型不能为空")
	private String type; // 菜单类型：1-目录，2-菜单，3-按钮
	
	private String openType; // 打开方式：_blank-新窗口，_iframe-页签
	
	private String status; // 状态：0-隐藏，1-显示
	
	private String perms; // 权限标识
	
	private String icon; // 菜单图标
	
	private String sort; // 排序

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPerms() {
		return perms;
	}

	public void setPerms(String perms) {
		this.perms = perms;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = "layui-icon " + icon;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getOpenType() {
		return openType;
	}

	public void setOpenType(String openType) {
		this.openType = openType;
	}
	
	

}
