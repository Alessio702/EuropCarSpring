package com.example.demo.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = Min0Impl.class)
@Documented
public @interface Min0 {
	public String message() default "Il campo deve essere maggiore di 0!";

	public float errore() default 0.0f;

	public Class<?>[] groups() default {};

	public abstract Class<? extends Payload>[] payload() default {};
}
