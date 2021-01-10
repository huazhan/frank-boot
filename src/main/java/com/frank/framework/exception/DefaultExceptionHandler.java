package com.frank.framework.exception;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.ModelAndView;

import com.frank.framework.common.JsonResult;

/**
 * 自定义异常处理器
 * @author Frank
 *
 */
@RestControllerAdvice
public class DefaultExceptionHandler{
    
	private static final Logger log = LoggerFactory.getLogger(DefaultExceptionHandler.class);

    /**
     * 请求方式不支持
     */
    @ExceptionHandler({ HttpRequestMethodNotSupportedException.class })
    public JsonResult handleException(HttpRequestMethodNotSupportedException e){
        log.error(e.getMessage(), e);
        return JsonResult.error("不支持' " + e.getMethod() + "'请求");
    }
    
    /**
     * 	权限不足
     * @param e
     * @return
     */
    @ExceptionHandler({ AccessDeniedException.class })
    public JsonResult accessDeniedException(AccessDeniedException e){
    	log.error(e.getMessage(), e);
    	return JsonResult.error("操作权限不足");
    }
    
    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public Object runtimeException(RuntimeException e,HttpServletRequest req){
        log.error("运行时异常:", e);
        // 使用HttpServletRequest中的header检测请求是否为ajax, 如果是ajax则返回json, 如果为非ajax则返回view(即ModelAndView)
		String contentTypeHeader = req.getHeader("Content-Type");
		String acceptHeader = req.getHeader("Accept");
		String xRequestedWith = req.getHeader("X-Requested-With");
		if ((contentTypeHeader != null && contentTypeHeader.contains("application/json"))
				|| (acceptHeader != null && acceptHeader.contains("application/json"))
				|| "XMLHttpRequest".equalsIgnoreCase(xRequestedWith)) {
			return JsonResult.error("运行时异常:" + e.getMessage());
		} else {
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("msg", e.getMessage());
			modelAndView.addObject("url", req.getRequestURL());
			modelAndView.addObject("stackTrace", e.getStackTrace());
			modelAndView.setViewName("error/error");
			return modelAndView;
		}
        
    }
    

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public JsonResult handleException(Exception e){
        log.error(e.getMessage(), e);
        return JsonResult.error("服务器错误，请联系管理员");
    }
    
    /**
     * 参数绑定异常
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    public JsonResult bindException(BindException e){
    	log.error("参数绑定异常", e);
    	BindingResult result = e.getBindingResult();
    	List<FieldError> fieldErrors = result.getFieldErrors();
    	List<String> list = new ArrayList<>();
    	for (FieldError fieldError : fieldErrors) {
    		String field = fieldError.getField();
    		String defaultMessage = fieldError.getDefaultMessage();
    		list.add(field+":"+defaultMessage);
		}
    	return JsonResult.error(list.toString());
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public JsonResult methodArgumentNotValidException(MethodArgumentNotValidException e){
    	log.error("参数绑定异常", e);
    	BindingResult result = e.getBindingResult();
    	List<FieldError> fieldErrors = result.getFieldErrors();
    	List<String> list = new ArrayList<>();
    	for (FieldError fieldError : fieldErrors) {
    		String field = fieldError.getField();
    		String defaultMessage = fieldError.getDefaultMessage();
    		list.add(field+":"+defaultMessage);
    	}
    	return JsonResult.error(list.toString());
    }
    
    /**
     * 缺少请求参数异常
     * @param e
     * @return
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public JsonResult missingServletRequestParameterException(MissingServletRequestParameterException e){
    	log.error("缺少请求参数", e);
        return JsonResult.error("缺少请求参数："+e.getParameterName());
    }
    
    /**
     * 文件上传异常
     * @param e
     * @return
     */
    @ExceptionHandler(MultipartException.class)
    public Map<String, Object> multipartException(MultipartException e){
    	log.error("文件上传异常", e);
    	Map<String, Object> map = new HashMap<String, Object>(16);
    	map.put("success", 0);
		map.put("message", "图片上传失败"
				+ "\n支持图片格式：jpg, jpeg, gif, png, bmp, webp"
				+ "\n文件大小限制：1MB");
    	return map;
    }
    
    
    /**
     * 入参校验异常，需要在目标Controller类上加注解：@Validated
     * @param 
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public JsonResult constraintViolationException(ConstraintViolationException e){
    	log.error("入参校验异常",e);
    	Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
    	List<String> list = new ArrayList<>();
    	for (ConstraintViolation<?> constraintViolation : violations) {
    		list.add(constraintViolation.getMessage());
		}
    	return JsonResult.error(list.toString());
    }
    
    /**
     * 业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public JsonResult businessException(BusinessException e){
        log.error(e.getMessage(), e);
        return JsonResult.error(e.getMessage());
    }
    
    
}
