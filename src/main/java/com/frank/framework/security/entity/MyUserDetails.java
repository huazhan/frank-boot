package com.frank.framework.security.entity;


public class MyUserDetails{
	
	private Long id; // 主键ID

    private String username; // 用户账号

    private String password; // 用户密码

    private String status; // 状态：0-禁用，1-正常

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	


}
