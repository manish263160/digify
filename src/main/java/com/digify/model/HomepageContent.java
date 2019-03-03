package com.digify.model;

import java.io.Serializable;

public class HomepageContent extends BaseDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4472133817785941440L;
	private long id;
	private long userId;
	private String imageName;
	private String imageUrl;
	private long homeContentId;
	private String imageLink;
	private String contentDescription;
	private String viewsFolder;
	
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
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public long getHomeContentId() {
		return homeContentId;
	}
	public void setHomeContentId(long homeContentId) {
		this.homeContentId = homeContentId;
	}
	public String getImageLink() {
		return imageLink;
	}
	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}
	public String getContentDescription() {
		return contentDescription;
	}
	public void setContentDescription(String contentDescription) {
		this.contentDescription = contentDescription;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getViewsFolder() {
		return viewsFolder;
	}
	public void setViewsFolder(String viewsFolder) {
		this.viewsFolder = viewsFolder;
	}
}
