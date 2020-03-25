package com.earn.helper.system.service;

import com.earn.helper.entity.Tree;
import com.earn.helper.entity.Userinfo;

import java.util.List;
import java.util.Map;

/**
 * 用户信息表
 * 
 * luhui
 * @date 2019-02-11 10:56:33
 */
public interface ISystemUserinfoService {
	
	Userinfo get(Integer userId);
	
	List<Userinfo> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(Userinfo userinfo);
	
	int update(Userinfo userinfo);
	
	int remove(Integer userId);
	
	int batchRemove(Integer[] userIds);

    List<Tree<Userinfo>> getFansTree(Integer userId);
}
