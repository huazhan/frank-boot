package com.frank.core.log.entity;

import com.frank.core.base.BaseEntity;
import com.frank.framework.util.StaticMapUtil;

public class LoginLog  extends BaseEntity{

	private static final long serialVersionUID = 3928865028506338239L;

	private Long id; // 主键id
	
	private String username; // 用户名：admin

	private String sessionId; // sessionId

    private String ip; // 操作人ip地址：172.18.196.123
    
    private String address; // 操作地点：内网IP
    
    private String os; // 系统：Windows 10 or Windows Server 2016
    
    private String browser; // 浏览器：Chrome
    
    private String online; // 在线状态：0-在线，1-离线
    @SuppressWarnings("unused")
    private String onlineText; // 扩展字段：用于页面操作类型显示中文
    
    public String getOnlineText() {
		return StaticMapUtil.onlineMap.get(online);
	}

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

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getOnline() {
		return online;
	}

	public void setOnline(String online) {
		this.online = online;
	}
    
    
}
