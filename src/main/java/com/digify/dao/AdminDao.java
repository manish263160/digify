package com.digify.dao;

import java.util.List;

import com.digify.model.HomepageContent;
import com.digify.model.HomepageContentMaster;
import com.digify.model.User;

public interface AdminDao {

	List<HomepageContentMaster> getHomePageContentMaster();

	List<HomepageContent> getAllHomeComponentList(long userId, long homePageComponentMasterId);

	String insertFile(User user, String fileName, String tableName, Object obj);

	HomepageContent getHomeComponentById(long contentId);

	String updateFile(User user, String fileName, String tableName, Object homeComponent);

	boolean deleteContent(long contentId, String imageName, String tableName);

}
