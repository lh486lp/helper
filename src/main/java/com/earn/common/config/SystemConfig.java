package com.earn.common.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.earn.helper.entity.Systeminfo;

import lombok.extern.slf4j.Slf4j;

/**
 * 系统配置参数
 *
 * @author luhui
 * @since 2018/12/21 21:22
 */
@Slf4j
public class SystemConfig {
    private static SystemConfig config = null;
    private static Map<String, Object> systeminfos = new HashMap<>();

    /**
     * 私有构造方法
     */
    private SystemConfig() {}

    /**
     * 单例方法
     */
    public static SystemConfig getInstance() {
        if (config == null) {
            synchronized (SystemConfig.class) {
                if (config == null) {
                    config = new SystemConfig();
                }
            }
        }
        return config;
    }

    /**
     * 初始化参数类型
     */
    public void init() {
        log.info(">>>>>>>>>>>>>>>加载系统配置参数<<<<<<<<<<<<<");
        List<Systeminfo> list = new Systeminfo().selectAll();
        for (Systeminfo systeminfo : list) {
            systeminfos.put(systeminfo.getKey(), systeminfo.getValue());
        }
    }

    /**
     * 系统参数
     *
     * @return systeminfo
     */
    public static Map<String, Object> getSysteminfos() {
        return systeminfos;
    }

    public static Object getSysteminfo(String key) {
        return systeminfos.get(key);
    }
}
