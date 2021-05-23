package com.frank.framework.util;

import java.util.HashMap;

/**
 * 静态Map工具类，用于翻译各种状态值方便前端显示
 * @author Frank
 *
 */
public class StaticMapUtil {

	// 操作模块： 0-其他，1-用户管理，2-角色管理，3-菜单管理，4-部门管理
	@SuppressWarnings("serial")
	public final static HashMap<String, String> moduleMap = new HashMap<String, String>() {{    
        put("0", "其他");
        put("1", "用户管理");
        put("2", "角色管理");
        put("3", "菜单管理");
        put("4", "部门管理");
    }}; 
    
    // 操作类型： 0-其他，1-查询，2-新增，3-修改，4-删除，5-导入，6-导出，7-授权
    @SuppressWarnings("serial")
	public final static HashMap<String, String> typeMap = new HashMap<String, String>() {{    
        put("0", "其他");
        put("1", "查询");
        put("2", "新增");
        put("3", "更新");
        put("4", "删除");
        put("5", "导入");
        put("6", "导出");
        put("7", "授权");
    }}; 
    
    // 操作状态：0-正常，1-异常
    @SuppressWarnings("serial")
	public final static HashMap<String, String> statusMap = new HashMap<String, String>() {{    
        put("0", "正常");
        put("1", "异常");
    }}; 
    
    // 在线状态：0-在线，1-离线
    @SuppressWarnings("serial")
    public final static HashMap<String, String> onlineMap = new HashMap<String, String>() {{    
    	put("0", "在线");
    	put("1", "离线");
    }}; 
}
