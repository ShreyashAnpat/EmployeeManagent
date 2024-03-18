package com.api.Exception;

import org.springframework.core.MethodParameter;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

public class MethodArgsNotValidException extends MethodArgumentNotValidException{

	public MethodArgsNotValidException(MethodParameter parameter, BindingResult bindingResult) {
		super(parameter, bindingResult);
		// TODO Auto-generated constructor stub
		
	}

}
