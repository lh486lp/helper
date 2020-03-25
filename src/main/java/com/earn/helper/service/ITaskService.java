package com.earn.helper.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.earn.helper.entity.Task;

/**
 * <p>
 * 任务表 服务类
 * </p>
 *
 * @author luhui
 * @since 2018-12-22
 */
public interface ITaskService extends IService<Task> {

    /**
     * 分页查询列表
     *
     * @param task
     * @return
     */
    Page<Task> pageList(Task task);

    Task getTaskById(Task task);
}
