/**
 * 
 */
package com.digify.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.digify.Enums.BASIC_STRINGS;
import com.digify.dao.AdminDao;
import com.digify.dao.ProductDao;
import com.digify.model.Products;
import com.digify.model.RequestQuotes;
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
	ProductDao productDao;
	@Autowired
	private ApplicationProperties applicationProperties;
	@Autowired
	AdminDao adminDao;
	
	@Autowired
	MailingService mailingService;

	@Value("${mail.username}")
	private String senderMailId;
	
	private @Autowired VelocityEngine velocityEngine;
	
	@Override
	public <T> List<T> getAllProductServices(String tableName) {
		User user = GenUtilities.getLoggedInUser();
		if (tableName.equals("products")) {
			Long id = user != null ? user.getId() : null;
			List<Products> list = productDao.getAllProductServices(id, tableName);
			for (Products products : list) {
				String ImgUrl = this.applicationProperties.getProperty("appPath") + BASIC_STRINGS.ADMIN.getStringName()
						+ this.applicationProperties.getProperty("products") + products.getProductImage();
				products.setImageUrl(ImgUrl);

				String iconImgUrl = this.applicationProperties.getProperty("appPath")
						+ BASIC_STRINGS.ADMIN.getStringName() + this.applicationProperties.getProperty("products")
						+ products.getIconImg();
				products.setIconImgUrl(iconImgUrl);
			}
			return (List<T>) list;
		} else if (tableName.equals("services")) {
			Long id = user != null ? user.getId() : null;
			List<Services> list = productDao.getAllProductServices(id, tableName);
			for (Services products : list) {
				String ImgUrl = this.applicationProperties.getProperty("appPath") + BASIC_STRINGS.ADMIN.getStringName()
						+ this.applicationProperties.getProperty("services") + products.getServiceImage();
				products.setImageUrl(ImgUrl);
				String iconImgUrl = this.applicationProperties.getProperty("appPath")
						+ BASIC_STRINGS.ADMIN.getStringName() + this.applicationProperties.getProperty("services")
						+ products.getIconImg();
				products.setIconImgUrl(iconImgUrl);
			}
			return (List<T>) list;
		}
		return null;
	}

	@Override
	public <T> T getProductServiceById(long contentId, String tableName) {
		if (tableName.equals("products")) {
			Products obj = productDao.getProductServiceById(contentId, tableName);
			String ImgUrl = this.applicationProperties.getProperty("appPath") + BASIC_STRINGS.ADMIN.getStringName()
					+ this.applicationProperties.getProperty("products") + obj.getProductImage();
			obj.setImageUrl(ImgUrl);
			String iconImgUrl = this.applicationProperties.getProperty("appPath") + BASIC_STRINGS.ADMIN.getStringName()
					+ this.applicationProperties.getProperty("products") + obj.getIconImg();
			obj.setIconImgUrl(iconImgUrl);
			return (T) obj;
		} else if (tableName.equals("services")) {
			Services obj = productDao.getProductServiceById(contentId, tableName);
			String ImgUrl = this.applicationProperties.getProperty("appPath") + BASIC_STRINGS.ADMIN.getStringName()
					+ this.applicationProperties.getProperty("services") + obj.getServiceImage();
			obj.setImageUrl(ImgUrl);
			String iconImgUrl = this.applicationProperties.getProperty("appPath") + BASIC_STRINGS.ADMIN.getStringName()
					+ this.applicationProperties.getProperty("services") + obj.getIconImg();
			obj.setIconImgUrl(iconImgUrl);
			return (T) obj;
		}
		return null;
	}

	@Override
	public boolean deleteContent(long contentId, String imageName, String tableName) {
		if (imageName != null && !imageName.isEmpty()) {
			String imagePath = this.applicationProperties.getProperty("imageFolder");
			imageName = imagePath + BASIC_STRINGS.ADMIN.getStringName()
					+ this.applicationProperties.getProperty(tableName) + imageName;
			File oldFile = new File(imageName);
			try {
				GenUtilities.delete(oldFile);
				boolean bool = adminDao.deleteContent(contentId, imageName, tableName);
				return bool;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			boolean bool = adminDao.deleteContent(contentId, imageName, tableName);
		}
		return false;
	}

	@Override
	public boolean insertQuotes(RequestQuotes requestQuotes) {
		boolean bool = productDao.insertQuotes(requestQuotes);
		if(bool) {
			String email = requestQuotes.getPersonEmail();
			if(GenUtilities.isValidEmail(email)) {
				Map<String, Object> storemap = new HashMap<String, Object>();
				storemap.put("name", requestQuotes.getPersonName());
				storemap.put("DigifyTeam", "Digify Team");
				String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
						"email_Templates/sendEnquiry.vm", "UTF-8", storemap);
				mailingService.sendMail(senderMailId, new String[] { email }, null, "Request A Quote", text);
			}
		}
		return bool;
	}
}