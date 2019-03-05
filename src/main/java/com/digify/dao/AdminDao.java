package com.digify.dao;

import java.util.List;

import com.digify.model.HomepageContent;
import com.digify.model.HomepageContentMaster;
import com.digify.model.User;

public interface AdminDao {

	List<HomepageContentMaster> getHomePageContentMaster();

	List<HomepageContent> getAllHomeComponentList(Long userId, Long homePageComponentMasterId);

	String insertFile(User user, String fileName, String tableName, Object obj, String iconFileName);

	HomepageContent getHomeComponentById(Long contentId);

	String updateFile(User user, String fileName, String tableName, Object homeComponent, String iconFileName);

	boolean deleteContent(Long contentId, String imageName, String tableName);

}
