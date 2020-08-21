package com.klinux.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseDto implements Serializable {

	private static final long serialVersionUID = -6499381132235469726L;

	private String mensaje;

	private String id;

	private String created;

	private String modified;

	@JsonProperty("last_login")
	private String lastLogin;

	private String token;

	private String isactive;

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getModified() {
		return modified;
	}

	public void setModified(String modified) {
		this.modified = modified;
	}

	public String getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getIsactive() {
		return isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}

}