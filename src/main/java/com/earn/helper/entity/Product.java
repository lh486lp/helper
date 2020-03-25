package com.earn.helper.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 商品信息表
 * </p>
 *
 * @author luhui
 * @since 2018-12-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Product extends BaseEntity<Product> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "product_id", type = IdType.AUTO)
    private Integer productId;
    /**
     * 商品名称
     */
    @TableField("product_name")
    private String productName;
    /**
     * 价格
     */
    @TableField("product_price")
    private Double productPrice;
    /**
     * 优惠价格
     */
    @TableField("preferential_price")
    private Double preferentialPrice;
    /**
     * 封面图片
     */
    @TableField("img_path")
    private String imgPath;
    /**
     * banner图片
     */
    @TableField("img_paths")
    private String imgPaths;
    /**
     * 规格参数
     */
    @TableField("specification")
    private String specification;
    /**
     * 商品介绍
     */
    @TableField("introduction")
    private String introduction;
    /**
     * 图文详情
     */
    @TableField("content")
    private String content;

    /**
     * 类型 0-推广 1-普通 2-金牌 3-钻石
     */
    @TableField("product_type")
    private Integer productType;

    @Override
    protected Serializable pkVal() {
        return this.productId;
    }

}
