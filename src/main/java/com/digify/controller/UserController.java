package com.digify.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.digify.exception.GenericException;
import com.digify.model.User;
import com.digify.model.UserBookingDetails;
import com.digify.service.UserService;
import com.digify.utils.ApplicationProperties;
import com.digify.utils.GenUtilities;


@Controller
@RequestMapping({ "/user" })
@SessionAttributes({ "userdata" })
public class UserController {
	private static final Logger logger = Logger.getLogger(UserController.class);
	@Autowired
	UserService userService;
	@Autowired
	private ApplicationProperties applicationProperties;
	
	@RequestMapping(value = { "/bookedservice" }, method = { RequestMethod.GET })
	public String bookedservice(Model model, HttpServletRequest request) {
//		User user = GenUtilities.getLoggedInUser();
		List<UserBookingDetails> bookedservice = userService.getUserBookingDetails(); 
		model.addAttribute("active", "bookedservice");
		model.addAttribute("themecolor", this.applicationProperties.getProperty("themecolor"));
		model.addAttribute("list", bookedservice);
		return "bookedservice";
	}

	@RequestMapping(value = { "/userregistration" }, method = { RequestMethod.GET })
	public String register(@RequestParam(name = "error", required = false) String error, @ModelAttribute User user,
			ModelMap map, HttpServletRequest request) {
		logger.debug("register start");
		map.addAttribute("error", error);
		return "user/registrationpage";
	}

	@RequestMapping(value = { "/insertUser" }, method = { RequestMethod.POST })
	public String insertUser(@ModelAttribute User user, ModelMap map, HttpServletRequest request) {
		logger.debug("register start");

		try {
			this.userService.insertUser(user);
			return "user/userRegSuccess";
		} catch (GenericException arg5) {
			System.out.println(arg5.getMessage());
			return "redirect:userregistration?error=" + arg5.getMessage();
		}
	}

	@RequestMapping(value = { "/activateUser" }, method = { RequestMethod.GET })
	public String activateUser(@RequestParam String token, HttpServletRequest request) {
		try {
			if (GenUtilities.getLoggedInUser() != null) {
				request.logout();
				SecurityContextHolder.getContext().setAuthentication((Authentication) null);
			}

			String e = this.userService.activateUser(token);
			return "redirect:/login.htm?message=" + e;
		} catch (Exception arg3) {
			arg3.printStackTrace();
			logger.error("::activateUser: Exception occurred!!");
			return "redirect:/login.htm";
		}
	}
}