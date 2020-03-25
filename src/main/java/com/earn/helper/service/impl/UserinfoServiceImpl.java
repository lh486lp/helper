package com.earn.helper.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.earn.helper.entity.Userinfo;
import com.earn.helper.mapper.UserinfoMapper;
import com.earn.helper.service.IUserinfoService;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author luhui
 * @since 2018-12-21
 */
@Slf4j
@Service
public class UserinfoServiceImpl extends ServiceImpl<UserinfoMapper, Userinfo> implements IUserinfoService {
    @Autowired
    private UserinfoMapper userinfoMapper;

    @Override
    public Integer getMyFansCount(Integer userId) {
        return userinfoMapper.getMyFansCount(userId);
    }

    @Override
    public Integer getMyFansFansCount(Integer userId) {
        return userinfoMapper.getMyFansFansCount(userId);
    }

    @Override
    public List<Map<String, Object>> getMyFansLevelCount(Integer userId) {
        return userinfoMapper.getMyFansLevelCount(userId);
    }

    @Override
    public List<Map<String, Object>> getMyFansFansLevelCount(Integer userId) {
        return userinfoMapper.getMyFansFansLevelCount(userId);
    }

    @Override
    public List<Userinfo> getMyFansList(Userinfo userinfo) {
        return userinfoMapper.getMyFansList(userinfo);
    }

    @Override
    public List<Userinfo> getMyFansFansList(Userinfo userinfo) {
        return userinfoMapper.getMyFansFansList(userinfo);
    }
}
