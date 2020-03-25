package com.earn.helper.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.earn.helper.system.mapper.SysteminfoDao;
import com.earn.helper.entity.Systeminfo;
import com.earn.helper.system.service.ISystemSysteminfoService;



@Service
public class SystemSysteminfoServiceImpl implements ISystemSysteminfoService {
	@Autowired
	private SysteminfoDao systeminfoDao;
	
	@Override
	public Systeminfo get(String key){
		return systeminfoDao.get(key);
	}
	
	@Override
	public List<Systeminfo> list(Map<String, Object> map){
		return systeminfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return systeminfoDao.count(map);
	}
	
	@Override
	public int save(Systeminfo systeminfo){
		return systeminfoDao.save(systeminfo);
	}
	
	@Override
	public int update(Systeminfo systeminfo){
		return systeminfoDao.update(systeminfo);
	}
	
	@Override
	public int remove(String key){
		return systeminfoDao.remove(key);
	}
	
	@Override
	public int batchRemove(String[] keys){
		return systeminfoDao.batchRemove(keys);
	}
	
	
}
