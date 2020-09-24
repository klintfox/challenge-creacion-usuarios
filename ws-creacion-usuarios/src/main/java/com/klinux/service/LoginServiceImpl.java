package com.klinux.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klinux.dto.ResponseDto;
import com.klinux.dto.UsuarioDto;
import com.klinux.entity.Phone;
import com.klinux.entity.User;
import com.klinux.repository.LoginRepository;
import com.klinux.repository.PhoneRepository;
import com.klinux.util.GeneralMessages;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginRepository loginRepository;

	@Autowired
	private PhoneRepository phoneRepository;

	private ResponseDto response = new ResponseDto();

	private UsuarioDto usuarioDto = new UsuarioDto();

	@Override
	public ResponseDto saveUser(UsuarioDto request) throws Exception {
		validateEmailPattern(request);
		return response;
	}

	private void validateEmailPattern(UsuarioDto request) throws Exception {
		boolean flag = false;
		flag = usuarioDto.validateEmailPattern(request.getEmail());
		if (flag) {
			validatePasswordPattern(request);
		} else {
			response.setMensaje(GeneralMessages.ERROR_EMAIL_FORMAT);
		}
	}

	private void validatePasswordPattern(UsuarioDto request) throws Exception {
		boolean flag = false;
		flag = usuarioDto.validatePasswordPattern(request.getPassword());
		if (flag) {
			findUserbyEmail(request);
		} else {
			response.setMensaje(GeneralMessages.ERROR_PASSWORD_FORMAT);
		}
	}

	public void findUserbyEmail(UsuarioDto usuario) throws Exception {
		User userDb = loginRepository.findByEmail(usuario.getEmail());
		if (userDb == null) {
			saveNewUser(usuario);
		} else {
			response.setMensaje(GeneralMessages.ERROR_USER_EXISTS);
		}
	}

	@Transactional
	public void saveNewUser(UsuarioDto userDto) throws Exception {
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
			savePhones(userDto, user);
	}

	@Transactional
	public void savePhones(UsuarioDto userDto, User user) throws Exception {
		List<Phone> phones = new ArrayList<>();
		for (int i = 0; i < userDto.getPhones().size(); i++) {
			Phone phone = new Phone();
			phone.setPhoneNumber(userDto.getPhones().get(i).getNumber());
			phone.setCitycode(userDto.getPhones().get(i).getCitycode());
			phone.setCountrycode(userDto.getPhones().get(i).getCountrycode());
			phone.setUser(user);
			phones.add(phone);
			phone = phoneRepository.save(phone);
			if (phone == null)
				response.setMensaje(GeneralMessages.ERROR);
		}
		sendResponse(user);
	}

	public void sendResponse(User user) {
		ResponseDto response = new ResponseDto();
		response.setId(user.getId());
		response.setCreated(user.getCreated());
		response.setModified(user.getModified());
		response.setLastLogin(user.getLastLogin());
		response.setToken(user.getToken());
		response.setIsactive(user.getIsactive());
		response.setMensaje(GeneralMessages.EXITOSO);
	}

}