package com.digify.dao;

import java.util.List;

import com.digify.model.Products;

public interface ProductDao {

	<T> List<T> getAllProductServices(long id, String tableName);

	<T> T getProductServiceById(long contentId, String tableName);

}
