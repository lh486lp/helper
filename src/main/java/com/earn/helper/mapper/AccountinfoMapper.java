package com.earn.helper.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.earn.helper.entity.Accountinfo;

/**
 * <p>
 * 账户信息表 Mapper 接口
 * </p>
 *
 * @author luhui
 * @since 2019-01-05
 */
public interface AccountinfoMapper extends BaseMapper<Accountinfo> {

    List<Accountinfo> selectListPage(@Param("page") Page<Accountinfo> page,
        @Param("accountinfo") Accountinfo accountinfo);

    Double getAmount(Accountinfo accountinfo);

    Double getWithdraw(Accountinfo accountinfo);
}
