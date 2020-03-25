package com.earn.helper.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.earn.helper.entity.Orderinfo;

/**
 * <p>
 * 订单信息表 服务类
 * </p>
 *
 * @author luhui
 * @since 2018-12-21
 */
public interface IOrderinfoService extends IService<Orderinfo> {

    /**
     * 分页查询列表
     *
     * @param orderinfo
     * @return
     */
    Page<Orderinfo> pageList(Orderinfo orderinfo);

    Orderinfo getOrderById(Integer id);
}
