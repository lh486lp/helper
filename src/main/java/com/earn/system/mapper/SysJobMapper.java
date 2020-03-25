package com.earn.system.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import com.earn.system.entity.TaskDO;

/**
 * 任务
 *
 * @author 卢惠
 * @since 2019/2/15 上午10:38
 */
@Mapper
public interface SysJobMapper {

    TaskDO get(Long id);

    List<TaskDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(TaskDO task);

    int update(TaskDO task);

    int remove(Long id);

    int batchRemove(Long[] ids);
}
