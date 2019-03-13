package com.digify.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.digify.Enums.BASIC_STRINGS;
import com.digify.model.Products;
import com.digify.model.Services;
import com.digify.service.AdminService;
import com.digify.service.ProductService;
import com.digify.utils.ApplicationProperties;
import com.digify.utils.GenUtilities;

@Controller
@PreAuthorize("permitAll()")
@RequestMapping({"/admin/product"})
public class ProductController {

	private static final Logger logger = Logger.getLogger(ProductController.class);
	@Autowired
	ProductService productService;

	@Autowired
	ApplicationProperties applicationProperties;

	@Autowired
	AdminService adminService;
	
	@RequestMapping(value = { "/{tableName}" } ,method = { RequestMethod.GET })
	public String bookedservice(ModelMap model, HttpServletRequest request , @PathVariable("tableName") String tableName) {
		model=adminService.addUserInModel(model); 
		model=adminService.addListHomePageContent(model);
		model.addAttribute("themecolor", this.applicationProperties.getProperty("themecolor"));
		if(tableName.equals("products")) {
		List<Products> list = productService.getAllProductServices(tableName);
		for (Products products : list) {
			logger.info("desc:  "+products.getProductDescription());
		}
		model.addAttribute("list", list);
		}
		else if(tableName.equals("services")) {
			List<Services> list = productService.getAllProductServices(tableName);
			model.addAttribute("list", list);	
		}
		setActiveScreen(tableName,model);
		model.addAttribute("tableName", tableName);	
		return "productandservice/productServiceDashboard";
	}
	
	@RequestMapping(value = { "/add/addProductView/{tableName}" }, method = { RequestMethod.GET })
	public String addProductView(ModelMap model, HttpServletRequest request ,
			@PathVariable("tableName") String tableName,@RequestParam(value ="error" ,required = false) String error) {
		logger.info("addProductView::");
		model=adminService.addUserInModel(model); 
		model=adminService.addListHomePageContent(model);
		setActiveScreen(tableName,model);
		model.addAttribute("error", error);
		model.addAttribute("tableName", tableName);
		model.addAttribute("themecolor", this.applicationProperties.getProperty("themecolor"));
		return "productandservice/uploadProductServiceView";
	}
	
	@RequestMapping(value = { "/insertProduct" }, method = { RequestMethod.POST })
	public String addCarauserInsert( @RequestParam(value = "file", required = false) MultipartFile[] multipart,
			  ModelMap model,
			@ModelAttribute("products") Products products , @ModelAttribute("services") Services services  ,@RequestParam("tableName") String tableName) {			
		try {
			MultipartFile iconImg = null;
			MultipartFile file = null;
			if(multipart.length > 0) {
				file = multipart[0];
				iconImg = multipart[1];
			}else {
				iconImg = multipart[0];
			}
//			String filepath = extractservice.extracted(file, uploadNews, tableName);
			model=adminService.addUserInModel(model); 
			model=adminService.addListHomePageContent(model);
			setActiveScreen(tableName,model);
			model.addAttribute("themecolor", this.applicationProperties.getProperty("themecolor"));
			String filePath = null;
			if(tableName.equals("products"))
			 filePath= adminService.insertUpdateHomeComponent(file , products , tableName , "insert" , iconImg);
			if(tableName.equals("services"))
				 filePath= adminService.insertUpdateHomeComponent(file , services , tableName , "insert", iconImg);
				
			if(filePath != null) {
			model.addAttribute("imagepath", filePath);
			return "redirect:insertContentSuccessfull";
			}
			else {
				logger.error("error in file upload==");
				String msg = "error in file upload";
				return "redirect:add/addProductView/"+tableName+"?error=" + msg;
					
			}
		} catch (Exception e) {
			logger.error("error in file upload==" + e);
			return "redirect:add/addProductView/"+tableName+"?error="+ e.getMessage();
		}
	}
	
	@RequestMapping(value = { "/insertContentSuccessfull" }, method = { RequestMethod.GET })
	public String insertContentSuccessfull(ModelMap model) {
		model = adminService.addUserInModel(model);
		model = adminService.addListHomePageContent(model);
		return "admin/insertContentSuccessfull";
	}
	@RequestMapping(value = { "/editProductServiceView/{tableName}/{contentId}" }, method = { RequestMethod.GET })
	public String addCarauser(ModelMap model, HttpServletRequest request , @PathVariable("contentId") long contentId
			,@PathVariable("tableName") String tableName , @RequestParam(value="error" , required= false) String error) {
		model=adminService.addUserInModel(model); 
		model=adminService.addListHomePageContent(model);
		setActiveScreen(tableName,model);
		model.addAttribute("themecolor", this.applicationProperties.getProperty("themecolor"));
		if(tableName.equals("products")) {
		Products obj = productService.getProductServiceById(contentId , tableName);
		model.addAttribute("obj", obj);
		}
		else if(tableName.equals("services")) {
			Services obj = productService.getProductServiceById(contentId , tableName);
			model.addAttribute("obj", obj);
		}
		model.addAttribute("contentId", contentId);
		model.addAttribute("tableName", tableName);
		model.addAttribute("error", error);
		return "productandservice/editProductServiceView";
	}
	
