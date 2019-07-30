package com.digify.dao;

import java.util.List;

import com.digify.model.CartModel;
import com.digify.model.UserOrderDetails;
import com.digify.model.UserOrders;

public interface EcommerceDao {
	Long addTocart(String cartFor, long userId, int serviceProductId);

    List<CartModel> listOfUserCart(long userId);

    Integer insertOrder(UserOrders userOrders);

	<T> int[] insertOrderDetails(String string, UserOrders userOrders, List<T> productList);

	int cartDelete(int userId);

	boolean removeFromCart(int cartId);

	List<UserOrderDetails> getUserOrdersDetails(String orderId);

	UserOrders findByOrderId(String orderId);

	List<UserOrders> getUserOrders(boolean isAdmin);
}
