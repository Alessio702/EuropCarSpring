package com.example.demo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class Min0Impl implements ConstraintValidator<Min0, Float> {

	private float respectedValue;
	
	@Override
	public boolean isValid(Float value, ConstraintValidatorContext context) {
		boolean importo = false;
		
		if(value != null) {
			if (respectedValue != value)
				importo = true;
		}
		
		return importo;
	}
	
	@Override 
	public void initialize(Min0 min) {
		respectedValue = min.errore();
	}
}
