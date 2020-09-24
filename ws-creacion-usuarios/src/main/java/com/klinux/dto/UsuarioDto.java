package com.klinux.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class UsuarioDto implements Serializable {

	private static final long serialVersionUID = -5069229940876613178L;
	private String name;
	private String email;
	private String password;
	private List<PhonesDto> phones;

	public boolean validateEmailPattern(String email) {
		boolean flag = false;
		String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+cl";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		String response = (matcher.matches() ? "valid" : "invalid");
		if (response.equals("valid")) {
			flag = true;
		}
		return flag;
	}

	public boolean validatePasswordPattern(String password) {
		boolean flag = false;
		// ([A-Z]{1})|([a-z])|([0-9]{2})
		String regex = "^([A-Z])([a-z]).{5}([0-9]{2})";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(password);
		String response = (matcher.matches() ? "valid" : "invalid");
		if (response.equals("valid")) {
			flag = true;
		}
		return flag;
	}

	public String generateToken(String username) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
		String token = Jwts.builder().setId("klinuxJWT").setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();
		return token;
	}

	public UsuarioDto() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<PhonesDto> getPhones() {
		return phones;
	}

	public void setPhones(List<PhonesDto> phones) {
		this.phones = phones;
	}

}