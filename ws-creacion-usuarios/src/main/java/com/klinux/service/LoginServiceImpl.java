package com.klinux.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klinux.dto.ResponseDto;
import com.klinux.dto.UsuarioDto;
import com.klinux.entity.Phone;
import com.klinux.entity.User;
import com.klinux.exception.ResourceBadRequestException;
import com.klinux.repository.LoginRepository;
import com.klinux.repository.PhoneRepository;
import com.klinux.util.GeneralMessages;

@Service
public class LoginServiceImpl implements LoginService {

	private ResponseDto response;
	private UsuarioDto usuarioDto = new UsuarioDto();

	@Autowired
	private LoginRepository loginRepository;

	@Autowired
	private PhoneRepository phoneRepository;

	@Override
	public ResponseDto save(UsuarioDto request) throws Exception {
		response = new ResponseDto();
		validateEmailPattern(request);
		return response;
	}

	private void validateEmailPattern(UsuarioDto request) throws Exception {
		boolean flag = false;
		flag = usuarioDto.validateEmailPattern(request.getEmail());
		if (flag) {
			validatePasswordPattern(request);
		}
		else {
			throw new ResourceBadRequestException(GeneralMessages.ERROR_EMAIL_FORMAT);
		}
	}

	private void validatePasswordPattern(UsuarioDto request) throws Exception {
		boolean flag = false;
		flag = usuarioDto.validatePasswordPattern(request.getPassword());
		if (flag) {
			findUserbyEmail(request);
		} else {
			throw new ResourceBadRequestException(GeneralMessages.ERROR_PASSWORD_FORMAT);
		}
	}

	public void findUserbyEmail(UsuarioDto usuario) throws Exception {
		User userDb = loginRepository.findByEmail(usuario.getEmail());
		if (userDb == null) {
			saveUser(usuario);
		} else {
			throw new ResourceBadRequestException(GeneralMessages.ERROR_USER_EXISTS);
		}
	}

	public void saveUser(UsuarioDto userDto) throws Exception {
		String token = usuarioDto.generateToken(userDto.getEmail());
		User user = new User();
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setCreated(new Date());
		user.setModified(new Date());
		user.setLastLogin(new Date());
		user.setIsactive(GeneralMessages.IS_ACTIVE);
		user.setToken(token);
		user = loginRepository.save(user);
		if (user != null && userDto.getPhones().size() > 0)
			savePhonesOfUSer(userDto, user);
	}

	public void savePhonesOfUSer(UsuarioDto userDto, User user) throws Exception {
		for (int i = 0; i < userDto.getPhones().size(); i++) {
			Phone phone = new Phone();
			phone.setPhoneNumber(userDto.getPhones().get(i).getNumber());
			phone.setCitycode(userDto.getPhones().get(i).getCitycode());
			phone.setCountrycode(userDto.getPhones().get(i).getCountrycode());
			phone.setUser(user);
			phone = phoneRepository.save(phone);
			if (phone == null)
				response.setMensaje(GeneralMessages.ERROR);
		}
		sendResponse(user);
	}

	public void sendResponse(User user) {
		response.setId(user.getId());
		response.setCreated(user.getCreated());
		response.setModified(user.getModified());
		response.setLastLogin(user.getLastLogin());
		response.setToken(user.getToken());
		response.setIsactive(user.getIsactive());
		response.setMensaje(GeneralMessages.EXITOSO);
	}

	public ResponseDto getResponse() {
		return response;
	}

	public void setResponse(ResponseDto response) {
		this.response = response;
	}

}