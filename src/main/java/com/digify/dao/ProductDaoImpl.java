package com.digify.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.digify.model.HomepageContent;
import com.digify.model.Products;
import com.digify.model.RequestQuotes;
import com.digify.model.Services;
import com.digify.utils.DigifyJdbcTemplate;

@Repository
public class ProductDaoImpl extends DigifyJdbcTemplate implements ProductDao {

	private Logger logger = Logger.getLogger(DigifyJdbcTemplate.class);

	@Override
	public <T> List<T> getAllProductServices(Long id, String tableName) {
		logger.debug("getAllProduct start::");
		if (tableName.equals("products")) {
			List<Products> list = null;
			String query = "select * from products;"; // where home_content_id = ?
			try {
				list = getJdbcTemplate().query(query, new BeanPropertyRowMapper<Products>(Products.class));
			} catch (EmptyResultDataAccessException e) {
				logger.error("No role found" + e);
			} catch (DataAccessException e) {
				logger.error("No role found" + e);
			}
			return (List<T>) list;
		} else if (tableName.equals("services")) {

			List<Services> list = null;
			String query = "select * from services;"; // where home_content_id = ?
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
	public <T> T getProductServiceById(Long contentId, String tableName) {
		logger.debug("getProductServiceById ::");
		if (tableName.equals("products")) {
			Products list = null;
			String query = "select * from products where id = ?;";
			try {
				list = getJdbcTemplate().queryForObject(query, new BeanPropertyRowMapper<Products>(Products.class),
						contentId);
			} catch (EmptyResultDataAccessException e) {
				logger.error("No role found" + e);
			} catch (DataAccessException e) {
				logger.error("No role found" + e);
			}
			return (T) list;
		} else if (tableName.equals("services")) {
			Services list = null;
			String query = "select * from services where id = ?;";
			try {
				list = getJdbcTemplate().queryForObject(query, new BeanPropertyRowMapper<Services>(Services.class),
						contentId);
			} catch (EmptyResultDataAccessException e) {
				logger.error("No role found" + e);
			} catch (DataAccessException e) {
				logger.error("No role found" + e);
			}
			return (T) list;
		}
		return null;
	}

	@Override
	public boolean insertQuotes(RequestQuotes req) {
		logger.info("DB updation");
		try {
		String Sqlquery = "INSERT INTO request_quote "
				+ " (person_name , person_email , mobile , quote_details,inquiry_for, inquiry_for_id, inquiry_for_name ) VALUES " + "(?,?,?,? ,?,?,?)";
		int rowInsert = getJdbcTemplate().update(Sqlquery, req.getPersonName(), req.getPersonEmail(), req.getMobile(),
				req.getQuoteDetails() , req.getInquiryFor(),req.getInquiryForId(),req.getInquiryForName());
		return rowInsert > 0 ? true : false;
		} catch (EmptyResultDataAccessException e) {
			logger.error("No role found" + e);
		} catch (DataAccessException e) {
			logger.error("No role found" + e);
		}
		return false;
	}

}
