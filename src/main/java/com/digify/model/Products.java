package com.digify.model;

import java.io.Serializable;

public class Products extends BaseDto implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -658460981777155577L;
    private long id;
    private long userId;
    private String productName;
    private String productDescription;
    private String productImage;
    private String externalLink;
    private String viewsFolder;
    private String imageUrl;
    private String iconImg;
    private String iconImgUrl;
    private Integer isForEcommerce;
    private Integer cost;
    private Long userCartProductPK;

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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getExternalLink() {
        return externalLink;
    }

    public void setExternalLink(String externalLink) {
        this.externalLink = externalLink;
    }

    public String getViewsFolder() {
        return viewsFolder;
    }

    public void setViewsFolder(String viewsFolder) {
        this.viewsFolder = viewsFolder;
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

    public Integer getIsForEcommerce() {
        return isForEcommerce;
    }

    public void setIsForEcommerce(Integer isForEcommerce) {
        this.isForEcommerce = isForEcommerce;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

	public Long getUserCartProductPK() {
		return userCartProductPK;
	}

	public void setUserCartProductPK(Long userCartProductPK) {
		this.userCartProductPK = userCartProductPK;
	}


}
