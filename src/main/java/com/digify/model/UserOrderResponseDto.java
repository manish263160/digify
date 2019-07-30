package com.digify.model;

import java.io.Serializable;

public class UserOrderResponseDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2818269367470764530L;
	private Long id;
	private Products products;
	private Services services;
	private String TXNAMOUNT;
	private String TXNID;
	private String createdOn;
	private String userOrderId;
	public Long getId() {
		return id;
	}
	public Products getProducts() {
		return products;
	}
	public Services getServices() {
		return services;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setProducts(Products products) {
		this.products = products;
	}
	public void setServices(Services services) {
		this.services = services;
	}
	public String getTXNAMOUNT() {
		return TXNAMOUNT;
	}
	public String getTXNID() {
		return TXNID;
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public void setTXNAMOUNT(String tXNAMOUNT) {
		TXNAMOUNT = tXNAMOUNT;
	}
	public void setTXNID(String tXNID) {
		TXNID = tXNID;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	public String getUserOrderId() {
		return userOrderId;
	}
	public void setUserOrderId(String userOrderId) {
		this.userOrderId = userOrderId;
	}
	@Override
	public String toString() {
		return "UserOrderResponseDto [id=" + id + ", products=" + products + ", services=" + services + ", TXNAMOUNT="
				+ TXNAMOUNT + ", TXNID=" + TXNID + ", createdOn=" + createdOn + "]";
	}

}
