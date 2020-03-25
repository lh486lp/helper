package com.earn.helper.system.mapper;

import com.earn.helper.entity.Systeminfo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 系统配置信息表
 * luhui
 * @date 2019-02-11 10:56:24
 */
@Mapper
public interface SysteminfoDao {

	Systeminfo get(String key);
	
	List<Systeminfo> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(Systeminfo systeminfo);
	
	int update(Systeminfo systeminfo);
	
	int remove(String _key);
	
	int batchRemove(String[] keys);
	
	
}
