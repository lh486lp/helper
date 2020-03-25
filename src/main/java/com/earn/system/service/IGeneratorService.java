package com.earn.system.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * @author 卢惠
 * @since 2019/2/3 上午10:37
 */
@Service
public interface IGeneratorService {
    List<Map<String, Object>> list();

    byte[] generatorCode(String[] tableNames);
}
