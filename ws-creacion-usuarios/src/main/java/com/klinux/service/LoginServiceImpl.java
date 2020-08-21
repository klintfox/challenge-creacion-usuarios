package com.klinux.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klinux.dto.ResponseDto;
import com.klinux.dto.UsuarioDto;
import com.klinux.entity.Phone;
import com.klinux.entity.User;
import com.klinux.repository.LoginRepository;
import com.klinux.util.GeneralMessages;
import com.klinux.util.UtilValidations;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginRepository loginRepository;

	@Override
	public ResponseDto saveUser(UsuarioDto userDto) throws Exception {
		ResponseDto response = new ResponseDto();
		boolean flag = false;
		try {
			// 1 Email validation
			System.out.println("llego: " + userDto.toString());
			flag = UtilValidations.emailValidator(userDto.getEmail());
			if (flag) {
				// 2 Password Validator
				flag = UtilValidations.passwordValidator(userDto.getPassword());
				if (flag) {
					// 3 User Validation
					User userDb = loginRepository.findByEmail(userDto.getEmail());
					if (userDb != null) {
						// 4 Save New User
						User user = new User();
						user.setName(userDto.getName());
						user.setEmail(userDto.getEmail());
						user.setPassword(userDto.getPassword());
						user.setCreated(new Date());
						user.setModified(null);
						user.setLastLogin(new Date());
						List<Phone> phones = new ArrayList<>();
						for (int i = 0; i < userDto.getPhone().size(); i++) {
							Phone phone = new Phone();
							phone.setNumber(userDto.getPhone().get(i).getNumber());
							phone.setCitycode(userDto.getPhone().get(i).getCitycode());
							phone.setCountrycode(userDto.getPhone().get(i).getCountrycode());
							phones.add(phone);
						}
						user.setPhone(phones);
						loginRepository.save(user);
						response.setMensaje(GeneralMessages.EXITOSO);
					} else {
						// 5 Update User
						userDb.setPassword(userDto.getPassword());
						userDb.setModified(new Date());
						userDb.setLastLogin(new Date());
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

//	String token =loginService.save(); getJWTToken(UsuarioDto	);
	/**
	 * Método que genera u
	 * 
	 * @param username
	 * @return
	 */
//	private String getJWTToken(String username) {
//		String secretKey = "mySecretKey";
//		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");		
//		String token = Jwts
//				.builder()
//				.setId("softtekJWT")
//				.setSubject(username)
//				.claim("authorities",
//						grantedAuthorities.stream()
//								.map(GrantedAuthority::getAuthority)
//								.collect(Collectors.toList()))
//				.setIssuedAt(new Date(System.currentTimeMillis()))
//				.setExpiration(new Date(System.currentTimeMillis() + 600000))
//				.signWith(SignatureAlgorithm.HS512,
//						secretKey.getBytes()).compact();
//
//		return "Bearer " + token;
//	}

}
