package com.earn.helper.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 任务表
 * </p>
 *
 * @author luhui
 * @since 2018-12-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Task extends BaseEntity<Task> {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "task_id", type = IdType.AUTO)
    private Integer taskId;
    /**
     * 任务类型 1-其他任务 2-微广推手
     */
    private Integer taskType;
    /**
     * 任务标题
     */
    private String taskTitle;
    /**
     * 任务内容
     */
    private String taskContent;
    /**
     * 任务等级 2-金牌 3-钻石
     */
    private Integer taskLevel;
    /**
     * 任务奖励
     */
    private Double reward;
    /**
     * 任务图片
     */
    private String imgPaths;
    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    /**
     * 任务状态
     */
    @TableField(exist = false)
    private Integer status;
    /**
     * 任务期数
     */
    @TableField(exist = false)
    private Integer userId;
    /**
     * 任务期数
     */
    @TableField(exist = false)
    private String issue;

    @Override
    protected Serializable pkVal() {
        return this.taskId;
    }
}
