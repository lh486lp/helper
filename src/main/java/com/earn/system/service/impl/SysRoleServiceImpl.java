package com.earn.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.earn.system.entity.SysRole;
import com.earn.system.entity.SysRoleMenu;
import com.earn.system.mapper.SysRoleMapper;
import com.earn.system.mapper.SysRoleMenuMapper;
import com.earn.system.mapper.SysUserMapper;
import com.earn.system.mapper.SysUserRoleMapper;
import com.earn.system.service.ISysRoleService;

@Service
public class SysRoleServiceImpl implements ISysRoleService {

    public static final String ROLE_ALL_KEY = "\"role_all\"";

    public static final String DEMO_CACHE_NAME = "role";

    @Autowired
    SysRoleMapper roleMapper;
    @Autowired
    SysRoleMenuMapper sysRoleMenuMapper;
    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    SysUserRoleMapper sysUserRoleMapper;

    @Override
    public List<SysRole> list() {
        List<SysRole> roles = roleMapper.list(new HashMap<>(16));
        return roles;
    }

    @Override
    public List<SysRole> list(Integer userId) {
        List<Integer> rolesIds = sysUserRoleMapper.listRoleId(userId);
        List<SysRole> roles = roleMapper.list(new HashMap<>(16));
        for (SysRole sysRole : roles) {
            sysRole.setRoleSign("false");
            for (Integer roleId : rolesIds) {
                if (Objects.equals(sysRole.getRoleId(), roleId)) {
                    sysRole.setRoleSign("true");
                    break;
                }
            }
        }
        return roles;
    }

    @Transactional
    @Override
    public int save(SysRole role) {
        int count = roleMapper.save(role);
        List<Integer> menuIds = role.getMenuIds();
        Integer roleId = role.getRoleId();
        List<SysRoleMenu> rms = new ArrayList<>();
        for (Integer menuId : menuIds) {
            SysRoleMenu rmDo = new SysRoleMenu();
            rmDo.setRoleId(roleId);
            rmDo.setMenuId(menuId);
            rms.add(rmDo);
        }
        sysRoleMenuMapper.removeByRoleId(roleId);
        if (rms.size() > 0) {
            sysRoleMenuMapper.batchSave(rms);
        }
        return count;
    }

    @Transactional
    @Override
    public int remove(Integer id) {
        int count = roleMapper.remove(id);
        sysUserRoleMapper.removeByRoleId(id);
        sysRoleMenuMapper.removeByRoleId(id);
        return count;
    }

    @Override
    public SysRole get(Integer id) {
        SysRole sysRole = roleMapper.get(id);
        return sysRole;
    }

    @Override
    public int update(SysRole role) {
        int r = roleMapper.update(role);
        List<Integer> menuIds = role.getMenuIds();
        Integer roleId = role.getRoleId();
        sysRoleMenuMapper.removeByRoleId(roleId);
        List<SysRoleMenu> rms = new ArrayList<>();
        for (Integer menuId : menuIds) {
            SysRoleMenu rmDo = new SysRoleMenu();
            rmDo.setRoleId(roleId);
            rmDo.setMenuId(menuId);
            rms.add(rmDo);
        }
        if (rms.size() > 0) {
            sysRoleMenuMapper.batchSave(rms);
        }
        return r;
    }

    @Override
    public int batchremove(Integer[] ids) {
        int r = roleMapper.batchRemove(ids);
        return r;
    }

}
