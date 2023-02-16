package com.alexandre.helpdesk.resource.exceptions;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
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
				
		ex.getBindingResult().getFieldErrors().forEach(v ->errors.addErrors(v.getField(), v.getDefaultMessage())); 

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<StandardError> handleConstraintViolationException(ConstraintViolationException ex, HttpServletRequest request){
		
		ValidationError errors = new ValidationError(new Date(), HttpStatus.BAD_REQUEST.value(), 
				"Validation Error", "Erro na validação dos campos", request.getRequestURI());
		
		ex.getConstraintViolations().forEach(v -> errors.addErrors(v.getPropertyPath().toString(), v.getMessage()));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}

	@ExceptionHandler(TransactionSystemException.class)
	public ResponseEntity<StandardError> handleConstraintViolationException(TransactionSystemException ex, HttpServletRequest request){
		
		ValidationError error = new ValidationError(new Date(), HttpStatus.BAD_REQUEST.value(), 
				"Validation Error", ex.getMessage(), request.getRequestURI());
		
		//ex.getConstraintViolations().forEach(v -> errors.addErrors(v.getPropertyPath().toString(), v.getMessage()));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

}
