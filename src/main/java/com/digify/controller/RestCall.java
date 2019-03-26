package com.digify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.digify.model.RequestQuotes;
import com.digify.service.ProductService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@PreAuthorize("permitAll()")
@RequestMapping(value = { "/" })
public class RestCall {

	@Autowired
	ProductService productService;
	
	@RequestMapping(value = { "/requestQuotes" }, method = { RequestMethod.POST })
	@ResponseBody
	public boolean requestQuotes(@RequestParam("personName") String personName,
			@RequestParam("personEmail") String personEmail, @RequestParam("mobile") String mobile,
			@RequestParam("quoteDetails") String quoteDetails, @RequestParam("inquiryForId") long inquiryForId,
			@RequestParam("inquiryFor") String inquiryFor, @RequestParam("inquiryForName") String inquiryForName) {
		
		RequestQuotes requestQuotes= new RequestQuotes(personName, personEmail, mobile, quoteDetails, inquiryFor, inquiryForId, inquiryForName);
		boolean retrn = productService.insertQuotes(requestQuotes);
		return retrn;
	}
}
