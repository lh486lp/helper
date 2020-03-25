package com.earn.helper.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.earn.helper.system.mapper.TaskDao;
import com.earn.helper.entity.Task;
import com.earn.helper.system.service.ISystemTaskService;



@Service
public class SystemTaskServiceImpl implements ISystemTaskService {
	@Autowired
	private TaskDao taskDao;
	
	@Override
	public Task get(Integer taskId){
		return taskDao.get(taskId);
	}
	
	@Override
	public List<Task> list(Map<String, Object> map){
		return taskDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return taskDao.count(map);
	}
	
	@Override
	public int save(Task task){
		return taskDao.save(task);
	}
	
	@Override
	public int update(Task task){
		return taskDao.update(task);
	}
	
	@Override
	public int remove(Integer taskId){
		return taskDao.remove(taskId);
	}
	
	@Override
	public int batchRemove(Integer[] taskIds){
		return taskDao.batchRemove(taskIds);
	}
	
	
}
