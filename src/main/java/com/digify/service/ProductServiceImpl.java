/**
 * 
 */
package com.digify.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digify.Enums.BASIC_STRINGS;
import com.digify.dao.AdminDao;
import com.digify.dao.ProductDao;
import com.digify.model.HomepageContent;
import com.digify.model.Products;
import com.digify.model.Services;
import com.digify.model.User;
import com.digify.utils.ApplicationProperties;
import com.digify.utils.GenUtilities;

/**
 * @author mmandal
 *
 */
@Service
public class ProductServiceImpl implements ProductService {

	private static final Logger logger = Logger.getLogger(ProductServiceImpl.class);
	
	@Autowired
	ProductDao productDao ;
	@Autowired
	private ApplicationProperties applicationProperties;
	@Autowired
	AdminDao adminDao;
	@Override
	public <T> List<T> getAllProductServices(String tableName) {
		User user = GenUtilities.getLoggedInUser();
		if(tableName.equals("products")) {
		List<Products> list =productDao.getAllProductServices(user.getId() ,tableName);
		for (Products products : list) {
			String ImgUrl = this.applicationProperties.getProperty("appPath")+BASIC_STRINGS.ADMIN.getStringName()+this.applicationProperties.getProperty("products")
			+products.getProductImage();
			products.setImageUrl(ImgUrl);
		}
		return (List<T>) list;
		}
		else if(tableName.equals("services")) {
			List<Services> list =productDao.getAllProductServices(user.getId() ,tableName);
			for (Services products : list) {
				String ImgUrl = this.applicationProperties.getProperty("appPath")+BASIC_STRINGS.ADMIN.getStringName()+this.applicationProperties.getProperty("services")
				+products.getServiceImage();
				products.setImageUrl(ImgUrl);
			}
			return (List<T>) list;
		}
		return null;
	}

	@Override
	public <T> T getProductServiceById(long contentId, String tableName) {
		if(tableName.equals("products")) {
		Products obj = productDao.getProductServiceById(contentId, tableName);
		String ImgUrl = this.applicationProperties.getProperty("appPath")+BASIC_STRINGS.ADMIN.getStringName()+this.applicationProperties.getProperty("products")
		+obj.getProductImage();
		obj.setImageUrl(ImgUrl);
		return (T) obj;
		}
		else if(tableName.equals("services")) {
			Services obj = productDao.getProductServiceById(contentId, tableName);
			String ImgUrl = this.applicationProperties.getProperty("appPath")+BASIC_STRINGS.ADMIN.getStringName()+this.applicationProperties.getProperty("services")
			+obj.getServiceImage();
			obj.setImageUrl(ImgUrl);
			return (T) obj;
		}
		return null;
	}

	@Override
	public boolean deleteContent(long contentId, String imageName, String tableName) {
		if(imageName != null) {
			String imagePath = this.applicationProperties.getProperty("imageFolder");
			imageName = imagePath +BASIC_STRINGS.ADMIN.getStringName()+this.applicationProperties.getProperty(tableName)+imageName;
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
}
