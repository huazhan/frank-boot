package com.frank.framework.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.frank.framework.validator.IpValidator;



/**
 * 自定义注解ip地址校验
 * @author Frank
 *
 */
@Constraint(validatedBy = IpValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Ip {

	String message() default "ip地址不合法";
	 
    Class<?>[] groups() default {};
 
    Class<? extends Payload>[] payload() default {};
}
