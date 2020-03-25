package com.earn.helper.system.service;

import com.earn.helper.entity.Product;

import java.util.List;
import java.util.Map;

/**
 * 商品信息表
 * 
 * luhui
 * @date 2019-02-11 10:56:15
 */
public interface ISystemProductService {
	
	Product get(Integer productId);
	
	List<Product> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(Product product);
	
	int update(Product product);
	
	int remove(Integer productId);
	
	int batchRemove(Integer[] productIds);
	
}
