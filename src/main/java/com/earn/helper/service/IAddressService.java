package com.earn.helper.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.earn.helper.entity.Address;

/**
 * <p>
 * 收货地址表 服务类
 * </p>
 *
 * @author luhui
 * @since 2018-12-21
 */
public interface IAddressService extends IService<Address> {

    /**
     * 分页查询列表
     *
     * @param address
     * @return
     */
    Page<Address> pageList(Address address);

    /**
     * 去掉用户的默认收货地址
     *
     * @param userId
     * @return
     */
    Integer removeDefault(Integer userId);

    /**
     * 设置地址为默认收货地址
     *
     * @param addrId
     * @return
     */
    Integer setDefault(Integer addrId);
}
