package com.earn.system.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.earn.helper.entity.Tree;
import com.earn.system.entity.SysMenu;

@Service
public interface ISysMenuService {
    Tree<SysMenu> getSysMenuTree(Integer id);

    List<Tree<SysMenu>> listMenuTree(Integer id);

    Tree<SysMenu> getTree();

    Tree<SysMenu> getTree(Integer id);

    List<SysMenu> list(Map<String, Object> params);

    int remove(Integer id);

    int save(SysMenu menu);

    int update(SysMenu menu);

    SysMenu get(Integer id);

    String [] listPerms(Integer userId);
}
