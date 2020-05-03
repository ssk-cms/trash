package com.trash.collection.trash.score.service;

import com.trash.collection.trash.score.VO.ScoreUserVO;
import com.trash.collection.trash.score.domain.ScoreUser;
import com.baomidou.mybatisplus.service.IService;

/**
 *  服务类
 *
 * @author seth
 * @since 2020-01-19
 */
public interface ScoreUserService extends IService<ScoreUser> {

    /**
     * 查看用户可用积分
     * */
    ScoreUserVO selectUserScore();
}
