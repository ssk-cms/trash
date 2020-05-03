package com.trash.collection.trash.score.controller;


import com.trash.collection.trash.common.Response;
import com.trash.collection.trash.score.service.ScoreUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 *  控制器
 *
 * @author seth
 * @since 2020-01-19
 */
@RestController
@RequestMapping("/score/scoreUser")
@CrossOrigin
public class ScoreUserController {

    @Autowired
    ScoreUserService scoreUserService;

    /**
     * 查看用户剩余可用积分
     * */
    @GetMapping("/selectUserScore")
    public Response selectUserScore(){
        Response response = new Response();
        response.setData(this.scoreUserService.selectUserScore());
        return response;
    }

}

