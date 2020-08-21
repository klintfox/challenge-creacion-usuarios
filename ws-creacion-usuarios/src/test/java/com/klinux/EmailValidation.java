package com.klinux;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidation {

	final static String regex = "(?=.*?^[A-Z{1}])(?=.*?[a-z])"; // mayuscula
//	final static String regex = "^([a-zA-Z])"; // minuscula
//	final static String regex = "[0-9]{2}"; // dos numeros
//	final static String regex = "^(?=.*?[A-Z]{1})(?=.*?[a-z])(?=.*?[0-9]).{2}(?=.*?^[#?!@$%^&*-]).{8,}$";

	public static void main(String[] args) {

//		boolean flag = otrometodo();
//		System.out.println("Resultado: " + flag);

		List emails = new ArrayList();
		emails.add("Soloyo67"); // 1
		emails.add("Soloyo"); // 2
		emails.add("Aliceeee@aa33"); // 3
		emails.add("AliCe@aa33"); // 4
		emails.add("AAicea@259"); // 5
		emails.add("AAice le22"); // 6
		emails.add("SoloyoSS58"); // 7
		emails.add("xaSeTa"); // 8
		emails.add("soloyo"); // 9
		emails.add("22"); // 10
		emails.add("AAicea259"); // 11
		emails.add("A"); // 12
		emails.add("AbA"); // 13
		emails.add("Ab"); // 14
		emails.add("Ab02"); // 15
		emails.add("02"); // 15
//
//		// initialize the Pattern object
		Pattern pattern = Pattern.compile(regex);
//
//		// searching for occurrences of regex
		for (int i = 0; i < emails.size(); i++) {
			Matcher matcher = pattern.matcher(emails.get(i).toString());
			System.out.println(i + 1 + "   " + (matcher.matches() ? "Valid" : "invalid"));
		}

	}

	private static boolean otrometodo() {
		boolean flagResponse = false;
		boolean flag = false;
		boolean flag2 = false;

		String s = "Soloyo4g4$$";

		// 1 find two numbers
		String str = s.replaceAll("\\D+", "");
		if (str.length() == 2) {
			flag = true;
//			System.out.println("1: " + flag);
		}

		// 2 find one upper character
		int upperCount = 0;
		for (int i = 0; i < s.length(); i++) {
			if (Character.isUpperCase(s.charAt(i))) {
				upperCount++;
			}
		}
		if (upperCount == 1) {
			flag2 = true;
//			System.out.println("2: " + flag);
		}

		if (flag && flag2) {
			flagResponse = true;
		}

		return flagResponse;
	}

}
