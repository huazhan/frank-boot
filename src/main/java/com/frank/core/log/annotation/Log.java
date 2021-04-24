package com.frank.core.log.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.frank.core.log.enums.BusinessModule;
import com.frank.core.log.enums.BusinessType;


/**
 * 系统操作日志注解
 * @author Frank
 *
 */
@Target(ElementType.METHOD)//注解放置的目标位置,METHOD是可注解在方法级别上
@Retention(RetentionPolicy.RUNTIME)//注解在哪个阶段执行
public @interface Log {
	
	/**
	 * 方法描述
	 * @return
	 */
	String description() default "";
	
	/**
     * 模块 
     */
    public BusinessModule businessModule() default BusinessModule.OTHER;

    /**
     * 功能
     */
    public BusinessType businessType() default BusinessType.OTHER;
}
