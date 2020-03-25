package com.earn.helper.system.service.impl;

import com.earn.helper.enume.AccountTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.earn.helper.system.mapper.AccountinfoDao;
import com.earn.helper.entity.Accountinfo;
import com.earn.helper.system.service.ISystemAccountinfoService;



@Service
public class SystemAccountinfoServiceImpl implements ISystemAccountinfoService {
	@Autowired
	private AccountinfoDao accountinfoDao;
	
	@Override
	public Accountinfo get(Integer accountId){
		return accountinfoDao.get(accountId);
	}
	
	@Override
	public List<Accountinfo> list(Map<String, Object> map){
		return accountinfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return accountinfoDao.count(map);
	}
	
	@Override
	public int save(Accountinfo accountinfo){
		return accountinfoDao.save(accountinfo);
	}
	
	@Override
	public int update(Accountinfo accountinfo){
		return accountinfoDao.update(accountinfo);
	}
	
	@Override
	public int remove(Integer accountId){
		return accountinfoDao.remove(accountId);
	}
	
	@Override
	public int batchRemove(Integer[] accountIds){
		return accountinfoDao.batchRemove(accountIds);
	}
}
