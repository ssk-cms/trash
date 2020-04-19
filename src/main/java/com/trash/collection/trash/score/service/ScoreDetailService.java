package com.trash.collection.trash.score.service;

import com.trash.collection.trash.score.VO.ScoreDetailVO;
import com.trash.collection.trash.score.domain.ScoreDetail;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * 服务类
 *
 * @author seth
 * @since 2020-03-07
 */
public interface ScoreDetailService extends IService<ScoreDetail> {

    /**
     * 获取积分详情
     * */
    List<ScoreDetailVO> selectScoreDetail(Long userId);
}
