package com.earn.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.earn.helper.entity.Tree;
import com.earn.common.util.BuildTree;
import com.earn.system.entity.SysDept;
import com.earn.system.mapper.SysDeptMapper;
import com.earn.system.service.ISysDeptService;

@Service
public class SysDeptServiceImpl implements ISysDeptService {
    @Autowired
    private SysDeptMapper sysSysDeptMapper;

    @Override
    public SysDept get(Integer deptId) {
        return sysSysDeptMapper.get(deptId);
    }

    @Override
    public List<SysDept> list(Map<String, Object> map) {
        return sysSysDeptMapper.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return sysSysDeptMapper.count(map);
    }

    @Override
    public int save(SysDept sysDept) {
        return sysSysDeptMapper.save(sysDept);
    }

    @Override
    public int update(SysDept sysDept) {
        return sysSysDeptMapper.update(sysDept);
    }

    @Override
    public int remove(Integer deptId) {
        return sysSysDeptMapper.remove(deptId);
    }

    @Override
    public int batchRemove(Integer[] deptIds) {
        return sysSysDeptMapper.batchRemove(deptIds);
    }

    @Override
    public Tree<SysDept> getTree() {
        List<Tree<SysDept>> trees = new ArrayList<Tree<SysDept>>();
        List<SysDept> sysDepts = sysSysDeptMapper.list(new HashMap<String, Object>(16));
        for (SysDept sysDept : sysDepts) {
            Tree<SysDept> tree = new Tree<SysDept>();
            tree.setId(sysDept.getDeptId().toString());
            tree.setParentId(sysDept.getParentId().toString());
            tree.setText(sysDept.getName());
            Map<String, Object> state = new HashMap<>(16);
            state.put("opened", true);
            tree.setState(state);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<SysDept> t = BuildTree.build(trees);
        return t;
    }

    @Override
    public boolean checkDeptHasUser(Integer deptId) {
        // TODO Auto-generated method stub
        // 查询部门以及此部门的下级部门
        int result = sysSysDeptMapper.getDeptUserNumber(deptId);
        return result == 0 ? true : false;
    }

    @Override
    public List<Integer> listChildrenIds(Integer parentId) {
        List<SysDept> sysDepts = list(null);
        return treeMenuList(sysDepts, parentId);
    }

    List<Integer> treeMenuList(List<SysDept> menuList, long pid) {
        List<Integer> childIds = new ArrayList<>();
        for (SysDept mu : menuList) {
            // 遍历出父id等于参数的id，add进子节点集合
            if (mu.getParentId() == pid) {
                // 递归遍历下一级
                treeMenuList(menuList, mu.getDeptId());
                childIds.add(mu.getDeptId());
            }
        }
        return childIds;
    }

}
