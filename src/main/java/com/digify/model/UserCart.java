package com.digify.model;

import java.io.Serializable;
import java.util.List;

public class UserCart implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -9212912327355111502L;
	private List<Services> listOfService;
    private List<Products> listOfProducts;

    public List<Services> getListOfService() {
        return listOfService;
    }

    public void setListOfService(List<Services> listOfService) {
        this.listOfService = listOfService;
    }

    public List<Products> getListOfProducts() {
        return listOfProducts;
    }

    public void setListOfProducts(List<Products> listOfProducts) {
        this.listOfProducts = listOfProducts;
    }

    public UserCart(){
    	super();
    }
//    public UserCart(List<Services> listOfService ,List<Products> listOfProducts){
//
//        this.listOfService = listOfService;
//        this.listOfProducts = listOfProducts;
//    }


}
