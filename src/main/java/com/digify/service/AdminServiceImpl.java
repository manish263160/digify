package com.digify.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import com.digify.Enums.BASIC_STRINGS;
import com.digify.controller.AdminController;
import com.digify.dao.AdminDao;
import com.digify.model.HomepageContent;
import com.digify.model.HomepageContentMaster;
import com.digify.model.Products;
import com.digify.model.Services;
import com.digify.model.User;
import com.digify.utils.ApplicationProperties;
import com.digify.utils.GenUtilities;

@Service
public class AdminServiceImpl implements AdminService {
	private static final Logger logger = Logger.getLogger(AdminController.class);
	
	@Autowired AdminDao adminDao;
	@Autowired ApplicationProperties applicationProperties;
	@Override
	public List<HomepageContentMaster> getHomePageContentMaster() {
		return adminDao.getHomePageContentMaster();
	}
	@Override
	public List<HomepageContent> getAllHomeComponentList(long homePageComponentMasterId,  String viewsFolder) {
		User user = GenUtilities.getLoggedInUser();
		List<HomepageContent> list =adminDao.getAllHomeComponentList(user.getId() ,homePageComponentMasterId);
		for (HomepageContent homepageContent : list) {
			String ImgUrl = this.applicationProperties.getProperty("appPath")+BASIC_STRINGS.ADMIN.getStringName()+this.applicationProperties.getProperty("homePageContent")
			+viewsFolder+"/"+homepageContent.getImageName();
			homepageContent.setImageUrl(ImgUrl);
		}
		return list;
	}
	@Override
	public String insertUpdateHomeComponent(MultipartFile file, Object content, String tableName , String actionName) {
		User user = GenUtilities.getLoggedInUser();
		
		try {
			String fileExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."),
					file.getOriginalFilename().length());
			SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd_hh-mm-ss");
			Date date = new Date();
			String fileName = formatter.format(date) +"_"+ file.getOriginalFilename();
			String imagePath = this.applicationProperties.getProperty("imageFolder");
			if(tableName.equals("homepage_content")) {
				HomepageContent homeComponent = (HomepageContent) content;
			imagePath = imagePath +BASIC_STRINGS.ADMIN.getStringName()+this.applicationProperties.getProperty("homePageContent")+homeComponent.getViewsFolder()+"/";
			}
			else if(tableName.equals("products")) {
				imagePath = imagePath +BASIC_STRINGS.ADMIN.getStringName()+this.applicationProperties.getProperty("products");
			}
			else if(tableName.equals("services")) {
				imagePath = imagePath +BASIC_STRINGS.ADMIN.getStringName()+this.applicationProperties.getProperty("services");
			}
			File newFile = GenUtilities.uploadFile(imagePath, fileName, file);
			if (newFile != null) {
				fileExtension = fileExtension.replaceFirst("\\.", "");
//				GenUtilities.resizeImage(newFile, fileExtension, 206, 206);
				BufferedImage originalImage = ImageIO.read(newFile);
				/*BufferedImage profileMain = GenUtilities.getScaledInstance(originalImage, 206, 206,
						RenderingHints.VALUE_INTERPOLATION_BILINEAR, true);*/
				boolean isUploaded = ImageIO.write(originalImage, fileExtension, new File(imagePath + fileName));
				if (isUploaded) {
					if (actionName.equals("insert")) {
						String status = null;
						if(content instanceof HomepageContent) {
							HomepageContent homeComponent = (HomepageContent) content;
						status = adminDao.insertFile(user, fileName, tableName, homeComponent);
						
						if ("success".equals(status)) {
							String filepath = setUserUploadedFilePath(user, fileName, "homePageContent");
							return filepath;
						}
						}
						else if(content instanceof Products) {
							Products products = (Products) content;
						status = adminDao.insertFile(user, fileName, tableName, products);
						if ("success".equals(status)) {
							String filepath = setUserUploadedFilePath(user, fileName, "products");
							return filepath;
						}
						}
						else if(content instanceof Services) {
							Services service = (Services) content;
						status = adminDao.insertFile(user, fileName, tableName, service);
						if ("success".equals(status)) {
							String filepath = setUserUploadedFilePath(user, fileName, "services");
							return filepath;
						}
						}
						
					} else if (actionName.equals("update")) {
						String status = null;
						if(content instanceof HomepageContent) {
							HomepageContent homeComponent = (HomepageContent) content;
						status = adminDao.updateFile(user, fileName, tableName, homeComponent);
						if ("success".equals(status)) {
							String filepath = setUserUploadedFilePath(user, fileName, "homePageContent");
							return filepath;
						}
						}
						else if(content instanceof Products) {
							Products products = (Products) content;
						status = adminDao.updateFile(user, fileName, tableName, products);
						if ("success".equals(status)) {
							String filepath = setUserUploadedFilePath(user, fileName, "products");
							return filepath;
						}
						}
						else if(content instanceof Services) {
							Services service = (Services) content;
						status = adminDao.updateFile(user, fileName, tableName, service );
						if ("success".equals(status)) {
							String filepath = setUserUploadedFilePath(user, fileName, "services");
							return filepath;
						}
						}
						
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	
	private String setUserUploadedFilePath(User user, String fileName, String fileType) {
		String url = null;
	
			url = applicationProperties.getProperty("appPath") + user.getId()
			+ (fileType.equals("homePageContent") ? applicationProperties.getProperty("homePageContent") : fileType.equals("products") ? applicationProperties.getProperty("products")
					: applicationProperties.getProperty("services"))
			+ fileName;
		return url;
	}
	@Override
	public HomepageContent getHomeComponentById(long contentId , String viewsFolder) {
		HomepageContent obj = adminDao.getHomeComponentById(contentId);
		String ImgUrl = this.applicationProperties.getProperty("appPath")+BASIC_STRINGS.ADMIN.getStringName()+this.applicationProperties.getProperty("homePageContent")+viewsFolder+"/"
		+obj.getImageName();
		obj.setImageUrl(ImgUrl);
		return obj;
	}
	@Override
	public boolean deleteContent(long contentId, String imageName, String tableName, String viewFolder) {
		if(imageName != null) {
			String imagePath = this.applicationProperties.getProperty("imageFolder");
			imageName = imagePath +BASIC_STRINGS.ADMIN.getStringName()+this.applicationProperties.getProperty("homePageContent")+viewFolder +"/"+imageName;
			File oldFile =new File(imageName);
			try {
				GenUtilities.delete(oldFile);
				boolean bool = adminDao.deleteContent(contentId, imageName, tableName);
				return bool;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	
	public void addUserInModel(ModelMap model) {
		logger.debug("addUserInModel here:");
		User user = GenUtilities.getLoggedInUser();
		model.addAttribute("user", user);		
	}
	
	public void addListHomePageContent(ModelMap model) {
		logger.debug("addListHomePageContent add here:");
		List<HomepageContentMaster> listHomePgcontMaster = getHomePageContentMaster();
		model.addAttribute("listHomePgcontMaster", listHomePgcontMaster);
		
	}
}
