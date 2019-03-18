package com.digify.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.digify.Enums.BASIC_STRINGS;
import com.digify.model.HomepageContent;
import com.digify.model.Products;
import com.digify.model.Services;
import com.digify.service.AdminService;
import com.digify.service.ProductService;
import com.digify.service.UserService;
import com.digify.utils.ApplicationProperties;

@Controller
@RequestMapping(value = { "/frontendAction" })
@PreAuthorize("permitAll()")
public class FrontendActionController {

	@Autowired ProductService productService;
	@Autowired ApplicationProperties applicationProperties; 
	@Autowired UserService userService; 
	@Autowired AdminService adminService;
	
	@RequestMapping(value = { "/productPage/{id}" }, method = { RequestMethod.GET })
	public String productPage(ModelMap model, HttpServletRequest request , @PathVariable("id") Long id) {
		String tableName = BASIC_STRINGS.PRODUCTS.getStringName();
		Products product= productService.getProductServiceById(id, tableName);
		model = productService.setProductservice(model);
		model.addAttribute("product", product);
		model.addAttribute("table", tableName);
		
		return "frontend/productPage";
	}
	@RequestMapping(value = { "/servicePage/{id}" }, method = { RequestMethod.GET })
	public String servicePage(ModelMap model, HttpServletRequest request , @PathVariable("id") Long id) {
		String tableName = BASIC_STRINGS.SERVICES.getStringName();
		Services service= productService.getProductServiceById(id, tableName);
		model = productService.setProductservice(model);
		model.addAttribute("service", service);
		model.addAttribute("table", tableName);
		return "frontend/servicePage";
	}
	
	@RequestMapping(value = { "/aboutUs" }, method = { RequestMethod.GET })
	public String aboutUs(ModelMap model) {
		model = productService.setProductservice(model);
		String viewsFolder = BASIC_STRINGS.ABOUTUS.getStringName();
		Long id = new Long(2);
		List<HomepageContent> list =adminService.getAllHomeComponentList(id, viewsFolder);
		model.addAttribute("list", list);
		model.addAttribute("fromcontact", true);
 		return "frontend/aboutUs";
	}
	
	@RequestMapping(value = { "/dashBoard/{dashboardFor}" }, method = { RequestMethod.GET })
	public String dashBoard(ModelMap model , @PathVariable("dashboardFor") String dashboardFor) {
		model = productService.setProductservice(model);
		if(dashboardFor.equals(BASIC_STRINGS.PRODUCTS.getStringName())) {
		List<Products> allProducts = productService.getAllProductServices(BASIC_STRINGS.PRODUCTS.getStringName());
		model.addAttribute("allProducts", allProducts);
		}else if(dashboardFor.equals(BASIC_STRINGS.SERVICES.getStringName())) {
			List<Services> allServices = productService.getAllProductServices(BASIC_STRINGS.SERVICES.getStringName());	
			model.addAttribute("allServices", allServices);
		}
		model.addAttribute("table", dashboardFor);
 		return "frontend/productServiceDashBoard";
	}
	
	@RequestMapping(value = { "/contactUs" }, method = { RequestMethod.GET })
	public String contactUs(ModelMap model) {
		model = productService.setProductservice(model);
		Long id = new Long(3);
		String viewsFolder = BASIC_STRINGS.CONTACTUS.getStringName();
		List<HomepageContent> list =adminService.getAllHomeComponentList(id, viewsFolder);
		model.addAttribute("list", list);
		model.addAttribute("fromcontact", true);
 		return "frontend/contactUs";
	}
	
	
	@RequestMapping(value = { "/contactUsSubmit" }, method = { RequestMethod.POST })
	@ResponseBody
	public boolean contactUsSubmit(ModelMap model ,@RequestParam String name , @RequestParam String email ,
			@RequestParam String subject ,@RequestParam String message ) {		
		boolean retrn = userService.contactUsSubmit(name,email , subject , message);
		return retrn;
	}
	
	@RequestMapping(value = { "/faq" }, method = { RequestMethod.GET })
	public String faqPage(ModelMap model) {
		model = productService.setProductservice(model);
//		Long id = new Long(3);
//		String viewsFolder = BASIC_STRINGS.CONTACTUS.getStringName();
//		List<HomepageContent> list =adminService.getAllHomeComponentList(id, viewsFolder);
//		model.addAttribute("list", list);
		model.addAttribute("faq", true);
 		return "frontend/faq";
	}
	@RequestMapping(value = { "/team" }, method = { RequestMethod.GET })
	public String teamPage(ModelMap model) {
		model = productService.setProductservice(model);
		model.addAttribute("team", true);
 		return "frontend/team";
	}
}
