package com.alexandre.helpdesk.resource.exceptions;

import java.io.Serializable;
import java.util.Date;


public class StandardError implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Date date;
	private Integer status;
	private String error;
	private String message;
	private String path;
	
	public StandardError() {
		super();
	}

	public StandardError(Date date, Integer status, String error, String message, String path) {
		super();
		this.date = date;
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}

	public Date getTimestamp() {
		return date;
	}

	public void setTimestamp(Date date) {
		this.date = date;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	
	

}
