package com.digify.service;

import java.util.List;

import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import com.digify.model.HomepageContent;
import com.digify.model.HomepageContentMaster;
import com.digify.model.UserOrderResponseDto;
import com.digify.model.UserOrders;

public interface AdminService {

    List<HomepageContentMaster> getHomePageContentMaster();

    List<HomepageContent> getAllHomeComponentList(Long homePageComponentMasterId, String viewsFolder);

    String insertUpdateHomeComponent(MultipartFile file, Object content, String tableName, String actionName, MultipartFile iconImg);

    HomepageContent getHomeComponentById(Long contentId, String viewsFolder);

    boolean deleteContent(Long contentId, String imageName, String tableName, String viewFolder);

    ModelMap addUserInModel(ModelMap model);

    ModelMap addListHomePageContent(ModelMap model);

	List<UserOrders> getAllOrders();

	List<UserOrderResponseDto> getOrderByOrderId(String orderId);

}
