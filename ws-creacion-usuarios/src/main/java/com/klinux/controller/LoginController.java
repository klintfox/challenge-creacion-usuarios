package com.klinux.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.klinux.dto.ResponseDto;
import com.klinux.dto.UsuarioDto;
import com.klinux.service.LoginService;

@RestController
public class LoginController {

	private static Logger log = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private LoginService loginService;

	@PostMapping("login")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<ResponseDto> saveNewUser(@RequestBody UsuarioDto user) {
		ResponseDto response = new ResponseDto();
		try {
			response = loginService.save(user);
		} catch (Exception e) {
			log.error(new Throwable().getStackTrace()[0].getMethodName() + " - " + e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
