package com.trash.collection.trash.activity.controller;


import com.trash.collection.trash.activity.domain.ActivityMsg;
import com.trash.collection.trash.activity.service.ActivityMsgService;
import com.trash.collection.trash.common.ResultWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.Result;
import javax.xml.ws.Response;

/**
 *  控制器
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
     * */
    @PostMapping("/addActivity")
    public ResultWrapper addActivity(@RequestBody ActivityMsg activityMsg){
        ResultWrapper resultWrapper = new ResultWrapper();

        return resultWrapper;
    }

}

