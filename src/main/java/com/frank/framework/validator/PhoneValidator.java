package com.frank.framework.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import com.frank.framework.annotation.Phone;


/**
 * 手机号码校验
 * @author Frank
 *
 */
public class PhoneValidator implements ConstraintValidator<Phone, String> {
	
	private Pattern pattern = Pattern.compile("1(([38]\\d)|(5[^4&&\\d])|(4[579])|(7[0135678]))\\d{8}");

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		return StringUtils.isNotBlank(value) ? pattern.matcher(value).matches() : true;
	}
}
