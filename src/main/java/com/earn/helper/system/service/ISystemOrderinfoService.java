package com.earn.helper.system.service;

import com.earn.helper.entity.Orderinfo;

import java.util.List;
import java.util.Map;

/**
 * 订单信息表
 * 
 * luhui
 * @date 2019-02-11 10:56:13
 */
public interface ISystemOrderinfoService {
	
	Orderinfo get(Integer orderId);
	
	List<Orderinfo> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(Orderinfo orderinfo);
	
	int update(Orderinfo orderinfo);
	
	int remove(Integer orderId);
	
	int batchRemove(Integer[] orderIds);
	
}
