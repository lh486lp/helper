package com.earn.system.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.earn.system.entity.SysUser;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 09:45:11
 */
@Mapper
public interface SysUserMapper {

    SysUser get(Integer userId);

    List<SysUser> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(SysUser user);

    int update(SysUser user);

    int remove(Integer userId);

    int batchRemove(Integer[] userIds);

    Integer[] listAllDept();

    SysUser selectByName(String username);
}
