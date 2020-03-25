package com.earn.helper.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.earn.common.config.SystemConfig;
import com.earn.helper.entity.R;
import com.earn.helper.service.ISysteminfoService;
import com.earn.common.util.FileUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 系统配置信息表 前端控制器
 * </p>
 *
 * @author luhui
 * @since 2018-12-22
 */
@Slf4j
@RestController
@RequestMapping("/systeminfo")
public class SysteminfoController extends BaseController {
    @Autowired
    private ISysteminfoService systeminfoService;
    @Value("${uploadPath}")
    private String uploadPath;

    @ResponseBody
    @GetMapping("/list")
    public R list() {
        return new R<Map>().setData(SystemConfig.getSysteminfos());
    }

    @ResponseBody
    @GetMapping("/init")
    public R init() {
        SystemConfig.getInstance().init();
        return R.ok();
    }

    @ResponseBody
    @PostMapping("/uploadFile")
    public R uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        fileName = FileUtil.renameToUUID(fileName);
        try {
            return R.ok().put("filePath", FileUtil.uploadFile(file.getBytes(), uploadPath, fileName));
        } catch (Exception e) {
            return R.error();
        }
    }
}
