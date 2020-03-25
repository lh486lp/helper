package com.earn.helper.system.service;

import com.earn.helper.entity.TaskRecord;

import java.util.List;
import java.util.Map;

/**
 * 任务记录表
 * 
 * luhui
 * @date 2019-02-11 10:56:30
 */
public interface ISystemTaskRecordService {
	
	TaskRecord get(Integer recordId);
	
	List<TaskRecord> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TaskRecord taskRecord);
	
	int update(TaskRecord taskRecord);
	
	int remove(Integer userId);
	
	int batchRemove(Integer[] userIds);
	
}
