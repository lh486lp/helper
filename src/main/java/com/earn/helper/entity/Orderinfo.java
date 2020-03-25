package com.earn.helper.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 订单信息表
 * </p>
 *
 * @author luhui
 * @since 2018-12-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Orderinfo extends BaseEntity<Orderinfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 订单ID
     */
    @TableId(value = "order_id", type = IdType.AUTO)
    private Integer orderId;
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 用户ID
     */
    private Integer userId;
    @TableField(exist = false)
    private Userinfo userinfo;
    /**
     * 地址ID
     */
    private Integer addrId;
    @TableField(exist = false)
    private Address address;
    /**
     * 商品ID
     */
    private Integer productId;
    @TableField(exist = false)
    private Product product;
    /**
     * 成交单价
     */
    private Double productUnitPrice;
    /**
     * 购买数量
     */
    private Integer productCount;
    /**
     * 支付时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date payTime;
    /**
     * 快递种类
     */
    private String postType;
    /**
     * 快递单号
     */
    private String postNo;
    /**
     * 快递邮寄时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date postTime;
    /**
     * 订单状态 0-待支付 1-待发货 2-待收货 3-已完成
     */
    private Integer status;

    @Override
    protected Serializable pkVal() {
        return this.orderId;
    }

}
