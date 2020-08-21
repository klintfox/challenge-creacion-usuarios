package com.klinux.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import com.klinux.dto.ResponseDto;
import com.klinux.dto.UsuarioDto;
import com.klinux.entity.Phone;
import com.klinux.entity.User;
import com.klinux.repository.LoginRepository;
import com.klinux.repository.PhoneRepository;
import com.klinux.util.GeneralMessages;
import com.klinux.util.UtilValidations;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginRepository loginRepository;

	@Autowired
	private PhoneRepository phoneRepositort;

	@Override
	public ResponseDto saveUser(UsuarioDto userDto) throws Exception {
		ResponseDto response = new ResponseDto();
		boolean flag = false;
		try {
			System.out.println("User_Request: " + userDto.toString());
			flag = UtilValidations.emailValidator(userDto.getEmail());
			if (flag) {
				flag = UtilValidations.passwordValidator(userDto.getPassword());
				if (flag) {
					User userDb = loginRepository.findByEmail(userDto.getEmail());
					if (userDb == null) {
						response = saveNewUser(userDto);
					} else {
						response.setMensaje(GeneralMessages.ERROR_USER_EXISTS);
					}
				} else {
					response.setMensaje(GeneralMessages.ERROR_PASSWORD_FORMAT);
				}
			} else {
				response.setMensaje(GeneralMessages.ERROR_EMAIL_FORMAT);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	public ResponseDto saveNewUser(UsuarioDto userDto) throws Exception {
		ResponseDto response = new ResponseDto();
		String token = getJWTToken(userDto.getEmail());
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
		List<Phone> phones = new ArrayList<>();
		for (int i = 0; i < userDto.getPhones().size(); i++) {
			Phone phone = new Phone();
			phone.setNumber(userDto.getPhones().get(i).getNumber());
			phone.setCitycode(userDto.getPhones().get(i).getCitycode());
			phone.setCountrycode(userDto.getPhones().get(i).getCountrycode());
			phone.setUser(user);
			phones.add(phone);
			phoneRepositort.save(phone);
		}
		response.setId(user.getId());
		response.setCreated(user.getCreated());
		response.setModified(user.getModified());
		response.setLastLogin(user.getLastLogin());
		response.setToken(user.getToken());
		response.setIsactive(user.getIsactive());
		response.setMensaje(GeneralMessages.EXITOSO);
		return response;
	}

	private String getJWTToken(String username) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
		String token = Jwts.builder().setId("klinuxJWT").setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();
		return "Bearer " + token;
	}

}
