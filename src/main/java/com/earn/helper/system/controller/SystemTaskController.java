package com.earn.helper.system.controller;

import java.util.List;
import java.util.Map;

import com.earn.common.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.earn.common.util.PageUtils;
import com.earn.common.util.Query;
import com.earn.helper.entity.R;
import com.earn.helper.entity.Task;
import com.earn.helper.system.service.ISystemTaskService;

/**
 * 任务表 luhui
 * 
 * @since 2019-02-11 10:56:29
 */

@Controller
@RequestMapping("/system/task")
public class SystemTaskController {
    @Autowired
    private ISystemTaskService taskService;

    @GetMapping()
    String Task() {
        return "helper/task/list";
    }

    @ResponseBody
    @GetMapping("/list")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        // 查询列表数据
        Query query = new Query(params);
        List<Task> taskList = taskService.list(query);
        int total = taskService.count(query);
        PageUtils pageUtils = new PageUtils(taskList, total);
        return pageUtils;
    }

    @GetMapping("/add")
    String add() {
        return "helper/task/add";
    }

    @GetMapping("/edit/{taskId}")
    String edit(@PathVariable("taskId") Integer taskId, Model model) {
        Task task = taskService.get(taskId);
        model.addAttribute("task", task);
        model.addAttribute("imgPaths", StringUtil.isNullAndBlank(task.getImgPaths()) ? "" : task.getImgPaths().split(","));
        return "helper/task/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    public R save(Task task) {
        if (taskService.save(task) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    public R update(Task task) {
        taskService.update(task);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    public R remove(Integer taskId) {
        if (taskService.remove(taskId) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody

    public R remove(@RequestParam("ids[]") Integer[] taskIds) {
        if (taskService.batchRemove(taskIds) > 0) {
            return R.ok();
        }
        return R.error();
    }
}
