package com.klinux;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidation {

	final static String regex = "^([A-Z])([a-z]).{5}([0-9]{2})"; // mayuscula

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {

		List emails = new ArrayList();
		emails.add("Program45"); // 1
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
		emails.add("02"); // 16

		Pattern pattern = Pattern.compile(regex);
		for (int i = 0; i < emails.size(); i++) {
			Matcher matcher = pattern.matcher(emails.get(i).toString());
			System.out.println(i + 1 + "   " + (matcher.matches() ? "Valid" : "invalid"));
		}

	}
}