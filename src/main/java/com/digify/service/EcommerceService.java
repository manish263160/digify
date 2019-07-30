package com.digify.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.digify.model.CartModel;
import com.digify.model.UserCart;
import com.digify.model.UserOrderDetails;
import com.digify.model.UserOrderResponseDto;
import com.digify.model.UserOrders;

public interface EcommerceService {


    boolean addTocart(String cartFor, long userId, int serviceProductId, HttpServletRequest request);

    List<CartModel> listOfUserCart(long userId);

    UserCart getUserCart(long userId);

	boolean insertOrder(UserOrders userOrders, HttpServletRequest request);

	boolean removeFromCart(int serviceProductId);

	Map<String, List<UserOrderResponseDto>> getUserOrders();
}
