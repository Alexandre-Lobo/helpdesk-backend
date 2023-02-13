package com.alexandre.helpdesk.resource.exceptions;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.alexandre.helpdesk.service.exceptions.DataIntegrationViolationException;
import com.alexandre.helpdesk.service.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException ex, HttpServletRequest request){
		
		StandardError error = new StandardError(new Date(), HttpStatus.NOT_FOUND.value(), 
				"Object not found", ex.getMessage(), request.getRequestURI());
				

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		
	}
	
	@ExceptionHandler(DataIntegrationViolationException.class)
	public ResponseEntity<StandardError> dataIntegrationViolationException(DataIntegrationViolationException ex, HttpServletRequest request){
		
		StandardError error = new StandardError(new Date(), HttpStatus.BAD_REQUEST.value(), 
				"Violação de dados", ex.getMessage(), request.getRequestURI());
				

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validationErros(MethodArgumentNotValidException ex, HttpServletRequest request){
		
		ValidationError errors = new ValidationError(new Date(), HttpStatus.BAD_REQUEST.value(), 
				"Validation Error", "Erro na validação dos campos", request.getRequestURI());
				
		for(FieldError x: ex.getBindingResult().getFieldErrors()) {
			errors.addErrors(x.getField(), x.getDefaultMessage());
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
		
	}

}
