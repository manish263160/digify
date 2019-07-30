package com.digify.dao;

import java.util.List;

import com.digify.model.RequestQuotes;

public interface ProductDao {

    <T> List<T> getAllProductServices(Long id, String tableName);

    <T> T getProductServiceById(Long contentId, String tableName);

    boolean insertQuotes(RequestQuotes requestQuotes);

}
