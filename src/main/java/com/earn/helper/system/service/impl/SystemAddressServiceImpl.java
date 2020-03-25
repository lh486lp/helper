package com.earn.helper.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.earn.helper.system.mapper.AddressDao;
import com.earn.helper.entity.Address;
import com.earn.helper.system.service.ISystemAddressService;



@Service
public class SystemAddressServiceImpl implements ISystemAddressService {
	@Autowired
	private AddressDao addressDao;
	
	@Override
	public Address get(Integer addrId){
		return addressDao.get(addrId);
	}
	
	@Override
	public List<Address> list(Map<String, Object> map){
		return addressDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return addressDao.count(map);
	}
	
	@Override
	public int save(Address address){
		return addressDao.save(address);
	}
	
	@Override
	public int update(Address address){
		return addressDao.update(address);
	}
	
	@Override
	public int remove(Integer addrId){
		return addressDao.remove(addrId);
	}
	
	@Override
	public int batchRemove(Integer[] addrIds){
		return addressDao.batchRemove(addrIds);
	}
	
	
}
