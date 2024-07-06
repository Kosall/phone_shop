package org.kosal.phoneshop.kosal1_phoneshop.exception;

import org.springframework.core.MethodParameter;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

public class MethodArgumentInvalid extends MethodArgumentNotValidException {

	public MethodArgumentInvalid(MethodParameter parameter, BindingResult bindingResult) {
				
		super(parameter, bindingResult);
		// TODO Auto-generated constructor stub
	}
	

}
