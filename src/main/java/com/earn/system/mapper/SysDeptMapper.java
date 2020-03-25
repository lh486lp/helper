package com.earn.system.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.earn.system.entity.SysDept;

/**
 * 部门管理
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 15:35:39
 */
@Mapper
public interface SysDeptMapper {

    SysDept get(Integer deptId);

    List<SysDept> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(SysDept dept);

    int update(SysDept dept);

    int remove(Integer deptId);

    int batchRemove(Integer[] deptIds);

    Integer[] listParentDept();

    int getDeptUserNumber(Integer deptId);
}
