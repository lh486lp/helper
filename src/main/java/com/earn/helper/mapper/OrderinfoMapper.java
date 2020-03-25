package com.earn.helper.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.earn.helper.entity.Orderinfo;

/**
 * <p>
 * 订单信息表 Mapper 接口
 * </p>
 *
 * @author luhui
 * @since 2018-12-21
 */
public interface OrderinfoMapper extends BaseMapper<Orderinfo> {

    List<Orderinfo> selectListPage(@Param("page") Page<Orderinfo> page, @Param("orderinfo") Orderinfo orderinfo);

    Orderinfo getOrderById(Integer id);
}