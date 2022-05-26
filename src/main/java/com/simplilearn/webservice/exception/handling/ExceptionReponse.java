package com.simplilearn.webservice.exception.handling;

import java.util.Date;

public class ExceptionReponse {
	
	private String message;
	private String status;
	private Date timestamp;
	private String trace;
	
	public ExceptionReponse() {}	
	public ExceptionReponse(String message, String status, String trace) {
		super();
		this.message = message;
		this.status = status;
		this.timestamp = new Date();
		this.trace = trace;
	}
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getTrace() {
		return trace;
	}
	public void setTrace(String trace) {
		this.trace = trace;
	}
	
}
