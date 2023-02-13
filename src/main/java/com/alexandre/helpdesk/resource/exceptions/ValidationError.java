package com.alexandre.helpdesk.resource.exceptions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ValidationError extends StandardError{

	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage>errors = new ArrayList<>();

	public ValidationError() {
		super();
	}

	public ValidationError(Date date, Integer status, String error, String message, String path) {
		super(date, status, error, message, path);
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addErrors(String fieldName, String message) {
		this.errors.add(new FieldMessage(fieldName, message));
	}
	
	

}
