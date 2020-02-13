package com.trash.collection.trash.activity.controller;


import com.trash.collection.trash.activity.domain.ActivityMsg;
import com.trash.collection.trash.activity.service.ActivityMsgService;
import com.trash.collection.trash.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


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
    public Response list(String pagesize, String pageIndex,String param){
        Response response = new Response();

        return response;
    }

}

