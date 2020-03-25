package com.earn.common.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.earn.common.config.SystemConfig;

import lombok.extern.slf4j.Slf4j;

/**
 * 服务启动执行
 *
 * @author luhui
 * @since 2018/12/21 21:22
 */
@Slf4j
@Component
public class StartupRunner implements CommandLineRunner {
    @Override
    public void run(String... args) {
        // 加载系统配置参数
        SystemConfig.getInstance().init();
        log.info(">>>>>>>>>>>>>>>>参数加载成功！<<<<<<<<<<<<<<");
    }
}