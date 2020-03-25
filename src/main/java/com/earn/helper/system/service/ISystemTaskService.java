package com.earn.helper.system.service;

import com.earn.helper.entity.Task;

import java.util.List;
import java.util.Map;

/**
 * 任务表
 * 
 * luhui
 * @date 2019-02-11 10:56:29
 */
public interface ISystemTaskService {
	
	Task get(Integer taskId);
	
	List<Task> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(Task task);
	
	int update(Task task);
	
	int remove(Integer taskId);
	
	int batchRemove(Integer[] taskIds);
	
}
