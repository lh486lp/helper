package com.earn.helper.system.service;

import com.earn.helper.entity.Systeminfo;

import java.util.List;
import java.util.Map;

/**
 * 系统配置信息表
 * 
 * luhui
 * @date 2019-02-11 10:56:24
 */
public interface ISystemSysteminfoService {
	
	Systeminfo get(String key);
	
	List<Systeminfo> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(Systeminfo systeminfo);
	
	int update(Systeminfo systeminfo);
	
	int remove(String key);
	
	int batchRemove(String[] keys);
	
}
