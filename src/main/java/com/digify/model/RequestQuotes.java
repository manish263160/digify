package com.digify.model;

import java.io.Serializable;

public class RequestQuotes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 633356774666970739L;

	private long id;
	private String personName;
	private String personEmail;
	private String mobile;
	private String quoteDetails;
	private String inquiryFor;
	private long inquiryForId;
	private String inquiryForName;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getPersonEmail() {
		return personEmail;
	}
	public void setPersonEmail(String personEmail) {
		this.personEmail = personEmail;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getQuoteDetails() {
		return quoteDetails;
	}
	public void setQuoteDetails(String quoteDetails) {
		this.quoteDetails = quoteDetails;
	}
	public String getInquiryFor() {
		return inquiryFor;
	}
	public void setInquiryFor(String inquiryFor) {
		this.inquiryFor = inquiryFor;
	}
	public long getInquiryForId() {
		return inquiryForId;
	}
	public void setInquiryForId(long inquiryForId) {
		this.inquiryForId = inquiryForId;
	}
	public String getInquiryForName() {
		return inquiryForName;
	}
	public void setInquiryForName(String inquiryForName) {
		this.inquiryForName = inquiryForName;
	}
}
