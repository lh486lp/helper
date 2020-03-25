package com.earn.helper.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.earn.helper.entity.Product;

/**
 * <p>
 * 商品信息表 Mapper 接口
 * </p>
 *
 * @author luhui
 * @since 2018-12-21
 */
public interface ProductMapper extends BaseMapper<Product> {

    List<Product> selectListPage(@Param("page") Page<Product> page, @Param("product") Product product);
}