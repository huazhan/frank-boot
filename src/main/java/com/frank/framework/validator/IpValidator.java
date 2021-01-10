package com.frank.framework.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import com.frank.framework.annotation.Ip;

/**
 * ip地址校验
 * @author Frank
 *
 */
public class IpValidator implements ConstraintValidator<Ip, String> {
	
	private Pattern pattern = Pattern.compile("([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}");

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return StringUtils.isNotBlank(value) ? pattern.matcher(value).matches() : true;
	}
}
