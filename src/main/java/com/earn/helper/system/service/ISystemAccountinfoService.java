package com.earn.helper.system.service;

import com.earn.helper.entity.Accountinfo;
import com.earn.helper.enume.AccountTypeEnum;

import java.util.List;
import java.util.Map;

/**
 * 账户信息表
 * 
 * luhui
 * @date 2019-02-11 12:16:58
 */
public interface ISystemAccountinfoService {
	
	Accountinfo get(Integer accountId);
	
	List<Accountinfo> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(Accountinfo accountinfo);
	
	int update(Accountinfo accountinfo);
	
	int remove(Integer accountId);
	
	int batchRemove(Integer[] accountIds);
}
