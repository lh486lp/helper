package com.earn.helper.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.earn.common.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.earn.common.util.Const;
import com.earn.helper.entity.TaskRecord;
import com.earn.helper.mapper.TaskRecordMapper;
import com.earn.helper.service.ITaskRecordService;

/**
 * <p>
 * 任务记录表 服务实现类
 * </p>
 *
 * @author luhui
 * @since 2018-12-21
 */
@Service
public class TaskRecordServiceImpl extends ServiceImpl<TaskRecordMapper, TaskRecord> implements ITaskRecordService {
    @Autowired
    private TaskRecordMapper taskRecordMapper;

    @Override
    public Page<TaskRecord> pageList(TaskRecord taskRecord) {
        Page<TaskRecord> page = new Page<>(taskRecord.getPage(), taskRecord.getSize());
        List<TaskRecord> records = taskRecordMapper.selectListPage(page, taskRecord);
        for (TaskRecord record : records) {
            if (!StringUtil.isNullAndBlank(record.getImgPath())) {
                record.setImgPath(Const.FILE_PATH + record.getImgPath());
            }
        }
        return page.setRecords(records);

    }

    @Override
    public Integer getGoldCount(Integer userId, String issue) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("taskLevel", 2);
        params.put("issue", issue);
        return taskRecordMapper.getCompleteCount(params);
    }

    @Override
    public Integer getDiamondCount(Integer userId, String issue) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("taskLevel", 3);
        params.put("issue", issue);
        return taskRecordMapper.getCompleteCount(params);
    }

    @Override
    public TaskRecord getByPrimary(TaskRecord taskRecord) {
        TaskRecord record = taskRecordMapper.getByPrimary(taskRecord);
        if (!StringUtil.isNullAndBlank(record.getImgPath())) {
            record.setImgPath(Const.FILE_PATH + record.getImgPath());
        }
        return record;
    }

    @Override
    public boolean updateByPrimary(TaskRecord taskRecord) {
        return taskRecordMapper.updateByPrimary(taskRecord) > 0;
    }

    @Override
    public void toExpired(String issue) {
        taskRecordMapper.toExpired(issue);
    }
}
