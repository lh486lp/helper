package com.earn.helper.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.earn.helper.entity.Userinfo;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author luhui
 * @since 2018-12-21
 */
@Mapper
public interface UserinfoMapper extends BaseMapper<Userinfo> {

    Integer getMyFansCount(Integer userId);

    Integer getMyFansFansCount(Integer userId);

    List<Map<String, Object>> getMyFansLevelCount(Integer userId);

    List<Map<String, Object>> getMyFansFansLevelCount(Integer userId);

    List<Userinfo> getMyFansList(Userinfo userinfo);

    List<Userinfo> getMyFansFansList(Userinfo userinfo);
}