package com.earn.helper.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.earn.common.annotation.ActiveUser;
import com.earn.common.config.SystemConfig;
import com.earn.helper.entity.R;
import com.earn.helper.entity.Task;
import com.earn.helper.entity.TaskRecord;
import com.earn.helper.entity.UserLogin;
import com.earn.helper.service.ITaskRecordService;
import com.earn.common.util.Const;
import com.earn.common.util.FileUtil;
import com.earn.common.util.StringUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 任务记录表 前端控制器
 * </p>
 *
 * @author luhui
 * @since 2018-12-21
 */
@Slf4j
@RestController
@RequestMapping("/taskRecord")
public class TaskRecordController extends BaseController {
    @Autowired
    private ITaskRecordService taskRecordService;
    @Value("${uploadPath}")
    private String uploadPath;

    @ResponseBody
    @GetMapping("/list")
    public R list(TaskRecord taskRecord) {
        Page<TaskRecord> list = taskRecordService.pageList(taskRecord);
        return new R<Page<TaskRecord>>().setData(list);
    }

    @ResponseBody
    @PostMapping("/save")
    public R save(@ActiveUser UserLogin userLogin, TaskRecord taskRecord) {
        if (StringUtil.numFormat(taskRecord.getTaskId()) == 0) {
            return R.error("没有找到这个任务");
        }
        taskRecord.setUserId(userLogin.getUserinfo().getUserId());
        taskRecord.setIssue(StringUtil.dateFormat());
        TaskRecord record = taskRecordService.getByPrimary(taskRecord);

        taskRecord.setOpsUserId(userLogin.getUserinfo().getUserId());
        if (record == null) {
            // 根据任务等级，确定任务奖励，保存到记录中，不因任务赏金改变而影响历史记录
            taskRecord.setReward(new Task().selectById(taskRecord.getTaskId()).getReward());
            return getR(taskRecord.insert());
        } else {
            if (StringUtil.numFormat(taskRecord.getStatus()) == 1) {
                if ((System.currentTimeMillis() - record.getCreateTime().getTime()) > Const.TASK_LIMIT_TIME) {
                    if (taskRecord.getFile() != null) {
                        MultipartFile file = taskRecord.getFile();
                        String fileName = file.getOriginalFilename();
                        fileName = FileUtil.renameToUUID(fileName);
                        try {
                            taskRecord.setImgPath(FileUtil.uploadFile(file.getBytes(), uploadPath, fileName));
                        } catch (Exception e) {
                            return R.error("图片上传失败");
                        }
                    } else {
                        return R.error("请上传图片");
                    }
                } else {
                    return R.error("没有达到限制时间，请稍后提交");
                }
            }
            taskRecord.setUpdateTime(new Date());
            return getR(taskRecordService.updateByPrimary(taskRecord));
        }
    }
}
