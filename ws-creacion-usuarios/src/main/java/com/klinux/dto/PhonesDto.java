package com.klinux.dto;

import java.io.Serializable;

public class PhonesDto implements Serializable {

	private static final long serialVersionUID = 4093730209867093750L;
	private String number;
	private String citycode;
	private String countrycode;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	public String getCountrycode() {
		return countrycode;
	}

	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}

}
