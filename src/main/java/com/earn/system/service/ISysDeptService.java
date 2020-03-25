package com.earn.system.service;

import java.util.List;
import java.util.Map;

import com.earn.helper.entity.Tree;
import com.earn.system.entity.SysDept;

/**
 * 部门管理
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-27 14:28:36
 */
public interface ISysDeptService {

    SysDept get(Integer deptId);

    List<SysDept> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(SysDept sysDept);

    int update(SysDept sysDept);

    int remove(Integer deptId);

    int batchRemove(Integer[] deptIds);

    Tree<SysDept> getTree();

    boolean checkDeptHasUser(Integer deptId);

    List<Integer> listChildrenIds(Integer parentId);
}
