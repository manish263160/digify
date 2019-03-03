package com.digify.service;

import java.util.List;

import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import com.digify.model.HomepageContent;
import com.digify.model.HomepageContentMaster;
import com.digify.model.User;
import com.digify.utils.GenUtilities;

public interface AdminService {

	List<HomepageContentMaster> getHomePageContentMaster();

	List<HomepageContent> getAllHomeComponentList(long homePageComponentMasterId, String viewsFolder);

	String insertUpdateHomeComponent(MultipartFile file, Object content, String tableName , String actionName);

	HomepageContent getHomeComponentById(long contentId, String viewsFolder);

	boolean deleteContent(long contentId, String imageName, String tableName, String viewFolder);
	
	void addUserInModel(ModelMap model);
	
	void addListHomePageContent(ModelMap model); 

}
