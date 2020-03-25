package com.earn.system.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.earn.system.entity.SysRoleMenu;

/**
 * 角色与菜单对应关系
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 11:08:59
 */
@Mapper
public interface SysRoleMenuMapper {

    SysRoleMenu get(Integer id);

    List<SysRoleMenu> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(SysRoleMenu roleMenu);

    int update(SysRoleMenu roleMenu);

    int remove(Integer id);

    int batchRemove(Integer[] ids);

    List<Integer> listMenuIdByRoleId(Integer roleId);

    int removeByRoleId(Integer roleId);

    int removeByMenuId(Integer menuId);

    int batchSave(List<SysRoleMenu> list);
}
