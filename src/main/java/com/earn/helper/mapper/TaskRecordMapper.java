package com.earn.helper.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.earn.helper.entity.TaskRecord;

/**
 * <p>
 * 任务记录表 Mapper 接口
 * </p>
 *
 * @author luhui
 * @since 2018-12-21
 */
public interface TaskRecordMapper extends BaseMapper<TaskRecord> {

    List<TaskRecord> selectListPage(@Param("page") Page<TaskRecord> page, @Param("taskRecord") TaskRecord taskRecord);

    Integer getCompleteCount(Map<String, Object> params);

    TaskRecord getByPrimary(TaskRecord taskRecord);

    Integer updateByPrimary(TaskRecord taskRecord);

    void toExpired(String issue);
}