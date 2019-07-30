package com.digify.model;

import java.io.Serializable;

public class User extends BaseDto implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -7580938671398817987L;
    private long id;
    private String name;
    private String address;
    private String email;
    private String mobileNo;
    private int status;
    private String passGenToken;
    private Long otp;
    private String password;
    private String role;
    private String userInfoJson;
    private UserCart userCart;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public User() {

    }

    public User(long id, String username, String address, String email) {
        this.id = id;
        this.name = username;
        this.address = address;
        this.email = email;
    }

    public User(User user) {

        super();
        this.id = user.getId();
        this.name = user.getName();
        this.address = user.getAddress();
        this.password = user.getPassword();
        this.email = user.getEmail();
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the mobileNo
     */
    public String getMobileNo() {
        return mobileNo;
    }

    /**
     * @param mobileNo the mobileNo to set
     */
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof User))
            return false;
        User other = (User) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + name + ", address=" + address
                + ", email=" + email + ", otp=" + otp + "]";
    }


    public Long getOtp() {
        return otp;
    }

    public void setOtp(Long oTP) {
        otp = oTP;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserInfoJson() {
        return userInfoJson;
    }

    public void setUserInfoJson(String userInfoJson) {
        this.userInfoJson = userInfoJson;
    }

    public String getPassGenToken() {
        return passGenToken;
    }

    public void setPassGenToken(String passGenToken) {
        this.passGenToken = passGenToken;
    }

    public UserCart getUserCart() {
        return userCart;
    }

    public void setUserCart(UserCart userCart) {
        this.userCart = userCart;
    }

}
