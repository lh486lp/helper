package com.earn.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;

/**
 * 文件管理
 *
 * @author luhui
 * @since 2019/1/20 19:33
 */
@Slf4j
public class FileUtil {
    public static String uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();

        return fileName;
    }

    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            return file.delete();
        } else {
            return false;
        }
    }

    public static String renameToUUID(String fileName) {
        return UUID.randomUUID() + "." + fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public static boolean isExist(String imgPath) {
        return false;
    }
}
