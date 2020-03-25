package com.earn.system.controller;

import java.io.File;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.earn.helper.controller.BaseController;
import com.earn.helper.entity.R;
import com.earn.common.util.FileUtil;
import com.earn.common.util.StringUtil;

/**
 * 文件上传
 * 
 * @author luhui
 * @since 2019/2/13 23:07
 */
@Controller
@RequestMapping("/system/sysFile")
public class FileController extends BaseController {
    @Autowired
    private ResourceLoader resourceLoader;
    @Value("${uploadPath}")
    private String uploadPath;

    @ResponseBody
    @PostMapping("/upload")
    R upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        if (file != null) {
            String fileName = file.getOriginalFilename();
            fileName = FileUtil.renameToUUID(fileName);
            try {
                return R.ok(FileUtil.uploadFile(file.getBytes(), uploadPath, fileName));
            } catch (Exception e) {
                return R.error("图片上传失败");
            }
        } else {
            return R.error("请上传图片");
        }
    }

    @ResponseBody
    @PostMapping("/getGroupfolderPath")
    public R getGroupfolderPath() {
        return R.ok(uploadPath);
    }

    @ResponseBody
    @RequestMapping("/getFile")
    public Resource getFile(@RequestParam("fileName") String fileName) {
        try {
            // 由于是读取本机的文件，file是一定要加上的
            return resourceLoader.getResource((fileName.contains("http://") || fileName.contains("https://")) ? fileName : "file:" + uploadPath + File.separator + fileName);
        } catch (Exception e) {
            return null;
        }
    }

    public Boolean isExist(String url) {
        Boolean isExist = false;
        if (!StringUtil.isEmpty(url)) {
            String filePath = url.replace("/files/", "");
            filePath = uploadPath + filePath;
            File file = new File(filePath);
            if (file.exists()) {
                isExist = true;
            }
        }
        return isExist;
    }
}
