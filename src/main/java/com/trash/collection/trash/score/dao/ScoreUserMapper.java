package com.trash.collection.trash.score.dao;

import com.trash.collection.trash.score.VO.ScoreUserVO;
import com.trash.collection.trash.score.domain.ScoreUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Mapper 接口
 *
 * @author seth
 * @since 2020-01-19
 */
@Mapper
public interface ScoreUserMapper extends BaseMapper<ScoreUser> {

    /**
     * 查看用戶可用積分
     *
     * @param id
     */
    ScoreUserVO selectUserScore(Long id);
}
