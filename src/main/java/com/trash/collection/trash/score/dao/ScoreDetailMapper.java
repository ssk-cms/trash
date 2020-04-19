package com.trash.collection.trash.score.dao;

import com.trash.collection.trash.score.VO.ScoreDetailVO;
import com.trash.collection.trash.score.domain.ScoreDetail;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Mapper 接口
 *
 * @author seth
 * @since 2020-03-07
 */
@Mapper
public interface ScoreDetailMapper extends BaseMapper<ScoreDetail> {

    /**
     * 查询积分详情
     * */
    List<ScoreDetailVO> selectScoreDeatil(Long userId);
}
