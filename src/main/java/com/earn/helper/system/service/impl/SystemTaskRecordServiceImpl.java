package com.earn.helper.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.earn.helper.system.mapper.TaskRecordDao;
import com.earn.helper.entity.TaskRecord;
import com.earn.helper.system.service.ISystemTaskRecordService;



@Service
public class SystemTaskRecordServiceImpl implements ISystemTaskRecordService {
	@Autowired
	private TaskRecordDao taskRecordDao;
	
	@Override
	public TaskRecord get(Integer recordId){
		return taskRecordDao.get(recordId);
	}
	
	@Override
	public List<TaskRecord> list(Map<String, Object> map){
		return taskRecordDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return taskRecordDao.count(map);
	}
	
	@Override
	public int save(TaskRecord taskRecord){
		return taskRecordDao.save(taskRecord);
	}
	
	@Override
	public int update(TaskRecord taskRecord){
		return taskRecordDao.update(taskRecord);
	}
	
	@Override
	public int remove(Integer userId){
		return taskRecordDao.remove(userId);
	}
	
	@Override
	public int batchRemove(Integer[] userIds){
		return taskRecordDao.batchRemove(userIds);
	}
	
	
}
