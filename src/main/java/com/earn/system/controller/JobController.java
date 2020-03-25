package com.earn.system.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.earn.helper.controller.BaseController;
import com.earn.helper.entity.R;
import com.earn.common.util.PageUtils;
import com.earn.common.util.Query;
import com.earn.system.entity.TaskDO;
import com.earn.system.service.ISysJobService;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-26 20:53:48
 */
@Slf4j
@Controller
@RequestMapping("/system/job")
public class JobController extends BaseController {
    private String prefix = "system/job";
    @Autowired
    private ISysJobService taskScheduleJobService;

    @GetMapping()
    String taskScheduleJob() {
        return prefix + "/job";
    }

    @ResponseBody
    @GetMapping("/list")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        // 查询列表数据
        Query query = new Query(params);
        List<TaskDO> taskScheduleJobList = taskScheduleJobService.list(query);
        int total = taskScheduleJobService.count(query);
        PageUtils pageUtils = new PageUtils(taskScheduleJobList, total);
        return pageUtils;
    }

    @GetMapping("/add")
    String add() {
        return prefix + "/add";
    }

    @GetMapping("/edit/{id}")
    String edit(@PathVariable("id") Long id, Model model) {
        TaskDO job = taskScheduleJobService.get(id);
        model.addAttribute("job", job);
        return prefix + "/edit";
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        TaskDO taskScheduleJob = taskScheduleJobService.get(id);
        return R.ok().put("taskScheduleJob", taskScheduleJob);
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    public R save(TaskDO taskScheduleJob) {
        if (taskScheduleJobService.save(taskScheduleJob) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @PostMapping("/update")
    public R update(TaskDO taskScheduleJob) {
        taskScheduleJobService.update(taskScheduleJob);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    public R remove(Long id) {
        if (taskScheduleJobService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    public R remove(@RequestParam("ids[]") Long[] ids) {
        taskScheduleJobService.batchRemove(ids);

        return R.ok();
    }

    @PostMapping(value = "/changeJobStatus")
    @ResponseBody
    public R changeJobStatus(Long id, String cmd) {
        String label = "停止";
        if ("start".equals(cmd)) {
            label = "启动";
        } else {
            label = "停止";
        }
        try {
            taskScheduleJobService.changeStatus(id, cmd);
            return R.ok("任务" + label + "成功");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return R.ok("任务" + label + "失败");
    }

}
