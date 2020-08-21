package com.klinux.dto;

import java.io.Serializable;
import java.util.List;

public class UsuarioDto implements Serializable {

	private static final long serialVersionUID = -5069229940876613178L;
	private String name;
	private String email;
	private String password;
	private List<PhonesDto> phone;

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

	public List<PhonesDto> getPhone() {
		return phone;
	}

	public void setPhone(List<PhonesDto> phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "UsuarioDto [name=" + name + ", email=" + email + ", password=" + password + ", phone=" + phone + "]";
	}

}