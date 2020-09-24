package com.klinux.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceBadRequestException extends Exception {

	private static final long serialVersionUID = 292317171077184479L;

	public ResourceBadRequestException(String message) {
		super(message);
	}

}