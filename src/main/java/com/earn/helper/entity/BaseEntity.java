package com.earn.helper.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @param <T>
 * @author luhui
 * @since 2018/12/21 07:22
 */
@Data
@EqualsAndHashCode(callSuper = false)
public abstract class BaseEntity<T extends BaseEntity> extends Model<T> {
    private static final long serialVersionUID = 1L;
    /**
     * 状态 0-禁用 1-启用
     */
    private Boolean visible = true;
    /**
     * 操作人
     */
    private Integer opsUserId;
    @TableField(exist = false)
    private Userinfo opsUserinfo;
    /**
     * 创建时间
     */
    @TableField("create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 操作时间
     */
    @TableField("update_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @TableField(exist = false)
    private Integer page;

    @TableField(exist = false)
    private Integer size;

    /**
     * 主键
     *
     * @return Serializable
     */
    @Override
    protected abstract Serializable pkVal();
}
