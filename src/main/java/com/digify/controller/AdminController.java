package com.digify.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.digify.model.HomepageContent;
import com.digify.service.AdminService;
import com.digify.utils.ApplicationProperties;
import com.digify.utils.GenUtilities;

/**
 * 
 * @author mmandal
 *
 */
@Controller
@RequestMapping({ "/admin" })
public class AdminController {

	private static final Logger logger = Logger.getLogger(AdminController.class);
	@Autowired
	AdminService adminService;
	@Autowired
	private ApplicationProperties applicationProperties;

	@RequestMapping(value = { "/dashboard" }, method = { RequestMethod.GET })
	public String bookedservice(ModelMap model, HttpServletRequest request) {
		model = adminService.addUserInModel(model);
		model = adminService.addListHomePageContent(model);
		model.addAttribute("active", "admin");
		model.addAttribute("themecolor", this.applicationProperties.getProperty("themecolor"));
		return "admin/dashboard";
	}

	@RequestMapping(value = { "/home/{pathVar}/{viewFolder}/{id}" }, method = { RequestMethod.GET })
	public String openHomeContent(ModelMap model, HttpServletRequest request, @PathVariable("pathVar") String pathVar,
			@PathVariable("id") long id, @PathVariable("viewFolder") String viewFolder) {
		model = adminService.addUserInModel(model);
		model = adminService.addListHomePageContent(model);
		model.addAttribute("active", pathVar);
		logger.debug("path var and id" + pathVar + " :" + id);
		model.addAttribute("themecolor", this.applicationProperties.getProperty("themecolor"));
		List<HomepageContent> list = adminService.getAllHomeComponentList(id, viewFolder);
		model.addAttribute("list", list);
		model.addAttribute("id", id);
		model.addAttribute("viewsFolder", viewFolder);
		return "admin/" + viewFolder + "/" + pathVar;
	}

	@RequestMapping(value = { "/add/addHomeContentView/{id}/{viewFolder}" }, method = { RequestMethod.GET })
	public String addCarauser(ModelMap model, HttpServletRequest request,
			@PathVariable("id") String homeContenMastertId, @PathVariable("viewFolder") String viewFolder) {
		model = adminService.addUserInModel(model);
		model = adminService.addListHomePageContent(model);
		model.addAttribute("active", viewFolder);
		model.addAttribute("themecolor", this.applicationProperties.getProperty("themecolor"));
		model.addAttribute("id", homeContenMastertId);
		model.addAttribute("viewFolder", viewFolder);
		return "admin/uploadHomeContnetView";
	}

	@RequestMapping(value = { "/insertHomeContent" }, method = { RequestMethod.POST })
	public String addCarauserInsert(@RequestParam(value = "file", required = false) MultipartFile file, ModelMap model,
			@ModelAttribute("homeComponent") HomepageContent homeComponent) {
		try {
			String tableName = "homepage_content";
//			String filepath = extractservice.extracted(file, uploadNews, tableName);
			model = adminService.addUserInModel(model);
			model = adminService.addListHomePageContent(model);
			model.addAttribute("active",homeComponent.getViewFolder());
			model.addAttribute("themecolor", this.applicationProperties.getProperty("themecolor"));
			String filePath = adminService.insertUpdateHomeComponent(file, homeComponent, tableName, "insert" , null);
			model.addAttribute("imagepath", filePath);
			return "redirect:insertContentSuccessfull";
		} catch (Exception e) {
			logger.error("error in file upload==" + e);
			return "redirect:add/addHomeContentView/" + homeComponent.getHomeContentId() + "/"
					+ homeComponent.getViewFolder() + "?error=" + e.getMessage();
		}
	}

	
	@RequestMapping(value = { "/editHomeContnetView/{contentId}/{viewFolder}/{table}" }, method = {
			RequestMethod.GET })
	public String addCarauser(ModelMap model, HttpServletRequest request, @PathVariable("contentId") long contentId,
			@PathVariable("table") String tableName, @PathVariable("viewFolder") String viewFolder) {
		model = adminService.addUserInModel(model);
		model = adminService.addListHomePageContent(model);
		model.addAttribute("active", "editPage");
		model.addAttribute("themecolor", this.applicationProperties.getProperty("themecolor"));
		HomepageContent obj = adminService.getHomeComponentById(contentId, viewFolder);
		model.addAttribute("homeContentObj", obj);
		model.addAttribute("table", tableName);
		model.addAttribute("contentId", contentId);
		model.addAttribute("viewFolder", viewFolder);
		return "admin/editHomeContnetView";
	}

	// updateHomeContent
	@RequestMapping(value = { "/updateHomeContent" }, method = { RequestMethod.POST })
	public String updateHomeContent(@RequestParam(value = "file", required = false) MultipartFile file, ModelMap model,
			@ModelAttribute("homeComponent") HomepageContent homeComponent, @RequestParam("contentId") long contentId,
			@RequestParam("oldImageURL") String oldImageURL) {

		try {
			String tableName = "homepage_content";
//			String filepath = extractservice.extracted(file, uploadNews, tableName);
			model = adminService.addUserInModel(model);
			model = adminService.addListHomePageContent(model);
			model.addAttribute("active", "carousel");
			model.addAttribute("themecolor", this.applicationProperties.getProperty("themecolor"));
			homeComponent.setId(contentId);
			if (file != null && !file.isEmpty()) {
				String imagePath = this.applicationProperties.getProperty("imageFolder");
				oldImageURL = imagePath + BASIC_STRINGS.ADMIN.getStringName()
						+ this.applicationProperties.getProperty("homePageContent") + homeComponent.getViewFolder()
						+ "/" + oldImageURL;
				File oldFile = new File(oldImageURL);
				GenUtilities.delete(oldFile);
			} else {
				homeComponent.setImageName(oldImageURL);
			}
			String filePath = adminService.insertUpdateHomeComponent(file, homeComponent, tableName, "update",null);
			model.addAttribute("imagepath", filePath);
			return "admin/insertContentSuccessfull";
		} catch (Exception e) {
			logger.error("error in file upload==" + e);
			return "redirect:updateHomeContent/" + homeComponent.getHomeContentId() + "?error=" + e.getMessage();
		}
	}

	@RequestMapping(value = { "/insertContentSuccessfull" }, method = { RequestMethod.GET })
	public String insertContentSuccessfull(ModelMap model) {
		model = adminService.addUserInModel(model);
		model = adminService.addListHomePageContent(model);
		return "admin/insertContentSuccessfull";
	}

	@RequestMapping(value = { "/deleteContent/{contentId}/{viewFolder}/{tableName}" }, method = {
			RequestMethod.DELETE })
	@ResponseBody
	public String deleteContent(@PathVariable("contentId") long contentId,
			@RequestParam(value = "imageName", required = false) String imageName,
			@PathVariable("viewFolder") String viewFolder, @PathVariable("tableName") String tableName) {
		logger.info(
				"delete content data: " + contentId + " " + imageName + " " + tableName + " viewFolder: " + viewFolder);
		boolean statusDelete = adminService.deleteContent(contentId, imageName, tableName, viewFolder);
		return statusDelete ? "success" : "fail";
	}

}
