package com.frank.framework.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class AddressUtils {
	
	/**
	 * 	淘宝ip地址库
	 * @see https://ip.taobao.com/instructions
	 */
	public static final String OUTGETIPINFO_URL = "https://ip.taobao.com/outGetIpInfo";
	public static final String ACCESSKEY = "alibaba-inc";

	/**
	 * https://ip.taobao.com/outGetIpInfo?ip=120.234.61.154&accessKey=alibaba-inc
	 * {
		    "data": {
		        "area": "",
		        "country": "中国",
		        "isp_id": "100025",
		        "queryIp": "120.234.61.154",
		        "city": "深圳",
		        "ip": "120.234.61.154",
		        "isp": "移动",
		        "county": "",
		        "region_id": "440000",
		        "area_id": "",
		        "county_id": null,
		        "region": "广东",
		        "country_id": "CN",
		        "city_id": "440300"
		    },
		    "msg": "query success",
		    "code": 0
		}
		code的值的含义为，0：成功，1：服务器异常，2：请求参数异常，3：服务器繁忙，4：个人qps超出。
	 * @Author chenhuazhan
	 * @Date: 2021年4月17日 下午4:16:09 
	 * @Description: 根据ip解析地理位置
	 * @param ip
	 * @return
	 */
	public static String getCityByIp(String ip) {
		try {
			String url = OUTGETIPINFO_URL + "?ip=" + ip + "&accessKey=" + ACCESSKEY;
			String formRequest = HttpUtils.getFormRequest(url);
			JSONObject parseObject = JSON.parseObject(formRequest);
			int code = parseObject.getIntValue("code");
			if (code == 0) {
				JSONObject jsonObject = parseObject.getJSONObject("data");
				String region = jsonObject.getString("region");
				String city = jsonObject.getString("city");
				if ("内网IP".equals(city)) {
					return city;
				}else {
					return region + " " +city;
				} 
			}
		} catch (Exception e) {
			return "未知";
		}
		return "未知";
	}
}
