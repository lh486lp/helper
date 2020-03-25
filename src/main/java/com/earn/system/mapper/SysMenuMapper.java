package com.earn.system.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.earn.system.entity.SysMenu;

/**
 * 菜单管理
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 09:45:09
 */
@Mapper
public interface SysMenuMapper {

    SysMenu get(Integer menuId);

    List<SysMenu> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(SysMenu menu);

    int update(SysMenu menu);

    int remove(Integer menuId);

    int batchRemove(Integer[] menuIds);

    List<SysMenu> listMenuByUserId(Integer id);

    List<String> listUserPerms(Integer id);
}
