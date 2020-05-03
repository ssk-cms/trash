package com.trash.collection.trash.score.service.impl;

import com.trash.collection.trash.common.NotLoginedDotGo;
import com.trash.collection.trash.common.RRException;
import com.trash.collection.trash.score.VO.ScoreUserVO;
import com.trash.collection.trash.score.domain.ScoreUser;
import com.trash.collection.trash.score.dao.ScoreUserMapper;
import com.trash.collection.trash.score.service.ScoreUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.trash.collection.trash.user.VO.UserInfo;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 服务实现类
 *
 * @author seth
 * @since 2020-01-19
 */
@Service
public class ScoreUserServiceImpl extends ServiceImpl<ScoreUserMapper, ScoreUser> implements ScoreUserService {


    /**
     * 查看用户可用积分
     */
    @Override
    public ScoreUserVO selectUserScore() {
        UserInfo userInfo = NotLoginedDotGo.getUser();
        if (Objects.isNull(userInfo)){
            throw new RRException("请登录！");
        }
        if (Objects.isNull(userInfo.getId())){
            throw new RRException("请登录！");
        }
        ScoreUserVO scoreUserVO = this.baseMapper.selectUserScore(userInfo.getId());
        return scoreUserVO;
    }
}
