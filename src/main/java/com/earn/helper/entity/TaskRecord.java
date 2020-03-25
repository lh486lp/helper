package com.earn.helper.entity;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 任务记录表
 * </p>
 *
 * @author luhui
 * @since 2018-12-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TaskRecord extends BaseEntity<TaskRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "record_id", type = IdType.AUTO)
    private Integer recordId;

    /**
     * 用户id
     */
    private Integer userId;
    @TableField(exist = false)
    private Userinfo userinfo;
    /**
     * 任务id
     */
    private Integer taskId;
    @TableField(exist = false)
    private Task task;
    /**
     * 任务期数
     */
    private String issue;
    /**
     * 任务赏金
     */
    private Double reward;
    /**
     * 任务图片
     */
    private String imgPath;
    /**
     * 状态 0-进行中 1-待审核 2-已失效 3-已完成
     */
    private Integer status;

    @TableField(exist = false)
    private MultipartFile file;

    @Override
    protected Serializable pkVal() {
        return this.recordId;
    }
}
