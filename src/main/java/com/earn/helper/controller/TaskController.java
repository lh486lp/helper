package com.earn.helper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.earn.common.annotation.ActiveUser;
import com.earn.helper.entity.R;
import com.earn.helper.entity.Task;
import com.earn.helper.entity.UserLogin;
import com.earn.helper.service.ITaskRecordService;
import com.earn.helper.service.ITaskService;
import com.earn.common.util.Const;
import com.earn.common.util.StringUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 任务表 前端控制器
 * </p>
 *
 * @author luhui
 * @since 2018-12-22
 */
@Slf4j
@RestController
@RequestMapping("/task")
public class TaskController extends BaseController {
    @Autowired
    private ITaskService taskService;
    @Autowired
    private ITaskRecordService taskRecordService;

    @ResponseBody
    @GetMapping("/list")
    public R list(@ActiveUser UserLogin userLogin, Task task) {
        Integer userId = userLogin.getUserinfo().getUserId();
        task.setUserId(userId);
        Page<Task> list = taskService.pageList(task);
        R r = new R<Page<Task>>().setData(list);

        // 获取当日完成任务次数
        String issue = StringUtil.dateFormat();
        int goldCount = taskRecordService.getGoldCount(userId, issue);
        int diamondCount = taskRecordService.getDiamondCount(userId, issue);
        r.put("goldCompleteCount", goldCount);
        r.put("diamondCompleteCount", diamondCount);
        r.put("goldCount", Const.getTaskCount(Const.GOLD));
        r.put("diamondCount", Const.getTaskCount(Const.DIAMOND));
        return r;
    }

    @ResponseBody
    @GetMapping("/get/{id}")
    public R get(@ActiveUser UserLogin userLogin, @PathVariable("id") Integer id) {
        Task task = new Task();
        task.setTaskId(id);
        task.setUserId(userLogin.getUserinfo().getUserId());

        task = taskService.getTaskById(task);
        if (!StringUtil.isNullAndBlank(task.getTaskContent())) {
            task.setTaskContent(task.getTaskContent().replaceAll(Const.FILE_GET_METHOD_REG, Const.FILE_PATH));
        }
        if (!StringUtil.isNullAndBlank(task.getImgPaths())) {
            StringBuilder paths = new StringBuilder();
            String[] imgPaths = task.getImgPaths().split(",");
            for (String imgPath : imgPaths) {
                paths.append(",").append(Const.FILE_PATH).append(imgPath);
            }
            task.setImgPaths(paths.substring(1).toString());
        }
        return new R<Task>().setData(task);
    }

    @ResponseBody
    @GetMapping("/getTask/{id}")
    public R getTask(@ActiveUser UserLogin userLogin, @PathVariable("id") Integer id) {
        Task task = new Task().selectById(id);

        return new R<Task>().setData(task);
    }
}
