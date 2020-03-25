package com.earn.helper.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.earn.helper.entity.Userinfo;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author luhui
 * @since 2018-12-21
 */
public interface IUserinfoService extends IService<Userinfo> {

    /**
     * 获取我的直属粉丝数
     *
     * @param userId
     * @return
     */
    Integer getMyFansCount(Integer userId);

    /**
     * 获取我的直属粉丝的直属粉丝数
     *
     * @param userId
     * @return
     */
    Integer getMyFansFansCount(Integer userId);

    /**
     * 获取我的各等级直属粉丝数
     *
     * @param userId
     * @return
     */
    List<Map<String, Object>> getMyFansLevelCount(Integer userId);

    /**
     * 获取我的各等级直属粉丝的直属粉丝数
     *
     * @param userId
     * @return
     */
    List<Map<String, Object>> getMyFansFansLevelCount(Integer userId);

    /**
     * 获取我的直属粉丝列表
     *
     * @param userinfo
     * @return
     */
    List<Userinfo> getMyFansList(Userinfo userinfo);

    /**
     * 获取我的直属粉丝的直属粉丝列表
     *
     * @param userinfo
     * @return
     */
    List<Userinfo> getMyFansFansList(Userinfo userinfo);
}
