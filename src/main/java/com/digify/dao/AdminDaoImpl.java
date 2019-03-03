package com.digify.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.digify.model.HomepageContent;
import com.digify.model.HomepageContentMaster;
import com.digify.model.Products;
import com.digify.model.Services;
import com.digify.model.User;
import com.digify.utils.DigifyJdbcTemplate;

@Repository
public class AdminDaoImpl extends DigifyJdbcTemplate implements AdminDao {

	private Logger logger = Logger.getLogger(DigifyJdbcTemplate.class);

	@Override
	public List<HomepageContentMaster> getHomePageContentMaster() {
		logger.debug("getHomePageContent::");
		List<HomepageContentMaster> list = null;
		String query = "select * from homepage_content_m;";
		try {
			list = getJdbcTemplate().query(query,
					new BeanPropertyRowMapper<HomepageContentMaster>(HomepageContentMaster.class));
		} catch (EmptyResultDataAccessException e) {
			logger.error("No role found" + e);
		} catch (DataAccessException e) {
			logger.error("No role found" + e);
		}
		return list;
	}

	@Override
	public List<HomepageContent> getAllHomeComponentList(long userId, long homePageComponentMasterId) {
		logger.debug("getAllHomeComponentList::");
		List<HomepageContent> list = null;
		String query = "select * from homepage_content where home_content_id = ?;";
		try {
			list = getJdbcTemplate().query(query, new BeanPropertyRowMapper<HomepageContent>(HomepageContent.class),homePageComponentMasterId);
		} catch (EmptyResultDataAccessException e) {
			logger.error("No role found" + e);
		} catch (DataAccessException e) {
			logger.error("No role found" + e);
		}
		return list;
	}

	@Override
	public String insertFile(User user, String fileName, String tableName, Object obj) {
		long userid = user.getId();
		// String idColumn =
		// tableName.equals("uploaded_image")||tableName.equals("uploaded_video")?"user_id":"id";
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date curdate1 = new Date();
		String currentTime1 = sdf1.format(curdate1);
		String Sqlquery = "";
		int rowInsert = 0;

			if (obj != null && obj instanceof HomepageContent) {
				HomepageContent homepagecontent = null;
				homepagecontent = (HomepageContent) obj;
				Sqlquery = "INSERT INTO " + tableName + " (user_id,image_name,home_content_id,image_link,content_description,created_on,created_by) VALUES "
						+ "(?,?,?,?,?,'"+ currentTime1 + "',?)";
				rowInsert = getJdbcTemplate().update(Sqlquery, userid, fileName,homepagecontent.getHomeContentId() ,
						homepagecontent.getImageLink(), homepagecontent.getContentDescription(), user.getName());
			}
			else if (obj != null && obj instanceof Products) {
				Products products = null;
				products = (Products) obj;
				Sqlquery = "INSERT INTO " + tableName + " (user_id , product_name , product_description , product_image , external_link , created_on , created_by  ) VALUES "
						+ "(?,?,?,?,?,'"+ currentTime1 + "',?)";
				rowInsert = getJdbcTemplate().update(Sqlquery, userid, products.getProductName(),
						products.getProductDescription(), fileName, products.getExternalLink() , user.getName());
			}
			else if (obj != null && obj instanceof Services) {
				Services services = null;
				services = (Services) obj;
				Sqlquery = "INSERT INTO " + tableName + " (user_id , service_name , service_description , service_image , external_link , created_on , created_by  ) VALUES "
						+ "(?,?,?,?,?,'"+ currentTime1 + "',?)";
				rowInsert = getJdbcTemplate().update(Sqlquery, userid, services.getServiceName() ,
						services.getServiceDescription(), fileName, services.getExternalLink() , user.getName());
			}
		return rowInsert > 0 ? "success" : "fail";
	}

	@Override
	public HomepageContent getHomeComponentById(long contentId) {
		logger.debug("getHomeComponentById ::");
		HomepageContent list = null;
		String query = "select * from homepage_content where id = ?;";
		try {
			list = getJdbcTemplate().queryForObject(query, new BeanPropertyRowMapper<HomepageContent>(HomepageContent.class),contentId);
		} catch (EmptyResultDataAccessException e) {
			logger.error("No role found" + e);
		} catch (DataAccessException e) {
			logger.error("No role found" + e);
		}
		return list;
	}

	@Override
	public String updateFile(User user, String fileName, String tableName, Object content) {
//		long userid = user.getId();
		// String idColumn =
		// tableName.equals("uploaded_image")||tableName.equals("uploaded_video")?"user_id":"id";
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date curdate1 = new Date();
//		String currentTime1 = sdf1.format(curdate1);
		String Sqlquery = "";
		int updateInsert = 0;

		if (content != null) {
			if (content instanceof HomepageContent) {
				HomepageContent homepagecontent = null;
				homepagecontent = (HomepageContent) content;
				Sqlquery = "UPDATE homepage_content SET " + 
						" image_name = ?,image_link = ?,content_description = ?,modified_on = now() WHERE id = ?;";
				updateInsert = getJdbcTemplate().update(Sqlquery, fileName,
						homepagecontent.getImageLink(), homepagecontent.getContentDescription(), homepagecontent.getId());
			}
			
			if (content instanceof Products) {
				Products product = null;
				product = (Products) content;
				Sqlquery = "UPDATE products SET " + 
						" product_image = ?,external_link = ?,product_description = ? , product_name = ?,modified_on = now() WHERE id = ?;";
				updateInsert = getJdbcTemplate().update(Sqlquery, fileName,
						product.getProductImage(), product.getExternalLink(), product.getProductDescription() ,product.getProductImage() ,product.getId());
			}
			if (content instanceof Services) {
				Services service = null;
				service = (Services) content;
				Sqlquery = "UPDATE services SET " + 
						" service_image = ?,external_link = ?,service_description = ? , service_name = ?,modified_on = now() WHERE id = ?;";
				updateInsert = getJdbcTemplate().update(Sqlquery, fileName,
						service.getServiceImage(), service.getExternalLink(), service.getServiceDescription() ,service.getServiceImage() ,service.getId());
			}
		}
		return updateInsert > 0 ? "success" : "fail";
	}

	@Override
	public boolean deleteContent(long contentId, String imageName, String tableName) {
		logger.info("deleteContent ==start ");
		final StringBuilder sql = new StringBuilder();
		sql.append(" delete from ");
		sql.append(tableName);
		sql.append(" where id= ?");
		
		int rowcount = 0;
		rowcount = getJdbcTemplate().update(sql.toString(), contentId);
		if (rowcount > 0) {
			return true;
		}
		return false;
	}

}
