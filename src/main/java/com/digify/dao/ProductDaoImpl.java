package com.digify.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.digify.model.HomepageContent;
import com.digify.model.Products;
import com.digify.model.Services;
import com.digify.utils.DigifyJdbcTemplate;

@Repository
public class ProductDaoImpl extends DigifyJdbcTemplate implements ProductDao{

	private Logger logger = Logger.getLogger(DigifyJdbcTemplate.class);
	@Override
	public <T> List<T> getAllProductServices(long id,String tableName) {
		logger.debug("getAllProduct start::");
		if(tableName.equals("products")) {
		List<Products> list = null;
		String query = "select * from products;";  // where home_content_id = ?
		try {
			list = getJdbcTemplate().query(query, new BeanPropertyRowMapper<Products>(Products.class));
		} catch (EmptyResultDataAccessException e) {
			logger.error("No role found" + e);
		} catch (DataAccessException e) {
			logger.error("No role found" + e);
		}
		return (List<T>) list;
		}
		else if(tableName.equals("services")) {

			List<Services> list = null;
			String query = "select * from services;";  // where home_content_id = ?
			try {
				list = getJdbcTemplate().query(query, new BeanPropertyRowMapper<Services>(Services.class));
			} catch (EmptyResultDataAccessException e) {
				logger.error("No role found" + e);
			} catch (DataAccessException e) {
				logger.error("No role found" + e);
			}
			return (List<T>) list;			
		}
		return null;
	}
	@Override
	public <T> T getProductServiceById(long contentId , String tableName) {
		logger.debug("getProductServiceById ::");
		if(tableName.equals("products")) {
		Products list = null;
		String query = "select * from products where id = ?;";
		try {
			list = getJdbcTemplate().queryForObject(query, new BeanPropertyRowMapper<Products>(Products.class),contentId);
		} catch (EmptyResultDataAccessException e) {
			logger.error("No role found" + e);
		} catch (DataAccessException e) {
			logger.error("No role found" + e);
		}
		return (T) list;
		}
		else if(tableName.equals("services")) {
			Services list = null;
			String query = "select * from services where id = ?;";
			try {
				list = getJdbcTemplate().queryForObject(query, new BeanPropertyRowMapper<Services>(Services.class),contentId);
			} catch (EmptyResultDataAccessException e) {
				logger.error("No role found" + e);
			} catch (DataAccessException e) {
				logger.error("No role found" + e);
			}
			return (T) list;
		}
		return null;
	}

}
