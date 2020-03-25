package com.earn.helper.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.earn.helper.system.mapper.OrderinfoDao;
import com.earn.helper.entity.Orderinfo;
import com.earn.helper.system.service.ISystemOrderinfoService;



@Service
public class SystemOrderinfoServiceImpl implements ISystemOrderinfoService {
	@Autowired
	private OrderinfoDao orderinfoDao;
	
	@Override
	public Orderinfo get(Integer orderId){
		return orderinfoDao.get(orderId);
	}
	
	@Override
	public List<Orderinfo> list(Map<String, Object> map){
		return orderinfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return orderinfoDao.count(map);
	}
	
	@Override
	public int save(Orderinfo orderinfo){
		return orderinfoDao.save(orderinfo);
	}
	
	@Override
	public int update(Orderinfo orderinfo){
		return orderinfoDao.update(orderinfo);
	}
	
	@Override
	public int remove(Integer orderId){
		return orderinfoDao.remove(orderId);
	}
	
	@Override
	public int batchRemove(Integer[] orderIds){
		return orderinfoDao.batchRemove(orderIds);
	}
	
	
}
