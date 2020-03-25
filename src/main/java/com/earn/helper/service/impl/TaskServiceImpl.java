package com.earn.helper.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.earn.common.util.Const;
import com.earn.common.util.StringUtil;
import com.earn.helper.entity.Task;
import com.earn.helper.mapper.TaskMapper;
import com.earn.helper.service.ITaskService;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 任务表 服务实现类
 * </p>
 *
 * @author luhui
 * @since 2018-12-22
 */
@Slf4j
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements ITaskService {
    @Autowired
    private TaskMapper taskMapper;

    @Override
    public Page<Task> pageList(Task task) {
        Page<Task> page = new Page<>(task.getPage(), task.getSize());
        List<Task> records = taskMapper.selectListPage(page, task);
        for (Task record : records) {
            if (!StringUtil.isNullAndBlank(record.getTaskContent())) {
                record.setTaskContent(record.getTaskContent().replaceAll(Const.FILE_GET_METHOD_REG, Const.FILE_PATH));
            }
            if (!StringUtil.isNullAndBlank(record.getImgPaths())) {
                StringBuilder paths = new StringBuilder();
                String[] imgPaths = record.getImgPaths().split(",");
                for (String imgPath : imgPaths) {
                    paths.append(",").append(Const.FILE_PATH).append(imgPath);
                }
                record.setImgPaths(paths.substring(1).toString());
            }
        }
        return page.setRecords(records);
    }

    @Override
    public Task getTaskById(Task task) {
        task = taskMapper.getTaskById(task);
        if (!StringUtil.isNullAndBlank(task.getTaskContent())) {
            task.setTaskContent(task.getTaskContent().replaceAll(Const.FILE_GET_METHOD_REG, Const.FILE_PATH));
        }
        if (!StringUtil.isNullAndBlank(task.getImgPaths())) {
            StringBuilder paths = new StringBuilder();
            String[] imgPaths = task.getImgPaths().split(",");
            for (String imgPath : imgPaths) {
                paths.append(",").append(Const.FILE_PATH).append(imgPath);
            }
            task.setImgPaths(paths.substring(1).toString());
        }
        return task;
    }
}
