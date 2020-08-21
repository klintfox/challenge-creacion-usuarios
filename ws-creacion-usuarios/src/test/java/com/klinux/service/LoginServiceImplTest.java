package com.klinux.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import com.klinux.entity.User;
import com.klinux.repository.LoginRepository;
import com.klinux.util.GeneralMessages;
import com.klinux.util.UtilValidations;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

class LoginServiceImplTest {

	@Autowired
	private LoginRepository loginRepository;

//	@Test
//	void testSaveUser() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testSaveNewUser() {
//		UsuarioDto userDto = new UsuarioDto();
//		userDto.se
//		fail("Not yet implemented");
//	}	

	@Test
	void saveUser() {
		boolean flag = false;
		String token = getJWTToken("klint@gmail.cl");
		User user = new User();
		user.setName("Klint");
		user.setEmail("Roman");
		user.setPassword("Pruebas22");
		user.setCreated(new Date());
		user.setModified(new Date());
		user.setLastLogin(new Date());
		user.setIsactive(GeneralMessages.IS_ACTIVE);
		user.setToken(token);
		user = loginRepository.save(user);
		if (user != null) {
			flag = true;
		}
		assert (!flag);
	}

	@Test
	void emailValidator() {
		String email = "klint@gmail.cl";
		boolean flag = UtilValidations.emailValidator(email);
		assertTrue(flag);
	}

	@Test
	void passwordValidator() {
		String password = "";
		boolean flag = UtilValidations.passwordValidator(password);
		assertTrue(flag);
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
