package com.earn.helper.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.earn.helper.entity.Product;

/**
 * <p>
 * 商品信息表 服务类
 * </p>
 *
 * @author luhui
 * @since 2018-12-21
 */
public interface IProductService extends IService<Product> {

    /**
     * 分页查询列表
     *
     * @param product
     * @return
     */
    Page<Product> pageList(Product product);
}
