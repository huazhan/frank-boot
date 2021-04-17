
package com.frank.core.log.entity;

/**
 * 系统操作日志
 * @author Frank
 *
 */
public class SysLog {

	private Long id; // 主键id
	
	private String username; // 用户名：admin

    private String ip; // ip地址：172.18.196.123
    
    private String address; // 操作地点：内网IP

    private String uri; // 请求接口：/user/list
    
    private String url; // 请求接口完整地址：http://47.107.184.125:8089/user/list

    private String method; // 请求方式：GET

    private String params; // 参数：[com.frank.core.system.user.entity.User@1b6444b1]
    
    private String clazz; // 所在类及方法：com.frank.core.system.user.controller.UserController.list

    private String describe; // 方法描述信息：查询用户列表

    private String os; // 系统：Windows 10 or Windows Server 2016
    
    private String browser; // 浏览器：Chrome
    
    private String createTime; // 操作时间

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

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

    

}
