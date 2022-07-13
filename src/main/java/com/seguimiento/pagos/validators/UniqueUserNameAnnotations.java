package com.seguimiento.pagos.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = UniqueUserNameValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUserNameAnnotations {

	String message() default "{encuesta.constrain.email.unique.message}"; // mensaje por defecto de error
	
	Class<?>[] groups() default{}; // Anotacion estandar para los validator
	
	Class<? extends Payload>[] payload() default {}; // Anotacion estandar para los validator 
	
}
