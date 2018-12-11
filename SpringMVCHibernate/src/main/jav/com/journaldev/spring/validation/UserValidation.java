package com.journaldev.spring.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.journaldev.spring.model.User;

public class UserValidation implements Validator{

	@Override
	public boolean supports(Class<?> arg0) {
		return User.class.equals(arg0);
	}

	@Override
	public void validate(Object arg0, Errors errors) {
	}

}
