package com.earn.system.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.earn.system.entity.SysRole;

/**
 * 角色
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-02 20:24:47
 */
@Mapper
public interface SysRoleMapper {

    SysRole get(Integer roleId);

    List<SysRole> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(SysRole role);

    int update(SysRole role);

    int remove(Integer roleId);

    int batchRemove(Integer[] roleIds);
}
