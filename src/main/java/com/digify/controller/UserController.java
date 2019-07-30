package com.digify.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.digify.model.UserOrderResponseDto;
import com.digify.service.EcommerceService;
import com.digify.service.ProductService;
import com.digify.service.UserService;


@Controller
@RequestMapping({"/user"})
@SessionAttributes({"userdata"})
@PreAuthorize("hasRole('ROLE_USER')")
public class UserController {
    private static final Logger logger = Logger.getLogger(UserController.class);
    @Autowired
    UserService userService;
//    @Autowired
//    private ApplicationProperties applicationProperties;
    @Autowired
    private EcommerceService ecommerceService;
    
	@Autowired
	ProductService productService;   

    @RequestMapping(value = "/getUserOrders", method = {RequestMethod.GET})
    public String getUserOrders(ModelMap model, HttpServletRequest request) throws Exception {
    	logger.info("getUserOrders :: ");
    	Map<String, List<UserOrderResponseDto>> userOrders = ecommerceService.getUserOrders();
    	model = productService.setProductservice(model);
    	model.addAttribute("userOrders", userOrders);
		return "frontend/myOrders";
    	
    }
}