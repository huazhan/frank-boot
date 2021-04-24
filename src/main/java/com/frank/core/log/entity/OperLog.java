
package com.frank.core.log.entity;

import com.frank.core.base.BaseEntity;
import com.frank.framework.util.StaticMapUtil;

/**
 * 系统操作日志
 * @author Frank
 *
 */
public class OperLog extends BaseEntity{

	private static final long serialVersionUID = 1L;

	private Long id; // 主键id
	
	private String username; // 用户名：admin

    private String ip; // 操作人ip地址：172.18.196.123
    
    private String address; // 操作地点：内网IP

    private String uri; // 请求接口：/user/list
    
    private String url; // 请求接口完整地址：http://192.168.1.1：8080/user/list

    private String method; // 请求方式：GET

    private String params; // 参数：[com.frank.core.system.user.entity.User@1b6444b1]
    
    private String clazz; // 所在类及方法：com.frank.core.system.user.controller.UserController.list

    private String description; // 方法描述信息：查询用户列表

    private String os; // 系统：Windows 10 or Windows Server 2016
    
    private String browser; // 浏览器：Chrome
    
    private String module; // 操作模块： 0-其他，1-用户管理，2-角色管理，3-菜单管理，4-部门管理
    @SuppressWarnings("unused")
	private String moduleText; // 扩展字段：用于页面显示操作模块中文
    
    private String type; // 操作类型： 0-其他，1-查询，2-新增，3-修改，4-删除，5-导入，6-导出，7-授权
    @SuppressWarnings("unused")
    private String typeText; // 扩展字段：用于页面操作类型显示中文
    
    private String status; // 操作状态：0-正常，1-异常
    @SuppressWarnings("unused")
    private String statusText; // 扩展字段：用于页面操作状态显示中文
    
    private String errorMsg; // 异常信息
    
    private String jsonResult; // 返回结果
    
	public String getModuleText() {
		return StaticMapUtil.moduleMap.get(module);
	}

	public String getTypeText() {
		return StaticMapUtil.typeMap.get(type);
	}
	
	public String getStatusText() {
		return StaticMapUtil.statusMap.get(status);
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
	
	public String getModule() {
		return module;
	}
	
	public void setModule(String module) {
		this.module = module;
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

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(String jsonResult) {
		this.jsonResult = jsonResult;
	}

}
