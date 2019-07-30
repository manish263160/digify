package com.digify.model;

public class CartModel extends BaseDto {

    private int id;
    private String cartFor;
    private long userId;
    private Long serviceId;
    private Long productId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCartFor() {
        return cartFor;
    }

    public void setCartFor(String cartFor) {
        this.cartFor = cartFor;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
