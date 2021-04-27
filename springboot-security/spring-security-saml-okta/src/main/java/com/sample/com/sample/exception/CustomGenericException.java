package com.sample.com.sample.exception;

public class CustomGenericException extends Exception {

	private static final long serialVersionUID = 1L;
	private String erorrDescription;

	public CustomGenericException(String erorrDescription) {
		super(erorrDescription);
		this.erorrDescription = erorrDescription;
	}

	public String getErorrDescription() {
		return erorrDescription;
	}

	public void setErorrDescription(String erorrDescription) {
		this.erorrDescription = erorrDescription;
	}
}
