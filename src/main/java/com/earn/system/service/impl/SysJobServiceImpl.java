package com.earn.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.earn.common.util.Const;
import com.earn.system.entity.ScheduleJob;
import com.earn.system.entity.TaskDO;
import com.earn.system.mapper.SysJobMapper;
import com.earn.system.quartz.utils.QuartzManager;
import com.earn.system.quartz.utils.ScheduleJobUtils;
import com.earn.system.service.ISysJobService;

/**
 * @author 卢惠
 * @since 2019/2/15 上午10:48
 */
@Service
public class SysJobServiceImpl implements ISysJobService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SysJobMapper taskScheduleJobMapper;

    @Autowired
    QuartzManager quartzManager;

    @Override
    public TaskDO get(Long id) {
        return taskScheduleJobMapper.get(id);
    }

    @Override
    public List<TaskDO> list(Map<String, Object> map) {
        return taskScheduleJobMapper.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return taskScheduleJobMapper.count(map);
    }

    @Override
    public int save(TaskDO taskScheduleJob) {
        return taskScheduleJobMapper.save(taskScheduleJob);
    }

    @Override
    public int update(TaskDO taskScheduleJob) {
        return taskScheduleJobMapper.update(taskScheduleJob);
    }

    @Override
    public int remove(Long id) {
        try {
            TaskDO scheduleJob = get(id);
            quartzManager.deleteJob(ScheduleJobUtils.entityToData(scheduleJob));
            return taskScheduleJobMapper.remove(id);
        } catch (SchedulerException e) {
            logger.error(e.getMessage(), e);
            return 0;
        }

    }

    @Override
    public int batchRemove(Long[] ids) {
        for (Long id : ids) {
            try {
                TaskDO scheduleJob = get(id);
                quartzManager.deleteJob(ScheduleJobUtils.entityToData(scheduleJob));
            } catch (SchedulerException e) {
                logger.error(e.getMessage(), e);
                return 0;
            }
        }
        return taskScheduleJobMapper.batchRemove(ids);
    }

    @Override
    public void initSchedule() throws SchedulerException {
        // 这里获取任务信息数据
        List<TaskDO> jobList = taskScheduleJobMapper.list(new HashMap<String, Object>(16));
        for (TaskDO scheduleJob : jobList) {
            if ("1".equals(scheduleJob.getJobStatus())) {
                ScheduleJob job = ScheduleJobUtils.entityToData(scheduleJob);
                quartzManager.addJob(job);
            }

        }
    }

    @Override
    public void changeStatus(Long jobId, String cmd) throws SchedulerException {
        TaskDO scheduleJob = get(jobId);
        if (scheduleJob == null) {
            return;
        }
        if (Const.STATUS_RUNNING_STOP.equals(cmd)) {
            quartzManager.deleteJob(ScheduleJobUtils.entityToData(scheduleJob));
            scheduleJob.setJobStatus(ScheduleJob.STATUS_NOT_RUNNING);
        } else {
            if (!Const.STATUS_RUNNING_START.equals(cmd)) {
            } else {
                scheduleJob.setJobStatus(ScheduleJob.STATUS_RUNNING);
                quartzManager.addJob(ScheduleJobUtils.entityToData(scheduleJob));
            }
        }
        update(scheduleJob);
    }

    @Override
    public void updateCron(Long jobId) throws SchedulerException {
        TaskDO scheduleJob = get(jobId);
        if (scheduleJob == null) {
            return;
        }
        if (ScheduleJob.STATUS_RUNNING.equals(scheduleJob.getJobStatus())) {
            quartzManager.updateJobCron(ScheduleJobUtils.entityToData(scheduleJob));
        }
        update(scheduleJob);
    }

}
