package com.frank.core.log.aspect;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.frank.core.log.annotation.SysLog;
import com.frank.framework.util.AddressUtils;
import com.frank.framework.util.IpUtils;
import com.frank.framework.util.UserUtils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;


@Component
@Aspect
public class SysLogAspect {
	
	private static final Logger logger = LoggerFactory.getLogger(SysLogAspect.class);

	@Pointcut("@annotation(com.frank.core.log.annotation.SysLog)")
    public void sysLogPoinCut() {
    }
	
	@Before("sysLogPoinCut()")
    public void dobefore(JoinPoint joinPoint){
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        
        logger.info("==============前置通知开始==============");
        
        String username = UserUtils.getCurrentUsername(); // 操作人
        String uri = request.getRequestURI().toString(); // 请求接口uri
        //String url = request.getRequestURL().toString(); // 请求接口完整url
        String ip = IpUtils.getIpAddr(request); // 获取用户的ip
        String adress = AddressUtils.getCityByIp(ip);
        String method = request.getMethod();
        String description = "";
        try {
        	description = getControllerMethodDescription(joinPoint);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        String args = getRequestArgs(joinPoint);
        String clazz = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        String uaStr = request.getHeader("User-Agent");
        UserAgent ua = UserAgentUtil.parse(uaStr);
        String os = ua.getOs().toString(); // Windows 7
        String browser = ua.getBrowser().toString(); // Chrome
        
        logger.info("操作人：{}",username);
        logger.info("请求接口 ：{}",uri);
        //logger.info("请求接口 url：{}",url);
        logger.info("来源ip：{}",ip);
        logger.info("请求方式：{}",method);
        logger.info("方法描述信息：{}",description);
        logger.info("请求参数：{}",args);
        logger.info("请求类：{}",clazz);
        logger.info("系统：{}",os);
        logger.info("浏览器：{}",browser);
        logger.info("操作地点：{}",adress);
        
        
        
    }
	
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
	
	
	public static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();//目标方法名
        Object[] arguments = joinPoint.getArgs();
        Class<?> targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method:methods) {
            if (method.getName().equals(methodName)){
                Class<?>[] clazzs = method.getParameterTypes();
                if (clazzs.length==arguments.length){
                    description = method.getAnnotation(SysLog.class).value();
                    break;
                }
            }
        }
        return description;
    }
	
	

}
