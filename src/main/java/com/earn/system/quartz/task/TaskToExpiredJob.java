package com.earn.system.quartz.task;

import com.earn.helper.service.ITaskRecordService;
import com.earn.common.util.StringUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

/**
 * 每天将已领取但未完成的任务设置为过期
 * 
 * @author 卢惠
 * @since 2019/2/15 上午11:13
 */
@Slf4j
@Component
public class TaskToExpiredJob implements Job {
    @Autowired
    private ITaskRecordService taskRecordService;

    @Override
    public void execute(JobExecutionContext arg0) {
        log.info("未完成的任务设置为过期-----start-----" + System.currentTimeMillis());
        try {
            taskRecordService.toExpired(StringUtil.dateFormat());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        log.info("未完成的任务设置为过期------end----" + System.currentTimeMillis());
    }

}