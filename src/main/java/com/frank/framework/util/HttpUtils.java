package com.frank.framework.util;

import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * Http工具类
 */
public class HttpUtils {

	/**
	 * POST的方式发送json格式的请求（application/json;charset=UTF-8）
	 * @param url 请求的url
	 * @param params 请求参数 Map
	 * @return
	 */
	public static String postJsonRequest(String url,Map<String, Object> params){
		
		RestTemplate restTemplate  = new RestTemplate();
		
		// 新建Http头，add方法可以添加参数
		HttpHeaders httpHeaders = new HttpHeaders();
		
		// 设置以json的方式提交
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		// 设置请求发送方式
		HttpMethod httpMethod = HttpMethod.POST;
		
		// 将请求头部和参数合成一个请求
         HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(params, httpHeaders);
		
        // 执行HTTP请求，将返回的结构使用String类格式化
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, httpMethod, requestEntity,String.class);
        
        return responseEntity.getBody();

	}
	
	/**
	 * POST的方式发送表单格式的请求（application/x-www-form-urlencoded）
	 * @param url 请求的url
	 * @param params 请求参数 Map
	 * @return
	 */
	public static String postFormRequest(String url,MultiValueMap<String, Object> params){
		
		RestTemplate restTemplate  = new RestTemplate();
		
		// 新建Http头，add方法可以添加参数
		HttpHeaders httpHeaders = new HttpHeaders();
		
		// 设置以表单的方式提交
		httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		// 设置请求发送方式
		HttpMethod httpMethod = HttpMethod.POST;
		
		// 将请求头部和参数合成一个请求
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(params, httpHeaders);
		
		// 执行HTTP请求，将返回的结构使用String类格式化
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, httpMethod, requestEntity,String.class);
		
		return responseEntity.getBody();
		
	}
	
	/**
	 * GET的方式发送表单格式的请求（application/x-www-form-urlencoded）
	 * @param url 请求的url
	 * @return
	 */
	public static String getFormRequest(String url){
		
		RestTemplate restTemplate  = new RestTemplate();
		
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
		
		return responseEntity.getBody();
		
	}
}