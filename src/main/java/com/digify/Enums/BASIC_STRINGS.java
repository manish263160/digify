package com.digify.Enums;

import java.io.Serializable;

public enum BASIC_STRINGS implements Serializable{

	SYSTEM("SYSTEM"),USER("USER"),ADMIN("ADMIN"), DEFAULT_USER("DEFAULT_USER"), 
	INACTIVE("INACTIVE") , COMPLETED("Completed"),ACTIVE("ACTIVE"), 
	CAROUSEL("carousel") , ABOUTUS("aboutus"),CONTACTUS("contactus"),
	PRODUCTS("products"),SERVICES("services"),
	//These are the delivery options
	CASHON_DELIVERY("CASHON_DELIVERY"),ONLINE("ONLINE"),CANCELED("CANCELED"),
	LOCALURL("http://localhost:8080"),PRODUCTION("http://13.234.106.81:8080");
	
	String stringName;

	BASIC_STRINGS(String stringName) {
		this.stringName = stringName;
	}
	public String getStringName() {
		return stringName;
	}

}
