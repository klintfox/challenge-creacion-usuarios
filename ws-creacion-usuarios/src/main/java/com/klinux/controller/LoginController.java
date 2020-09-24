package com.klinux.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.klinux.dto.ResponseDto;
import com.klinux.dto.UsuarioDto;
import com.klinux.exception.ResourceBadRequestException;
import com.klinux.service.LoginService;

@RestController
public class LoginController {

	@Autowired
	private LoginService loginService;

	@PostMapping("login")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<ResponseDto> saveNewUser(@RequestBody UsuarioDto user)
			throws Exception, ResourceBadRequestException {
		ResponseDto response = new ResponseDto();
		try {
			response = loginService.save(user);
		} catch (Exception e) {			
			throw new Exception("" + e.getMessage());
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
