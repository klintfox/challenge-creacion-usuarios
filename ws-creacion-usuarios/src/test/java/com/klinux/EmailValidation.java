package com.klinux;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidation {

//	private static final String regex = "^(?=.*[0-9])(?=.*[a-z])([A-Z]{1})(?=\\S+$).{6,}$";
//	private static final String regex = "^[a-zA-Z0-9]+$";
//	private static final String regex = "^[a-z]*[A-Z]+$";

	private static final String regex = "^(?=.*[0-9]{1})[a-zA-Z0-9]{8,}$";

	public static void main(String[] args) {
		List emails = new ArrayList();
		// valid email addresses
		emails.add("Soloyo66");
		//error
		emails.add("Alice@aa33");
		emails.add("Alicea2599");
		emails.add("Soloyo65*");
		emails.add("Alice le22");
		emails.add("SoloyoSS58");		
		emails.add("Cadena");
		emails.add("xaSeTa");		
		emails.add("soloyo");

		// initialize the Pattern object
		Pattern pattern = Pattern.compile(regex);

		// searching for occurrences of regex
		for (int i = 0; i < emails.size(); i++) {
			Matcher matcher = pattern.matcher(emails.get(i).toString());
			System.out.println(i + " is " + (matcher.matches() ? "valid" : "invalid"));
		}

	}

}
