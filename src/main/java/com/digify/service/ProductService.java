/**
 * 
 */
package com.digify.service;

import java.util.List;

import com.digify.model.Products;

/**
 * @author mmandal
 *
 */
public interface ProductService {

	<T> List<T> getAllProductServices(String tableName);

	<T> T getProductServiceById(long contentId, String tableName);

	boolean deleteContent(long contentId, String imageName, String tableName);

}
