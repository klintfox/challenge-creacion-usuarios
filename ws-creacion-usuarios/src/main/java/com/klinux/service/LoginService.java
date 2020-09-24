package com.klinux.service;

import com.klinux.dto.ResponseDto;
import com.klinux.dto.UsuarioDto;

public interface LoginService {

	ResponseDto save(UsuarioDto user) throws Exception;

}
