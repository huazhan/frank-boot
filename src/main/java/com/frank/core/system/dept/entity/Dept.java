package com.frank.core.system.dept.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.frank.core.base.BaseEntity;
import com.frank.framework.valid.Update;

/**
 * 部门Entity
 * @author Frank
 *
 */
public class Dept extends BaseEntity  {

	private static final long serialVersionUID = -7455960892617859047L;

	@NotNull(message = "id不能为空",groups = Update.class)
	private Long id; // 主键ID
	
	@NotNull(message = "父部门不能为空")
	private Long parentId; // 父部门ID
	
	private String parentName; // 扩展字段：父部门名称
	
	private String ids; // 祖级ID列表
	
	@NotBlank(message = "部门名称不能为空")
	private String name; // 部门名称
	
	private Integer sort; // 显示顺序
	
	private String leader; // 负责人
	
	private String phone; // 联系电话
	
	private String email; // 邮箱
	
	private String status; // 部门状态：0-禁用，1-正常
	
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
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getLeader() {
		return leader;
	}
	public void setLeader(String leader) {
		this.leader = leader;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
	


}
