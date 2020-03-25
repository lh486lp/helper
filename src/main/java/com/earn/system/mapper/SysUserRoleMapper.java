package com.earn.system.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.earn.system.entity.SysUserRole;

/**
 * 用户与角色对应关系
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 11:08:59
 */
@Mapper
public interface SysUserRoleMapper {

    SysUserRole get(Integer id);

    List<SysUserRole> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(SysUserRole userRole);

    int update(SysUserRole userRole);

    int remove(Integer id);

    int batchRemove(Integer[] ids);

    List<Integer> listRoleId(Integer userId);

    int removeByUserId(Integer userId);

    int removeByRoleId(Integer roleId);

    int batchSave(List<SysUserRole> list);

    int batchRemoveByUserId(Integer[] ids);
}
