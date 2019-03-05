package com.digify.model;

import java.io.Serializable;

public class Services extends BaseDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 963271265709129583L;
	private long id;
	private long userId;
	private String serviceName;
	private String serviceImage;
	private String serviceDescription;
	private String externalLink;
	private String imageUrl;
	private String iconImg;
	private String iconImgUrl;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getServiceImage() {
		return serviceImage;
	}
	public void setServiceImage(String serviceImage) {
		this.serviceImage = serviceImage;
	}
	public String getServiceDescription() {
		return serviceDescription;
	}
	public void setServiceDescription(String serviceDescription) {
		this.serviceDescription = serviceDescription;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getExternalLink() {
		return externalLink;
	}
	public void setExternalLink(String externalLink) {
		this.externalLink = externalLink;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getIconImg() {
		return iconImg;
	}
	public void setIconImg(String iconImg) {
		this.iconImg = iconImg;
	}
	public String getIconImgUrl() {
		return iconImgUrl;
	}
	public void setIconImgUrl(String iconImgUrl) {
		this.iconImgUrl = iconImgUrl;
	}
	
	
}
