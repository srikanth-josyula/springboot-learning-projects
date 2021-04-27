package com.sample.exceptions;

public class ExceptionResponse {
	private int status;
	private String message;
	private String details;

	public ExceptionResponse(int status, String message, String details) {
		super();
		this.status = status;
		this.message = message;
		this.details = details;
	}

	public int getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}

}
