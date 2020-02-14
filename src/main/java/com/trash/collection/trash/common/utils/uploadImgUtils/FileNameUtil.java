package com.trash.collection.trash.common.utils.uploadImgUtils;

import com.trash.collection.trash.common.utils.DateUtils;

import java.util.Date;

/**
 * 上传的图片文件生成新的文件名称
 */
public class FileNameUtil {
    /**
     * 获取文件后缀
     *
     * @param fileName
     * @return
     */
    public static String getSuffix(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 生成新的文件名
     *
     * @param fileOriginName 源文件名
     * @return
     */
    public static String getFileName(String fileOriginName) {
        return UUIDUtil.getUUID() + DateUtils.format(new Date()) + FileNameUtil.getSuffix(fileOriginName);
    }

}
