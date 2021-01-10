package com.frank.core.system.menu.entity;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * 菜单树形结构
 * @author Frank
 *
 */
public class MenuTree implements Serializable{
	
	private static final long serialVersionUID = 3032844138454394489L;

	private String id; // 主键ID
	
	private String parentId; // 父菜单ID
	
	private String parentName; // 扩展字段：上级菜单名称
	
	private String name; // 菜单名称
	private String title; // 菜单名称
	
	private String url; // 请求地址
	
	private String href; // 请求地址
	
	private String type; // 菜单类型：1-目录，2-菜单，3-按钮
	
	private String openType; // 打开方式：_blank-新窗口，_iframe-页签
	
	private String status; // 状态：0-隐藏，1-显示
	
	private String perms; // 权限标识
	
	private String icon; // 菜单图标
	
	private String sort; // 排序
	
	private String checkArr;
	
	private List<MenuTree> children;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		if (StringUtils.isNotBlank(type)) {
			if("1".equals(type)) {
				return "0";
			}else {
				return "1";
			}
		}
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
		this.icon = icon;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public List<MenuTree> getChildren() {
		return children;
	}

	public void setChildren(List<MenuTree> children) {
		this.children = children;
	}

	public String getOpenType() {
		return openType;
	}

	public void setOpenType(String openType) {
		this.openType = openType;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getCheckArr() {
		return checkArr;
	}

	public void setCheckArr(String checkArr) {
		this.checkArr = checkArr;
	}

	
}
