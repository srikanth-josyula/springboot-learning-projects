package com.sample.com.sample.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler({ HttpRequestMethodNotSupportedException.class, MissingServletRequestParameterException.class,
			MethodArgumentNotValidException.class, MissingPathVariableException.class })
	public ResponseEntity<Object> handleAllExceptions(final Exception ex, final WebRequest request) {
		if (ex instanceof HttpRequestMethodNotSupportedException) {
			return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
		} else if (ex instanceof MissingServletRequestParameterException) {
			return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ExceptionHandler(CustomGenericException.class)
	protected ResponseEntity<Object> handleCustomGenericException(CustomGenericException ex, final WebRequest request) {
		return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}