package com.earn.system.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.earn.system.entity.SysRole;

@Service
public interface ISysRoleService {

    SysRole get(Integer id);

    List<SysRole> list();

    int save(SysRole role);

    int update(SysRole role);

    int remove(Integer id);

    List<SysRole> list(Integer userId);

    int batchremove(Integer[] ids);
}
