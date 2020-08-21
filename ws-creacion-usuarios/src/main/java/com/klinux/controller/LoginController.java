package com.klinux.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.klinux.dto.ResponseDto;
import com.klinux.dto.UsuarioDto;
import com.klinux.service.LoginService;

@RestController
public class LoginController {

	private static Logger log = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private LoginService loginService;

	@PostMapping("newuser")
	public ResponseDto saveUser(@RequestBody UsuarioDto user) {
		ResponseDto response = new ResponseDto();		
		try {
			response = loginService.saveUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}	
		return response;
	}
}
