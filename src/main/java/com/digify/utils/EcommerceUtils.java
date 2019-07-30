package com.digify.utils;

import com.digify.model.UserCart;

import javax.servlet.http.HttpServletRequest;

public class EcommerceUtils {

    // Products in the cart, stored in Session.
    public static UserCart getCartInSession(HttpServletRequest request) {

        UserCart cartInfo = (UserCart) request.getSession().getAttribute("myCart");


        if (cartInfo == null) {
            cartInfo = new UserCart();

            request.getSession().setAttribute("myCart", cartInfo);
        }

        return cartInfo;
    }

    public static void removeCartInSession(HttpServletRequest request) {
        request.getSession().removeAttribute("myCart");
    }

    public static void storeLastOrderedCartInSession(HttpServletRequest request, UserCart cartInfo) {
        request.getSession().setAttribute("lastOrderedCart", cartInfo);
    }

    public static UserCart getLastOrderedCartInSession(HttpServletRequest request) {
        return (UserCart) request.getSession().getAttribute("lastOrderedCart");
    }
}
