package com.digify.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.digify.service.UserService;
import com.digify.utils.ApplicationProperties;


@Controller
@RequestMapping({ "/user" })
@SessionAttributes({ "userdata" })
public class UserController {
	private static final Logger logger = Logger.getLogger(UserController.class);
	@Autowired
	UserService userService;
	@Autowired
	private ApplicationProperties applicationProperties;
	

}