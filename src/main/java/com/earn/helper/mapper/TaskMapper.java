package com.earn.helper.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.earn.helper.entity.Task;

/**
 * <p>
 * 任务表 Mapper 接口
 * </p>
 *
 * @author luhui
 * @since 2018-12-22
 */
public interface TaskMapper extends BaseMapper<Task> {

    List<Task> selectListPage(@Param("page") Page<Task> page, @Param("task") Task task);

    Task getTaskById(Task task);
}
