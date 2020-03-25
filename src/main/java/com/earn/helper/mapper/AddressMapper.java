package com.earn.helper.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.earn.helper.entity.Address;

/**
 * <p>
 * 收货地址表 Mapper 接口
 * </p>
 *
 * @author luhui
 * @since 2018-12-21
 */
public interface AddressMapper extends BaseMapper<Address> {

    List<Address> selectListPage(@Param("page") Page<Address> page, @Param("address") Address address);
}