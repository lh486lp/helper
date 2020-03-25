package com.earn.system.service;

import java.util.List;
import java.util.Map;

import org.quartz.SchedulerException;
import com.earn.system.entity.TaskDO;

/**
 * @author 卢惠
 * @since 2019/2/15 上午10:49
 */
public interface ISysJobService {

    TaskDO get(Long id);

    List<TaskDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(TaskDO taskScheduleJob);

    int update(TaskDO taskScheduleJob);

    int remove(Long id);

    int batchRemove(Long[] ids);

    void initSchedule() throws SchedulerException;

    void changeStatus(Long jobId, String cmd) throws SchedulerException;

    void updateCron(Long jobId) throws SchedulerException;
}
