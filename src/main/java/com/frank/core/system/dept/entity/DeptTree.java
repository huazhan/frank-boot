package com.frank.core.system.dept.entity;

import java.io.Serializable;
import java.util.List;

public class DeptTree implements Serializable{
	
	private static final long serialVersionUID = 407894530024845163L;

	private Long id;
	
	private Long parentId;
	
	private String title;
	
	private Boolean last;
	
	private Boolean disabled;
	
	private String checkArr;
	
	private List<DeptTree> children;
	
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getLast() {
		return last;
	}

	public void setLast(Boolean last) {
		this.last = last;
	}

	public List<DeptTree> getChildren() {
		return children;
	}

	public void setChildren(List<DeptTree> children) {
		this.children = children;
	}

	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	public String getCheckArr() {
		return checkArr;
	}

	public void setCheckArr(String checkArr) {
		this.checkArr = checkArr;
	}
	
}
