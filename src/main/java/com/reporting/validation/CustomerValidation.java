package com.reporting.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.reporting.model.Customer;

public class CustomerValidation implements Validator{

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return Customer.class.equals(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		// TODO Auto-generated method stub
		
	}

}
