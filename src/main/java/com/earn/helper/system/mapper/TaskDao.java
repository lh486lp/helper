package com.earn.helper.system.mapper;

import com.earn.helper.entity.Task;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 任务表
 * luhui
 * @date 2019-02-11 10:56:29
 */
@Mapper
public interface TaskDao {

	Task get(Integer taskId);
	
	List<Task> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(Task task);
	
	int update(Task task);
	
	int remove(Integer task_id);
	
	int batchRemove(Integer[] taskIds);
	
	
}