	@RequestMapping(value = { "/updateProductService" }, method = { RequestMethod.POST})
	public String updateHomeContent(@RequestParam(value = "file", required = false) MultipartFile[] multipart,
			ModelMap model,
			@RequestParam("contentId") long  contentId,
			@ModelAttribute("products") Products products , @ModelAttribute("services") Services services  ,@RequestParam("tableName") String tableName,
			@RequestParam("oldImageURL") String oldImageURL , @RequestParam("oldIconImageURL") String oldIconImageURL) {	
		
		try {
			MultipartFile iconImg = null;
			MultipartFile file = null;
			if (multipart != null && multipart.length != 0) {
				if (multipart.length > 0) {
					file = !multipart[0].isEmpty() ? multipart[0] : null;
					iconImg = !multipart[1].isEmpty() ? multipart[1] : null;
				} else if(multipart.length == 0) {
					iconImg = !multipart[0].isEmpty() ? multipart[0] : null;
				}
			}
			model=adminService.addUserInModel(model); 
			model=adminService.addListHomePageContent(model);
			model.addAttribute("active", "carousel");
			model.addAttribute("themecolor", this.applicationProperties.getProperty("themecolor"));
			if(tableName.equals("products")) {
			products.setId(contentId);
			String imagePath = this.applicationProperties.getProperty("imageFolder");
			if(file != null && !file.isEmpty()) {
				if(oldImageURL != null && !oldImageURL.isEmpty()) {
				oldImageURL = imagePath +BASIC_STRINGS.ADMIN.getStringName()+this.applicationProperties.getProperty("products")+oldImageURL;
				}else {
					String fileName = file.getOriginalFilename();
					oldImageURL = imagePath +BASIC_STRINGS.ADMIN.getStringName()+this.applicationProperties.getProperty("products")+fileName;
				}
				File oldFile =new File(oldImageURL);
				GenUtilities.delete(oldFile);
			}else {
				products.setProductImage(oldImageURL);
			}
			if(iconImg != null && !iconImg.isEmpty()){
				if(oldIconImageURL != null && !oldIconImageURL.isEmpty()) {
				oldIconImageURL = imagePath +BASIC_STRINGS.ADMIN.getStringName()+this.applicationProperties.getProperty("products")+oldIconImageURL;
				}
				else {
					String fileName = iconImg.getOriginalFilename();
					oldImageURL = imagePath +BASIC_STRINGS.ADMIN.getStringName()+this.applicationProperties.getProperty("products")+fileName;
				}
				File oldIconFile =new File(oldIconImageURL);
				GenUtilities.delete(oldIconFile);
			}else {
				products.setIconImg(oldIconImageURL);
			}
			String filePath = adminService.insertUpdateHomeComponent(file , products , tableName , "update" ,iconImg);
			model.addAttribute("imagepath", filePath);
			return "redirect:insertContentSuccessfull";
			}
			else if(tableName.equals("services")) {
				services.setId(contentId);
				if(file != null && !file.isEmpty()) {
					String imagePath = this.applicationProperties.getProperty("imageFolder");
					oldImageURL = imagePath +BASIC_STRINGS.ADMIN.getStringName()+this.applicationProperties.getProperty("services")+oldImageURL;
					File oldFile =new File(oldImageURL);
					GenUtilities.delete(oldFile);
				}else {
					services.setServiceImage(oldImageURL);
				}
				String filePath = adminService.insertUpdateHomeComponent(file , services , tableName , "update" ,iconImg);
				model.addAttribute("imagepath", filePath);
				return "admin/insertContentSuccessfull";
				}
		} catch (Exception e) {
			logger.error("error in file upload==" + e);
			return "redirect:editProductServiceView/"+tableName+"/"+contentId+"?error=" + e.getMessage();
		}
		String st= "Something went wrong";
		return "redirect:editProductServiceView/"+tableName+"/"+contentId+"?error="+st;
	}
	
	@RequestMapping(value = { "/deleteContent/{contentId}/{tableName}" }, method = { RequestMethod.DELETE})
	@ResponseBody
	public String deleteContent( @PathVariable("contentId") long  contentId,
			@RequestParam(value="imageName", required=false) String imageName ,@PathVariable("tableName") String tableName  ) {	
		logger.info("delete content data: "+contentId +" "+imageName+ " "+ tableName);
		boolean statusDelete = productService.deleteContent(contentId,imageName,tableName);
		return statusDelete ? "success" : "fail";
	}
	
	private void setActiveScreen(String tableName, ModelMap model) {
		if(tableName.equals("products")) {
			model.addAttribute("active", "products") ;
		} else if(tableName.equals("services")) {
			model.addAttribute("active", "services");
		}
	}
}
