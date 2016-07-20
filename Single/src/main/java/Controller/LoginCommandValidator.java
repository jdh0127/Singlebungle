package Controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import spring.Member;

public class LoginCommandValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> clazz){
		return Member.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors){
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required");
		ValidationUtils.rejectIfEmpty(errors, "pwd", "required");
	}
}
