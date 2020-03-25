package com.earn.helper.system.mapper;

import com.earn.helper.entity.Accountinfo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 账户信息表
 * luhui
 * @date 2019-02-11 12:16:58
 */
@Mapper
public interface AccountinfoDao {

	Accountinfo get(Integer accountId);
	
	List<Accountinfo> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(Accountinfo accountinfo);
	
	int update(Accountinfo accountinfo);
	
	int remove(Integer account_id);
	
	int batchRemove(Integer[] accountIds);
	
	
}
