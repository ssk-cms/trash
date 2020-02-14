package com.trash.collection.trash.activity.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.sun.istack.internal.NotNull;
import com.trash.collection.trash.activity.domain.ActivityMsg;
import com.trash.collection.trash.activity.service.ActivityMsgService;
import com.trash.collection.trash.common.Response;
import com.trash.collection.trash.common.StatusCode;
import com.trash.collection.trash.common.utils.uploadImgUtils.FileNameUtil;
import com.trash.collection.trash.common.utils.uploadImgUtils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


/**
 * 控制器
 *
 * @author seth
 * @since 2020-01-19
 */
@RestController
@RequestMapping("/activity/activityMsg")
public class ActivityMsgController {

    @Autowired
    ActivityMsgService activityMsgService;

    /**
     * 新增活动
     */
    @PostMapping("/addActivity")
    public Response addActivity(@RequestBody @Validated ActivityMsg activityMsg) {
        Response response = new Response();
        activityMsgService.addActivity(activityMsg);
        return response;
    }

    /**
     * 查看活动列表
     */
    @GetMapping("/list")
    public Response list(Integer pageSize, Integer pageIndex, String param, Integer state) {
        Response response = new Response();
        if (Objects.isNull(pageIndex) || Objects.isNull(pageSize)) {
            response.setCode(StatusCode.Invalid_Code)
                    .setStatusCode(StatusCode.Invalid_Code)
                    .setMessage("请将相关参数补充完整！");
        } else {
            Page<ActivityMsg> page = new Page<>(pageIndex, pageSize);
            response.setData(activityMsgService.selectPage(page, param, state));
        }
        return response;
    }

    /**
     * 归档活动
     */
    @GetMapping("/delete")
    public Response delete(Long activityId) {
        Response response = new Response();
        activityMsgService.deleteActivity(activityId);
        return response;
    }

    /**
     * 上传图片
     */
    @PostMapping("/uploadImg")
    public Response uploadImg(@RequestParam("img") MultipartFile multipartFile, HttpServletRequest request) {
        Response response = new Response();
        //定义要上传文件 的存放路径
        String localPath="d:/home/data/image";
        //获得文件名字
        String fileName=multipartFile.getOriginalFilename();
        fileName= FileNameUtil.getFileName(fileName);
        File dest = new File(localPath + fileName);
        if(FileUploadUtil.upload(multipartFile, localPath, fileName)){
            // 将上传的文件写入到服务器端文件夹
            // 获取当前项目运气的完整url
            String requestURL = request.getRequestURL().toString();
            // 获取当前项目的请求路径url
            String requestURI = request.getRequestURI();
            // 得到去掉了uri的路径
            String url = requestURL.substring(0, requestURL.length()-requestURI.length() + 1);
            url="images/"+ fileName;
            response.setData(url);
        }
        else {
            response.setMessage("图片上传失败")
                    .setCode(StatusCode.File_Upload_Fail)
                    .setStatusCode(StatusCode.File_Upload_Fail);
        }
        return response;
    }


}

