package com.klinux.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilValidations {

	/**
	 * Método que validate el formato de un correo ejemplo: aaaaaaa@dominio.cl
	 * 
	 * @param email
	 * @return boolean
	 */
	public static boolean emailValidator(String email) {
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

	// validar clave (Una Mayuscula, letras minúsculas, y dos numeros)
	
	public static boolean passwordValidator(String password) {
		boolean flag = true;
		return flag;
	}
}
