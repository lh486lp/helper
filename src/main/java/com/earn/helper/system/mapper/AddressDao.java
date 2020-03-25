package com.earn.helper.system.mapper;

import com.earn.helper.entity.Address;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 收货地址表
 * luhui
 * @date 2019-02-11 10:56:09
 */
@Mapper
public interface AddressDao {

	Address get(Integer addrId);
	
	List<Address> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(Address address);
	
	int update(Address address);
	
	int remove(Integer addr_id);
	
	int batchRemove(Integer[] addrIds);
	
	
}
