package com.earn.system.service.impl;

import java.util.*;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.earn.common.util.BuildTree;
import com.earn.helper.entity.Tree;
import com.earn.system.entity.SysMenu;
import com.earn.system.mapper.SysMenuMapper;
import com.earn.system.mapper.SysRoleMenuMapper;
import com.earn.system.service.ISysMenuService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class SysMenuServiceImpl implements ISysMenuService {
    @Autowired
    SysMenuMapper sysMenuMapper;
    @Autowired
    SysRoleMenuMapper sysRoleMenuMapper;

    /**
     * @param
     * @param id
     * @return 树形菜单
     */
    @Cacheable
    @Override
    public Tree<SysMenu> getSysMenuTree(Integer id) {
        List<Tree<SysMenu>> trees = new ArrayList<Tree<SysMenu>>();
        List<SysMenu> sysMenus = sysMenuMapper.listMenuByUserId(id);
        for (SysMenu sysMenu : sysMenus) {
            Tree<SysMenu> tree = new Tree<SysMenu>();
            tree.setId(sysMenu.getMenuId().toString());
            tree.setParentId(sysMenu.getParentId().toString());
            tree.setText(sysMenu.getName());
            Map<String, Object> attributes = new HashMap<>(16);
            attributes.put("url", sysMenu.getUrl());
            attributes.put("icon", sysMenu.getIcon());
            tree.setAttributes(attributes);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<SysMenu> t = BuildTree.build(trees);
        return t;
    }

    @Override
    public List<SysMenu> list(Map<String, Object> params) {
        List<SysMenu> menus = sysMenuMapper.list(params);
        return menus;
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public int remove(Integer id) {
        int result = sysMenuMapper.remove(id);
        return result;
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public int save(SysMenu menu) {
        int r = sysMenuMapper.save(menu);
        return r;
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public int update(SysMenu menu) {
        int r = sysMenuMapper.update(menu);
        return r;
    }

    @Override
    public SysMenu get(Integer id) {
        SysMenu sysMenu = sysMenuMapper.get(id);
        return sysMenu;
    }

    @Override
    public Tree<SysMenu> getTree() {
        List<Tree<SysMenu>> trees = new ArrayList<Tree<SysMenu>>();
        List<SysMenu> sysMenus = sysMenuMapper.list(new HashMap<>(16));
        for (SysMenu sysMenu : sysMenus) {
            Tree<SysMenu> tree = new Tree<SysMenu>();
            tree.setId(sysMenu.getMenuId().toString());
            tree.setParentId(sysMenu.getParentId().toString());
            tree.setText(sysMenu.getName());
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<SysMenu> t = BuildTree.build(trees);
        return t;
    }

    @Override
    public Tree<SysMenu> getTree(Integer id) {
        // 根据roleId查询权限
        List<SysMenu> menus = sysMenuMapper.list(new HashMap<String, Object>(16));
        List<Integer> menuIds = sysRoleMenuMapper.listMenuIdByRoleId(id);
        List<Integer> temp = menuIds;
        for (SysMenu menu : menus) {
            if (temp.contains(menu.getParentId())) {
                menuIds.remove(menu.getParentId());
            }
        }
        List<Tree<SysMenu>> trees = new ArrayList<Tree<SysMenu>>();
        List<SysMenu> sysMenus = sysMenuMapper.list(new HashMap<String, Object>(16));
        for (SysMenu sysMenu : sysMenus) {
            Tree<SysMenu> tree = new Tree<SysMenu>();
            tree.setId(sysMenu.getMenuId().toString());
            tree.setParentId(sysMenu.getParentId().toString());
            tree.setText(sysMenu.getName());
            Map<String, Object> state = new HashMap<>(16);
            Integer menuId = sysMenu.getMenuId();
            if (menuIds.contains(menuId)) {
                state.put("selected", true);
            } else {
                state.put("selected", false);
            }
            tree.setState(state);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<SysMenu> t = BuildTree.build(trees);
        return t;
    }

    @Override
    public String [] listPerms(Integer userId) {
        List<String> perms = sysMenuMapper.listUserPerms(userId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms) {
            if (StringUtils.isNotBlank(perm)) {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet.toArray(new String[0]);
    }

    @Override
    public List<Tree<SysMenu>> listMenuTree(Integer id) {
        List<Tree<SysMenu>> trees = new ArrayList<Tree<SysMenu>>();
        List<SysMenu> sysMenus = sysMenuMapper.listMenuByUserId(id);
        for (SysMenu sysMenu : sysMenus) {
            Tree<SysMenu> tree = new Tree<SysMenu>();
            tree.setId(sysMenu.getMenuId().toString());
            tree.setParentId(sysMenu.getParentId().toString());
            tree.setText(sysMenu.getName());
            Map<String, Object> attributes = new HashMap<>(16);
            attributes.put("url", sysMenu.getUrl());
            attributes.put("icon", sysMenu.getIcon());
            tree.setAttributes(attributes);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        List<Tree<SysMenu>> list = BuildTree.buildList(trees, "0");
        return list;
    }

}
