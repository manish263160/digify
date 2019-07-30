package com.digify.model;

import java.io.Serializable;

public class UserOrderDetails implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2010963986442574005L;
	private Long id;
	private Long user_id;
	private String userOrderId;
	private Long productId;
	private Long serviceId;
	private String TXNAMOUNT;
	private String TXNID;
	private String createdOn;
	public Long getId() {
		return id;
	}
	public Long getUser_id() {
		return user_id;
	}
	
	public Long getProductId() {
		return productId;
	}
	public Long getServiceId() {
		return serviceId;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}
	public String getUserOrderId() {
		return userOrderId;
	}
	public void setUserOrderId(String userOrderId) {
		this.userOrderId = userOrderId;
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
}
