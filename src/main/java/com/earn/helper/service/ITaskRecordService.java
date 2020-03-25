package com.earn.helper.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.earn.helper.entity.TaskRecord;

/**
 * <p>
 * 任务记录表 服务类
 * </p>
 *
 * @author luhui
 * @since 2018-12-21
 */
public interface ITaskRecordService extends IService<TaskRecord> {

    /**
     * 分页查询列表
     *
     * @param taskRecord
     * @return
     */
    Page<TaskRecord> pageList(TaskRecord taskRecord);

    /**
     * 查询当日完成金牌任务数量
     * 
     * @return
     * @param userId
     * @param issue
     */
    Integer getGoldCount(Integer userId, String issue);

    /**
     * 查询当日完成钻石任务数量
     * 
     * @return
     * @param userId
     * @param issue
     */
    Integer getDiamondCount(Integer userId, String issue);

    /**
     * 根据主键查询
     *
     * @param taskRecord
     * @return
     */
    TaskRecord getByPrimary(TaskRecord taskRecord);

    /**
     * 根据主键更新
     *
     * @param taskRecord
     * @return
     */
    boolean updateByPrimary(TaskRecord taskRecord);

    /**
     * 未完成的任务设置为过期
     *
     * @param issue
     */
    void toExpired(String issue);
}
