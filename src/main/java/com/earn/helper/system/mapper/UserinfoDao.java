package com.earn.helper.system.mapper;

import com.earn.helper.entity.Userinfo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 用户信息表
 * luhui
 * @date 2019-02-11 10:56:33
 */
@Mapper
public interface UserinfoDao {

	Userinfo get(Integer userId);
	
	List<Userinfo> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(Userinfo userinfo);
	
	int update(Userinfo userinfo);
	
	int remove(Integer user_id);
	
	int batchRemove(Integer[] userIds);
	
	
}
