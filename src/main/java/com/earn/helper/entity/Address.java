package com.earn.helper.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 收货地址表
 * </p>
 *
 * @author luhui
 * @since 2018-12-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Address extends BaseEntity<Address> {

    private static final long serialVersionUID = 1L;

    /**
     * 地址ID
     */
    @TableId(value = "addr_id", type = IdType.AUTO)
    private Integer addrId;
    /**
     * 用户ID
     */
    private Integer userId;
    @TableField(exist = false)
    private Userinfo userinfo;
    /**
     * 收货人姓名
     */
    private String receiverName;
    /**
     * 收货人手机号
     */
    private String receiverPhone;
    /**
     * 省份
     */
    private String province;
    /**
     * 地市
     */
    private String area;
    /**
     * 城镇
     */
    private String town;
    /**
     * 详细地址
     */
    private String detail;
    /**
     * 默认地址 0-否 1-是
     */
    private Boolean defaultAddr;

    @Override
    protected Serializable pkVal() {
        return this.addrId;
    }

}
