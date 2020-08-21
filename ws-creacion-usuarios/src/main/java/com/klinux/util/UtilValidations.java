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

	/**
	 * validar clave (La primera Mayuscula, letras minúsculas, y dos numeros)
	 * @param password
	 * @return
	 */
	public static boolean passwordValidator(String password) {
//		([A-Z]{1})|([a-z])|([0-9]{2})
		boolean flag = false;
		String regex = "^([A-Z])([a-z]).{5}([0-9]{2})"; // mayuscula
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(password);
		String response = (matcher.matches() ? "valid" : "invalid");
		if (response.equals("valid")) {
			flag = true;
		}		
		return flag;
	}
}
