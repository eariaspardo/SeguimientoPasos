package com.seguimiento.pagos.exceptions;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import com.seguimiento.pagos.security.modelo.response.ErrorMessage;
import com.seguimiento.pagos.security.modelo.response.ValidationErrors;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;

@ControllerAdvice
public class AppExceptionHandler {
	
	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
    public ResponseEntity<Object> handleValidationErrorException(MethodArgumentNotValidException ex, WebRequest webRequest){

        Map<String, String> errores = new HashMap<>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errores.put(fieldName, errorMessage);
        }

        ValidationErrors validationErrors = new ValidationErrors(errores, new Date());
        
        return new ResponseEntity<>(validationErrors, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { Exception.class, ExpiredJwtException.class })
    public ResponseEntity<Object> handleException(Exception ex, WebRequest webRequest) {
        ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Object> generateException(ResponseStatusException re){
    	ErrorMessage errorMessage = new ErrorMessage(new Date(), "Expiro el Token: "+re.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Object>(errorMessage, re.getStatus());
    }
    
    /*@ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<Object> jwtExceptionExpired(ExpiredJwtException re){
    	ErrorMessage errorMessage = new ErrorMessage(new Date(), "Expiro el Token: "+re.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Object>(errorMessage, HttpStatus.OK);
    }*/
    
    @ExceptionHandler(JwtException.class)
    public ResponseEntity<Object> jwtException(JwtException re){
    	ErrorMessage errorMessage = new ErrorMessage(new Date(), "Expiro el Token: "+re.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Object>(errorMessage, HttpStatus.OK);
    }

}
