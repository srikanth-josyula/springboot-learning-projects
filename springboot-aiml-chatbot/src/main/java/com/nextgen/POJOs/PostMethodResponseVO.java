package com.sample.POJOs;


public class PostMethodResponseVO {
	
	private int statusCode;
	private String responseString;
	private String message;
	
	
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getResponseString() {
		return responseString;
	}
	public void setResponseString(String responseString) {
		this.responseString = responseString;
	}
	@Override
	public String toString() {
		return "PostMethodResponseVO [statusCode=" + statusCode + ", responseString=" + responseString + ", message="
				+ message + "]";
	}
	
	
	

}
