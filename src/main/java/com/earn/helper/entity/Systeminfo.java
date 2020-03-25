package com.earn.helper.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 系统配置信息表
 * </p>
 *
 * @author luhui
 * @since 2018-12-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Systeminfo extends BaseEntity<Systeminfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "_key", type = IdType.INPUT)
    private String key;

    private String value;

    private String description;

    @Override
    protected Serializable pkVal() {
        return this.key;
    }
}
