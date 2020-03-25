package com.earn.helper.system.service;

import com.earn.helper.entity.Address;

import java.util.List;
import java.util.Map;

/**
 * 收货地址表
 * 
 * luhui
 * @date 2019-02-11 10:56:09
 */
public interface ISystemAddressService {
	
	Address get(Integer addrId);
	
	List<Address> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(Address address);
	
	int update(Address address);
	
	int remove(Integer addrId);
	
	int batchRemove(Integer[] addrIds);
	
}
