package com.earn.helper.system.mapper;

import com.earn.helper.entity.Product;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 商品信息表
 * luhui
 * @date 2019-02-11 10:56:15
 */
@Mapper
public interface ProductDao {

	Product get(Integer productId);
	
	List<Product> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(Product product);
	
	int update(Product product);
	
	int remove(Integer productId);
	
	int batchRemove(Integer[] productIds);
	
	
}
