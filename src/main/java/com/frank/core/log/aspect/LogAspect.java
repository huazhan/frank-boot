package com.frank.core.log.aspect;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.frank.core.log.annotation.Log;
import com.frank.core.log.entity.OperLog;
import com.frank.core.log.mapper.OperLogMapper;
import com.frank.framework.util.AddressUtils;
import com.frank.framework.util.IpUtils;
import com.frank.framework.util.UserUtils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;


@Component
@Aspect
public class LogAspect {
	
	private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);
	
	@Autowired
	private OperLogMapper operLogMapper;

	@Pointcut("@annotation(com.frank.core.log.annotation.Log)")
    public void sysLogPoinCut() {
    }
	
	@Before("sysLogPoinCut()")
    public void dobefore(JoinPoint joinPoint){
		
    }
	
	/**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "sysLogPoinCut()", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Object jsonResult)
    {
        handleLog(joinPoint, null, jsonResult);
    }
	
	/**
     * 拦截异常操作
     * 
     * @param joinPoint 切点
     * @param e 异常
     */
    @AfterThrowing(value = "sysLogPoinCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e)
    {
        handleLog(joinPoint, e, null);
    }
	
	
	protected void handleLog(final JoinPoint joinPoint, final Exception e, Object jsonResult) {
		
		// 获得注解
		Log log = getAnnotationOperLog(joinPoint);
        if (log == null){
            return;
        }
		
		// 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        
        logger.info("==============记录操作日志==============");
        
        String username = UserUtils.getCurrentUsername(); // 操作人
        String uri = request.getRequestURI().toString(); // 请求接口uri
        String url = request.getRequestURL().toString(); // 请求接口完整url
        String ip = IpUtils.getIpAddr(request); // 获取用户的ip
        String address = AddressUtils.getCityByIp(ip);
        String method = request.getMethod();
        String description = getAnnotationOperLogDescription(joinPoint);
        String args = getRequestArgs(joinPoint);
        String clazz = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        String uaStr = request.getHeader("User-Agent");
        UserAgent ua = UserAgentUtil.parse(uaStr);
        String os = ua.getOs().toString(); // Windows 7
        String browser = ua.getBrowser().toString(); // Chrome
        int module = log.businessModule().ordinal(); // 操作模块：
        int type = log.businessType().ordinal(); // 操作类型：
        String status = e == null ? "0" : "1"; 
        String errorMsg = e == null ? "" : StrUtil.sub(e.getMessage(), 0, 2000);
        String jsonResultStr = JSON.toJSONString(jsonResult);
        
        logger.info("操作人：{}",username);
        logger.info("请求接口 ：{}",uri);
        logger.info("请求接口 url：{}",url);
        logger.info("来源ip：{}",ip);
        logger.info("请求方式：{}",method);
        logger.info("方法描述信息：{}",description);
        logger.info("请求参数：{}",args);
        logger.info("请求类：{}",clazz);
        logger.info("系统：{}",os);
        logger.info("浏览器：{}",browser);
        logger.info("操作地点：{}",address);
        logger.info("操作模块：{}",module);
        logger.info("操作类型：{}",type);
        logger.info("操作状态：{}",status);
        logger.info("异常信息：{}",errorMsg);
        logger.info("返回结果：{}",jsonResultStr);

        OperLog operLog = new OperLog();
        operLog.setUsername(username); // 用户名：admin
        operLog.setIp(ip); // 操作人ip地址：172.18.196.123
        operLog.setAddress(address); // 操作地点：内网IP
        operLog.setUri(uri); // 请求接口：/user/list
        operLog.setUrl(url); // 请求接口完整地址：http://192.168.1.1：8080/user/list
        operLog.setMethod(method); // 请求方式：GET
        operLog.setParams(args); // 参数：[com.frank.core.system.user.entity.User@1b6444b1]
        operLog.setClazz(clazz); // 所在类及方法：com.frank.core.system.user.controller.UserController.list
        operLog.setDescription(description); // 方法描述信息：查询用户列表
        operLog.setOs(os); // 系统：Windows 10 or Windows Server 2016
        operLog.setBrowser(browser); // 浏览器：Chrome
        operLog.setCreateTime(DateUtil.date()); // 操作时间
        operLog.setModule(String.valueOf(module)); // 操作模块
        operLog.setType(String.valueOf(type)); // 操作类型
        operLog.setStatus(status); // 操作状态：0-正常，1-异常
        operLog.setErrorMsg(errorMsg); // 异常信息
        operLog.setJsonResult(jsonResultStr); // 返回结果
        
        operLogMapper.insert(operLog);
        
	}
	
	
	/**
	 * 获取请求参数
	 * @param joinPoint
	 * @return
	 */
	public static String getRequestArgs(JoinPoint joinPoint) {
		List<Object> argList = new ArrayList<>();
        for (Object arg : joinPoint.getArgs()) {
        	argList.add(arg);
        }
        String args = JSON.toJSONString(argList);
        args = StrUtil.removePrefix(args,"[");
        args = StrUtil.removeSuffix(args,"]");
        return args;
	}
	
	/**
	 * 获取注解的描述信息
	 * @param joinPoint
	 * @return
	 * @throws Exception
	 */
	public static String getAnnotationOperLogDescription(JoinPoint joinPoint) {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();//目标方法名
        Object[] arguments = joinPoint.getArgs();
        String description = "";
		try {
			Class<?> targetClass = Class.forName(targetName);
			Method[] methods = targetClass.getMethods();
			for (Method method:methods) {
				if (method.getName().equals(methodName)){
					Class<?>[] clazzs = method.getParameterTypes();
					if (clazzs.length==arguments.length){
						description = method.getAnnotation(Log.class).description();
						break;
					}
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        return description;
    }
	
	/**
     * 是否存在注解，如果存在就获取
     */
    private Log getAnnotationOperLog(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null){
            return method.getAnnotation(Log.class);
        }
        return null;
    }
	
}
