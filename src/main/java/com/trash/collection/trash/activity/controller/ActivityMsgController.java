package com.trash.collection.trash.activity.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.trash.collection.trash.activity.domain.ActivityMsg;
import com.trash.collection.trash.activity.service.ActivityMsgService;
import com.trash.collection.trash.common.Response;
import com.trash.collection.trash.common.StatusCode;
import com.trash.collection.trash.common.utils.uploadImgUtils.FileNameUtil;
import com.trash.collection.trash.common.utils.uploadImgUtils.FileUploadUtil;
import com.trash.collection.trash.product.service.ProductKindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;


/**
 * 控制器
 *
 * @author seth
 * @since 2020-01-19
 */
@RestController
@RequestMapping("/activity/activityMsg")
//@CrossOrigin(origins = "", maxAge = 3600)
@CrossOrigin
public class ActivityMsgController {

    @Autowired
    ActivityMsgService activityMsgService;

    @Autowired
    ProductKindService productKindService;

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
            response.setData(activityMsgService.selectPage(new Page<>(pageIndex, pageSize), param, state));
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
     * 编辑活动
     * */
    @PostMapping("/update")
    public Response update(@RequestBody ActivityMsg activityMsg){
        Response response = new Response();
        if (Objects.isNull(activityMsg.getId())){
            return productKindService.judgeParam();
        }
        activityMsgService.updateMsg(activityMsg);
        return response;
    }

    /**
     * 查看具体某一条活动信息内容
     * */
    @GetMapping("/selectOneMsg")
    public Response selectOneMsg(Long activityMsgId){
        Response response = new Response();
        if (Objects.isNull(activityMsgId)){
            return productKindService.judgeParam();
        }
        response.setData(activityMsgService.selectById(activityMsgId));
        return response;
    }

    /**
     * 上传图片
     */
    @PostMapping("/uploadImg")
    public Response uploadImg(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        Response response = new Response();
        //定义要上传文件 的存放路径
        String localPath="d:/home/data/image";
        //获得文件名字
        String fileName=file.getOriginalFilename();
        fileName= FileNameUtil.getFileName(fileName);
//        File dest = new File(localPath + fileName);
        if(FileUploadUtil.upload(file, localPath, fileName)){
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

