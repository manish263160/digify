package com.digify.Enums;

import java.io.Serializable;

public enum BASIC_STRINGS implements Serializable{

	SYSTEM("SYSTEM"),USER("USER"),ADMIN("ADMIN"), DEFAULT_USER("DEFAULT_USER"), 
	INACTIVE("INACTIVE") , COMPLETED("Completed"),ACTIVE("ACTIVE"), 
	CAROUSEL("carousel") , ABOUTUS("aboutus"),CONTACTUS("contactus"),
	PRODUCT("product"),SERVICE("service"),
	//These are the delivery options
	CASHON_DELIVERY("CASHON_DELIVERY"),ONLINE("ONLINE"),CANCELED("CANCELED");
	
	String stringName;

	BASIC_STRINGS(String stringName) {
		this.stringName = stringName;
	}
	public String getStringName() {
		return stringName;
	}

}
