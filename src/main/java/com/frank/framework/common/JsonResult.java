package com.frank.framework.common;

import java.util.HashMap;

/**
 * 操作消息提醒
 * @author Frank
 *
 */
public class JsonResult extends HashMap<String, Object> {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 初始化一个新创建的 Message 对象
	 */
	public JsonResult() {}

	/**
	 * 返回错误消息
	 * 
	 * @return 错误消息
	 */
	public static JsonResult error() {
		return error(-1, "操作失败");
	}

	/**
	 * 返回错误消息
	 * 
	 * @param msg 内容
	 * @return 错误消息
	 */
	public static JsonResult error(String msg) {
		return error(-1, msg);
	}

	/**
	 * 返回错误消息
	 * 
	 * @param code 错误码
	 * @param msg  内容
	 * @return 错误消息
	 */
	public static JsonResult error(int code, String msg) {
		JsonResult json = new JsonResult();
		json.put("code", code);
		json.put("msg", msg);
		return json;
	}

	/**
	 * 返回成功消息
	 * 
	 * @param msg 内容
	 * @return 成功消息
	 */
	public static JsonResult success(String msg) {
		JsonResult json = new JsonResult();
		json.put("msg", msg);
		json.put("code", 0);
		return json;
	}

	/**
	 * 返回成功消息
	 * @param obj 对象
	 * @return
	 */
	public static JsonResult success(Object obj) {
		JsonResult json = new JsonResult();
		json.put("msg", "操作成功");
		json.put("data", obj);
		json.put("code", 0);
		return json;
	}

	/**
	 * 返回成功消息
	 * 
	 * @return 成功消息
	 */
	public static JsonResult success() {
		return JsonResult.success("操作成功");
	}

	/**
	 * 返回成功消息
	 * 
	 * @param key   键值
	 * @param value 内容
	 * @return 成功消息
	 */
	@Override
	public JsonResult put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
