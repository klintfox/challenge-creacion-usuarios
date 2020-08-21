package com.klinux;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidation {

	final static String regex = "^(?=.*?[A-Z]{1})"; // mayuscula
//	final static String regex2 = "[a-z]"; // minuscula
//	final static String regex3 = "[0-9]{2}"; // dos numeros
//	final static String regex = "^(?=.*?[A-Z]{1})(?=.*?[a-z])(?=.*?[0-9]).{2}(?=.*?[#?!@$%^&*-]).{8,}$";

	public static void main(String[] args) {

		boolean flag = otrometodo();
		System.out.println("Resultado: " +flag);

		List emails = new ArrayList();
		emails.add("Soloyo67"); // 1
		emails.add("Soloyo"); // 2
		emails.add("Aliceeee@aa33"); // 3
		emails.add("AliCe@aa33"); // 3
		emails.add("AAicea2599"); // 4
		emails.add("AAice le22"); // 5
		emails.add("SoloyoSS58"); // 6
		emails.add("xaSeTa"); // 7
		emails.add("soloyo"); // 8
		emails.add("22"); // 9
//
//		// initialize the Pattern object
		Pattern pattern = Pattern.compile(regex);
//
//		// searching for occurrences of regex
		for (int i = 0; i < emails.size(); i++) {
			Matcher matcher = pattern.matcher(emails.get(i).toString());
			System.out.println(i + 1 + " " + (matcher.matches() ? "Valid" : "invalid"));
		}

	}

	private static boolean otrometodo() {
		boolean flag= false;
//		String s = "Soloyo55";
//		
//		// 1 find two numbers
//		String str = s.replaceAll("\\D+", "");
//		if (str.length() == 2) {
//			flag = true;
//		}
////		System.out.println(str);
//		
//		// 2 find one upper character
//		String regex1 = "(^[A-Z]{1})"; // mayuscula
//		Pattern pattern = Pattern.compile(regex1);
//		Matcher matcher = pattern.matcher(s);
//		System.out.println((matcher.matches() ? "OK" : "invalid"));
		
		
		return flag;
	}

}
