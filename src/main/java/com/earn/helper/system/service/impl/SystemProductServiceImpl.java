package com.earn.helper.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.earn.helper.system.mapper.ProductDao;
import com.earn.helper.entity.Product;
import com.earn.helper.system.service.ISystemProductService;



@Service
public class SystemProductServiceImpl implements ISystemProductService {
	@Autowired
	private ProductDao productDao;
	
	@Override
	public Product get(Integer productId){
		return productDao.get(productId);
	}
	
	@Override
	public List<Product> list(Map<String, Object> map){
		return productDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return productDao.count(map);
	}
	
	@Override
	public int save(Product product){
		return productDao.save(product);
	}
	
	@Override
	public int update(Product product){
		return productDao.update(product);
	}
	
	@Override
	public int remove(Integer productId){
		return productDao.remove(productId);
	}
	
	@Override
	public int batchRemove(Integer[] productIds){
		return productDao.batchRemove(productIds);
	}
	
	
}
