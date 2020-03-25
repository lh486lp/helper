package com.earn.helper.system.mapper;

import com.earn.helper.entity.TaskRecord;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 任务记录表
 * luhui
 * @date 2019-02-11 10:56:30
 */
@Mapper
public interface TaskRecordDao {

	TaskRecord get(Integer recordId);
	
	List<TaskRecord> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TaskRecord taskRecord);
	
	int update(TaskRecord taskRecord);
	
	int remove(Integer recordId);
	
	int batchRemove(Integer[] recordIds);
	
	
}
