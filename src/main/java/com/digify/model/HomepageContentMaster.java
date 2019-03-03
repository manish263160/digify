package com.digify.model;

import java.io.Serializable;

public class HomepageContentMaster implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3532291622945482373L;
	private long id;
	private String name;
	private String description;
	private String displayName;
	private String icon;
	private String viewFolder;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getViewFolder() {
		return viewFolder;
	}
	public void setViewFolder(String viewFolder) {
		this.viewFolder = viewFolder;
	}

}
