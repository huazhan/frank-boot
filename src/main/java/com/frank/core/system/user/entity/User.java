package com.frank.core.system.user.entity;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.frank.core.base.BaseEntity;
import com.frank.core.system.role.entity.Role;
import com.frank.framework.annotation.Phone;
import com.frank.framework.valid.Update;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 	用户表entity
 * @author Frank
 *
 */
public class User extends BaseEntity {
	
	private static final long serialVersionUID = 3355321377288991554L;

	@NotNull(message="主键ID不能为空",groups = Update.class)
    private Long id; // 主键ID

    @NotNull(message="部门不能为空")
    private Long deptId; // 部门ID
    private String deptName; // 扩展字段：部门名称
    
    private String roleIds; // 扩展字段：角色id字符串逗号拼接
    
    private List<Role> roleList; // 扩展字段：角色列表

    @NotBlank(message="账号不能为空")
    private String username; // 用户账号

    @JsonIgnore
    private String password; // 用户密码

    @NotBlank(message="姓名不能为空")
    private String nickname; // 用户昵称

    private String sex; // 性别：0-女，1-男
    
    @Phone
    private String phone; // 手机号码

    private String email; // 邮箱

    private String status; // 状态：0-禁用，1-正常

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
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

   
}