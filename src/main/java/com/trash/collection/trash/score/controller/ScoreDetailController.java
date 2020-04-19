package com.trash.collection.trash.score.controller;


import com.trash.collection.trash.common.NotLoginedDotGo;
import com.trash.collection.trash.common.Response;
import com.trash.collection.trash.score.service.ScoreDetailService;
import com.trash.collection.trash.user.VO.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * 控制器
 *
 * @author seth
 * @since 2020-03-07
 */
@RestController
@RequestMapping("/score/scoreDetail")
@CrossOrigin
public class ScoreDetailController {

    @Autowired
    private ScoreDetailService scoreDetailService;

    UserInfo userInfo = NotLoginedDotGo.getUser();

    /**
     * 用户查看自己的积分详情
     * */
    @GetMapping("/selectScoreDetail")
    public Response selectScoreDetail(){
        Long userId = userInfo.getId();
        Response response = new Response();
        response.setData(this.scoreDetailService.selectScoreDetail(userId));
        return response;
    }

}

