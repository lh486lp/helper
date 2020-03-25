package com.earn.helper.system.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.earn.common.annotation.ActiveUser;
import com.earn.common.util.Const;
import com.earn.common.util.PageUtils;
import com.earn.common.util.Query;
import com.earn.helper.entity.*;
import com.earn.helper.enume.AccountTypeEnum;
import com.earn.helper.service.IAccountinfoService;
import com.earn.helper.system.service.ISystemTaskRecordService;
import com.earn.system.entity.SysUserLogin;

/**
 * 任务记录表 luhui
 * 
 * @since 2019-02-11 10:56:30
 */

@Controller
@RequestMapping("/system/taskRecord")
public class SystemTaskRecordController {
    @Autowired
    private ISystemTaskRecordService taskRecordService;
    @Autowired
    private IAccountinfoService accountinfoService;

    @GetMapping()
    String TaskRecord() {
        return "helper/taskRecord/list";
    }

    @ResponseBody
    @GetMapping("/list")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        // 查询列表数据
        Query query = new Query(params);
        List<TaskRecord> taskRecordList = taskRecordService.list(query);
        int total = taskRecordService.count(query);
        PageUtils pageUtils = new PageUtils(taskRecordList, total);
        return pageUtils;
    }

    @GetMapping("/add")
    String add() {
        return "helper/taskRecord/add";
    }

    @GetMapping("/edit/{recordId}")
    String edit(@PathVariable("recordId") Integer recordId, Model model) {
        TaskRecord taskRecord = taskRecordService.get(recordId);
        model.addAttribute("taskRecord", taskRecord);
        return "helper/taskRecord/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    public R save(TaskRecord taskRecord) {
        if (taskRecordService.save(taskRecord) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    public R update(@ActiveUser SysUserLogin userLogin, TaskRecord taskRecord) {
        if (taskRecord.getStatus() == 3) {
            // 获取完成任务的用户的详情
            Userinfo userinfo = new Userinfo().selectById(taskRecord.getUserId());
            // 获取完成的任务的详情
            Task task = new Task().selectById(taskRecord.getTaskId());

            // 自身任务奖励
            Integer opsUserId = userLogin.getSysUser().getUserId();
            Accountinfo accountinfo = new Accountinfo();
            accountinfo.setIsGain(1);
            accountinfo.setCreateTime(new Date());
            accountinfo.setUserId(userinfo.getUserId());
            accountinfo.setType(AccountTypeEnum.rw.ordinal());
            Double amount = accountinfoService.getAmount(accountinfo);
            Double limitReward = Const.getTaskReward(userinfo.getVipLevel());
            Double reward = amount + task.getReward() <= limitReward ? task.getReward() : limitReward - amount;
            accountinfoService.addAccount(userinfo, AccountTypeEnum.rw, reward, null, null, opsUserId);

            // 直属任务奖励
            Userinfo parent = new Userinfo().selectById(userinfo.getParentId());
            if (parent != null && parent.getVisible()) {
                accountinfo.setUserId(parent.getUserId());
                accountinfo.setType(AccountTypeEnum.zjfy.ordinal());
                amount = accountinfoService.getAmount(accountinfo);
                limitReward =
                    Const.getTaskReward(parent.getVipLevel(), userinfo.getVipLevel()) * AccountTypeEnum.zjfy.getRatio();
                reward = amount + task.getReward() <= limitReward ? task.getReward() : limitReward - amount;
                accountinfoService.addAccount(parent, AccountTypeEnum.zjfy, reward, null, null, opsUserId);
                // 直属的直属任务奖励
                Userinfo grandpa = new Userinfo().selectById(parent.getParentId());
                if (grandpa != null && grandpa.getVisible()) {
                    accountinfo.setUserId(grandpa.getUserId());
                    accountinfo.setType(AccountTypeEnum.jjfy.ordinal());
                    amount = accountinfoService.getAmount(accountinfo);
                    limitReward = Const.getTaskReward(grandpa.getVipLevel(), userinfo.getVipLevel())
                        * AccountTypeEnum.jjfy.getRatio();
                    reward = amount + task.getReward() <= limitReward ? task.getReward() : limitReward - amount;
                    accountinfoService.addAccount(grandpa, AccountTypeEnum.jjfy, reward, null, null, opsUserId);
                }
            }
        }
        taskRecordService.update(taskRecord);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    public R remove(Integer recordId) {
        if (taskRecordService.remove(recordId) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    public R remove(@RequestParam("ids[]") Integer[] userIds) {
        if (taskRecordService.batchRemove(userIds) > 0) {
            return R.ok();
        }
        return R.error();
    }
}
