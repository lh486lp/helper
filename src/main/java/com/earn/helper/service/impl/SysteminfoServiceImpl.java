package com.earn.helper.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.earn.helper.entity.Systeminfo;
import com.earn.helper.mapper.SysteminfoMapper;
import com.earn.helper.service.ISysteminfoService;

/**
 * <p>
 * 系统配置信息表 服务实现类
 * </p>
 *
 * @author luhui
 * @since 2018-12-22
 */
@Service
public class SysteminfoServiceImpl extends ServiceImpl<SysteminfoMapper, Systeminfo> implements ISysteminfoService {

}
