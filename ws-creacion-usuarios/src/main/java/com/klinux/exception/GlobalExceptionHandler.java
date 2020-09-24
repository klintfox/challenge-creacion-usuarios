package com.klinux.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceBadRequestException.class)
	public ResponseEntity<?> resourceBadRequestException(ResourceBadRequestException ex, WebRequest request) {
		String mensaje = ex.getMessage();
		return new ResponseEntity<>(mensaje, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
		String mensaje = ex.getMessage();
		return new ResponseEntity<>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
