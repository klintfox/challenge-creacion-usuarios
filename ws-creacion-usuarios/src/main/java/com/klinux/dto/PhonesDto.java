package com.klinux.dto;

import java.io.Serializable;

public class PhonesDto implements Serializable {

	private static final long serialVersionUID = 4093730209867093750L;
	private int number;
	private int citycode;
	private int countrycode;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getCitycode() {
		return citycode;
	}

	public void setCitycode(int citycode) {
		this.citycode = citycode;
	}

	public int getCountrycode() {
		return countrycode;
	}

	public void setCountrycode(int countrycode) {
		this.countrycode = countrycode;
	}

}
